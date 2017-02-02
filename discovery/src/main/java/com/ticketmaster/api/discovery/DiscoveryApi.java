package com.ticketmaster.api.discovery;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.ticketmaster.api.Version;
import com.ticketmaster.api.discovery.operation.ByIdOperation;
import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.operation.SearchVenuesOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.api.discovery.response.Response;
import com.ticketmaster.api.discovery.util.Preconditions;
import com.ticketmaster.discovery.model.*;
import com.ticketmaster.discovery.model.Page.Link;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class DiscoveryApi {

  private Logger logger = LoggerFactory.getLogger(DiscoveryApi.class);

  private static final String USER_AGENT = "User-Agent";
  private static final String API_KEY_QUERY_PARAM = "apikey";
  private final OkHttpClient client;
  private final DiscoveryApiConfiguration configuration;
  private final ObjectMapper mapper;
  private final String apiKey;
  private final HashMap<Class<?>, String> pathByType;

  public DiscoveryApi(String apiKey) {
    this(apiKey, DiscoveryApiConfiguration.builder().build());
  }

  public DiscoveryApi(String apiKey, DiscoveryApiConfiguration configuration) {
    Preconditions.checkNotNull(apiKey, "The API key is mandatory");
    this.apiKey = apiKey;
    this.configuration = configuration;
    this.mapper = new ObjectMapper() //
        .registerModule(new JodaModule()) //
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    this.client = new OkHttpClient.Builder()
                 .readTimeout(configuration.getSocketTimeout(), TimeUnit.MILLISECONDS)
                 .connectTimeout(configuration.getSocketConnectTimeout(), TimeUnit.MILLISECONDS)
                 .build();

    this.pathByType = new HashMap<>();
    this.pathByType.put(Event.class, "events");
    this.pathByType.put(Attraction.class, "attractions");
    this.pathByType.put(Venue.class, "venues");
  }

  public Response<Event> getEvent(ByIdOperation operation) throws IOException {
    return getById(operation, Event.class);
  }

  public Response<Venue> getVenue(ByIdOperation operation) throws IOException {
    return getById(operation, Venue.class);
  }

  public Response<Attraction> getAttraction(ByIdOperation operation) throws IOException {
    return getById(operation, Attraction.class);
  }

  public PagedResponse<Events> searchEvents(SearchEventsOperation operation) throws IOException {
    logger.debug("searchEvents invoked with {}", operation);

    Builder builder = urlBuilder(pathByType.get(Event.class));
    for (Entry<String, String> e : operation.getQueryParameters().entrySet()) {
      builder.addQueryParameter(e.getKey(), e.getValue());
    }

    logger.debug("searchEvents about to load {}", builder.build());
    Request request = getRequest(builder.build());
    okhttp3.Response response = client.newCall(request).execute();

    return new PagedResponse<Events>(response, mapper, Events.class);
  }

  public PagedResponse<Attractions> searchAttractions(SearchAttractionsOperation operation)
      throws IOException {
    logger.debug("searchAttractions invoked with {}", operation);

    Builder builder = urlBuilder(pathByType.get(Attraction.class));
    for (Entry<String, String> e : operation.getQueryParameters().entrySet()) {
      builder.addQueryParameter(e.getKey(), e.getValue());
    }

    logger.debug("searchAttractions about to load {}", builder.build());
    Request request = getRequest(builder.build());
    okhttp3.Response response = client.newCall(request).execute();

    return new PagedResponse<Attractions>(response, mapper, Attractions.class);
  }

  public PagedResponse<Venues> searchVenues(SearchVenuesOperation operation) throws IOException {
    logger.debug("searchVenues invoked with {}", operation);

    Builder builder = urlBuilder(pathByType.get(Venue.class));
    for (Entry<String, String> e : operation.getQueryParameters().entrySet()) {
      builder.addQueryParameter(e.getKey(), e.getValue());
    }

    logger.debug("searchVenues about to load {}", builder.build());
    Request request = getRequest(builder.build());
    okhttp3.Response response = client.newCall(request).execute();

    return new PagedResponse<Venues>(response, mapper, Venues.class);
  }

  public <T> PagedResponse<T> nextPage(PagedResponse<T> response) throws IOException {
    if (response == null || response.getNextPageLink() == null) {
      return null;
    }

    return navigateTo(response.getNextPageLink(), response.getType());
  }

  public <T> PagedResponse<T> previousPage(PagedResponse<T> response) throws IOException {
    Link previous = response.getPreviousPageLink();
    if (previous == null) {
      return null;
    }

    return navigateTo(response.getPreviousPageLink(), response.getType());
  }

  private Builder baseUrlBuilder() {
    Builder builder = new Builder();
    builder.scheme(configuration.getProtocol());
    builder.host(configuration.getDomainName());
    if (configuration.isPortSet()) {
      builder.port(configuration.getPort());
    }
    return builder;
  }

  // Package protected for testing purposess
  Builder urlBuilder(String path) {
    Builder builder =
        baseUrlBuilder().addPathSegment(configuration.getApiPackage())
            .addPathSegment(configuration.getApiVersion()).addPathSegment(path);

    if (configuration.getPathModifier() != DiscoveryApiConfiguration.PathModifier.NONE) {
      builder.addPathSegment(configuration.getPathModifier().getModifier());
    }

    return builder;
  }

  private <T> Response<T> getById(ByIdOperation operation, Class<T> clazz) throws IOException {
    logger.debug("get{} invoked with {}", clazz.getSimpleName(), operation);
    Builder builder = urlBuilder(pathByType.get(clazz));

    builder.addPathSegment(operation.getId());
    for (Entry<String, String> e : operation.getQueryParameters().entrySet()) {
      builder.addQueryParameter(e.getKey(), e.getValue());
    }

    Request request = getRequest(builder.build());
    okhttp3.Response response = client.newCall(request).execute();

    return new Response<T>(response, mapper, clazz);
  }

  private <T> PagedResponse<T> navigateTo(Link link, Class<T> type) throws IOException {
    HttpUrl baseUrl = baseUrlBuilder().build();
    HttpUrl nextUrl = baseUrl.resolve(link.getRelativeHref());
    logger.debug("About to navigate to {}", nextUrl);
    Request request = getRequest(nextUrl);

    okhttp3.Response nextResponse = client.newCall(request).execute();

    return new PagedResponse<T>(nextResponse, mapper, type);
  }


  private Request getRequest(HttpUrl url) {
    Builder urlBuilder = url.newBuilder().addQueryParameter(API_KEY_QUERY_PARAM, apiKey);
    if (configuration.getDefaultLocale() != null && url.queryParameter("locale") == null) {
      urlBuilder.addQueryParameter("locale", configuration.getDefaultLocale());
    }

    return new Request.Builder().url(urlBuilder.build())
        .addHeader(USER_AGENT, Version.getUserAgent()).build();
  }
}
