package com.ticketmaster.api.discovery;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.ticketmaster.api.Version;
import com.ticketmaster.api.discovery.operation.ByIdOperation;
import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.operation.SearchVenuesOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.api.discovery.response.Response;
import com.ticketmaster.api.discovery.util.Preconditions;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Attractions;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.discovery.model.Page.Link;
import com.ticketmaster.discovery.model.Venue;
import com.ticketmaster.discovery.model.Venues;

public class DiscoveryApi {

  private Logger logger = LoggerFactory.getLogger(DiscoveryApi.class);

  private static final String USER_AGENT = "User-Agent";
  private final String domainName = "app.ticketmaster.com";
  private final String apiPackage = "discovery";
  private final String apiVersion = "v2";
  private final OkHttpClient client = new OkHttpClient();
  private final ObjectMapper mapper;

  @SuppressWarnings("serial")
  private final Map<String, String> pathByType = new HashMap<String, String>() {
    {
      put(Event.class.getSimpleName(), "events");
      put(Attraction.class.getSimpleName(), "attractions");
      put(Venue.class.getSimpleName(), "venues");
    }
  };

  private String apiKey = null;
  private String locale = null;

  private DiscoveryApi(DiscoveryApiBuilder builder) {
    this.apiKey = builder.apiKey;
    this.locale = builder.locale;
    this.mapper = new ObjectMapper() //
        .registerModule(new GuavaModule()) //
        .registerModule(new JodaModule()) //
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  private HttpUrl.Builder baseUrlBuilder() {
    return new HttpUrl.Builder().scheme("https").host(domainName);
  }

  private HttpUrl.Builder urlBuilder(String path) {
    return baseUrlBuilder().addPathSegment(apiPackage).addPathSegment(apiVersion)
        .addPathSegment(path);
  }

  public static DiscoveryApiBuilder builder(String apiKey) {
    return new DiscoveryApiBuilder(apiKey);
  }

  private <T> Response<T> getById(ByIdOperation operation, Class<T> clazz) throws IOException {
    logger.debug("get{} invoked with {}", clazz.getSimpleName(), operation);
    HttpUrl.Builder builder = urlBuilder(pathByType(clazz.getSimpleName()));

    builder.addPathSegment(operation.getId());
    for (Entry<String, String> e : operation.getQueryParameters().entrySet()) {
      builder.addQueryParameter(e.getKey(), e.getValue());
    }

    Request request = getRequest(builder.build());
    okhttp3.Response response = client.newCall(request).execute();

    return new Response<T>(response, mapper, clazz);
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

    HttpUrl.Builder builder = urlBuilder(pathByType.get(Event.class.getSimpleName()));
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

    HttpUrl.Builder builder = urlBuilder(pathByType.get(Attraction.class.getSimpleName()));
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

    HttpUrl.Builder builder = urlBuilder(pathByType.get(Venue.class.getSimpleName()));
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

  private String pathByType(String simpleName) {
    if (Event.class.getSimpleName().equals(simpleName)) {
      return "events";
    }
    if (Attraction.class.getSimpleName().equals(simpleName)) {
      return "attractions";
    }
    if (Venue.class.getSimpleName().equals(simpleName)) {
      return "venues";
    }
    throw new IllegalArgumentException(String.format("Unsupported type %s", simpleName));
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
    HttpUrl.Builder urlBuilder = url.newBuilder().addQueryParameter("apikey", apiKey);
    if (locale != null && url.queryParameter("locale") == null) {
      urlBuilder.addQueryParameter("locale", locale);
    }

    return new Request.Builder().url(urlBuilder.build())
        .addHeader(USER_AGENT, Version.getUserAgent()).build();
  }

  public static class DiscoveryApiBuilder {

    private String apiKey;
    private String locale;

    public DiscoveryApiBuilder(String apiKey) {
      Preconditions.checkNotNull(apiKey, "The API key is mandatory");
      this.apiKey = apiKey;
    }

    public DiscoveryApiBuilder defaultLocale(String locale) {
      Preconditions.checkNotNull(locale);
      // TODO: Validate the locale is allowed
      this.locale = locale;
      return this;
    }

    public DiscoveryApi build() {
      return new DiscoveryApi(this);
    }

  }
}
