package com.ticketmaster.discovery.v2;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.net.HttpHeaders;
import com.ticketmaster.Version;
import com.ticketmaster.discovery.model.Link;
import com.ticketmaster.discovery.model.PagedResponse;
import com.ticketmaster.discovery.model.Response;
import com.ticketmaster.discovery.v2.model.Attraction;
import com.ticketmaster.discovery.v2.model.Attractions;
import com.ticketmaster.discovery.v2.model.Event;
import com.ticketmaster.discovery.v2.model.Events;
import com.ticketmaster.discovery.v2.model.Venue;
import com.ticketmaster.discovery.v2.model.Venues;
import com.ticketmaster.discovery.v2.operation.ByIdOperation;
import com.ticketmaster.discovery.v2.operation.SearchAttractionsOperation;
import com.ticketmaster.discovery.v2.operation.SearchEventsOperation;
import com.ticketmaster.discovery.v2.operation.SearchVenuesOperation;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DiscoveryApi {

    private Logger logger = LoggerFactory.getLogger(DiscoveryApi.class);

    private final String domainName = "app.ticketmaster.com";
    private final String apiPackage = "discovery";
    private final String apiVersion = "v2";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper;

    private final ImmutableMap<String, String> pathByType = ImmutableMap.of(Event.class.getSimpleName(), "events", Attraction.class.getSimpleName(),
            "attractions", Venue.class.getSimpleName(), "venues");

    private String apiKey = null;
    private String locale = null;

    private DiscoveryApi(DiscoveryApiBuilder builder) {
        this.apiKey = builder.apiKey;
        this.locale = builder.locale;
        this.mapper = new ObjectMapper().registerModule(new GuavaModule()).registerModule(new JodaModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private HttpUrl.Builder baseUrlBuilder() {
        return new HttpUrl.Builder().scheme("https").host(domainName);
    }

    private HttpUrl.Builder urlBuilder(String path) {
        return baseUrlBuilder().addPathSegment(apiPackage).addPathSegment(apiVersion).addPathSegment(path);
    }

    public static DiscoveryApiBuilder builder(String apiKey) {
        return new DiscoveryApiBuilder(apiKey);
    }

    private <T> Response<T> getById(ByIdOperation operation, Class<T> clazz) throws IOException {
        logger.debug("get{} invoked with {}", clazz.getSimpleName(), operation);
        HttpUrl.Builder builder = urlBuilder(pathByType.get(clazz.getSimpleName()));

        builder.addPathSegment(operation.getId());
        operation.getQueryParameters().entrySet().stream().forEach(e -> builder.addQueryParameter(e.getKey(), e.getValue()));

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
        operation.getQueryParameters().entrySet().stream().forEach(e -> builder.addQueryParameter(e.getKey(), e.getValue()));

        logger.debug("searchEvents about to load {}", builder.build());
        Request request = getRequest(builder.build());
        okhttp3.Response response = client.newCall(request).execute();

        return new PagedResponse<Events>(response, mapper, Events.class);
    }

    public PagedResponse<Attractions> searchAttractions(SearchAttractionsOperation operation) throws IOException {
        logger.debug("searchAttractions invoked with {}", operation);
        HttpUrl.Builder builder = urlBuilder(pathByType.get(Attraction.class.getSimpleName()));
        operation.getQueryParameters().entrySet().stream().forEach(e -> builder.addQueryParameter(e.getKey(), e.getValue()));

        logger.debug("searchEvents about to load {}", builder.build());
        Request request = getRequest(builder.build());
        okhttp3.Response response = client.newCall(request).execute();

        return new PagedResponse<Attractions>(response, mapper, Attractions.class);
    }

    public PagedResponse<Venues> searchVenues(SearchVenuesOperation operation) throws IOException {
        logger.debug("searchVenues invoked with {}", operation);
        HttpUrl.Builder builder = urlBuilder(pathByType.get(Venue.class.getSimpleName()));
        operation.getQueryParameters().entrySet().stream().forEach(e -> builder.addQueryParameter(e.getKey(), e.getValue()));

        logger.debug("searchEvents about to load {}", builder.build());
        Request request = getRequest(builder.build());
        okhttp3.Response response = client.newCall(request).execute();

        return new PagedResponse<Venues>(response, mapper, Venues.class);
    }

    public <T> PagedResponse<T> nextPage(PagedResponse<T> response) throws IOException {
        Link next = response.getNextPageLink();
        HttpUrl baseUrl = baseUrlBuilder().build();
        HttpUrl nextUrl = baseUrl.resolve(next.getRelativeHref());
        logger.debug("nextPage about to navigate to {}", nextUrl);
        Request request = getRequest(nextUrl);

        okhttp3.Response nextResponse = client.newCall(request).execute();

        return new PagedResponse<T>(nextResponse, mapper, response.getType());
    }

    public <T> PagedResponse<T> previousPage(PagedResponse<T> response) throws IOException {
        Link previous = response.getPreviousPageLink();
        HttpUrl baseUrl = baseUrlBuilder().build();
        HttpUrl previousUrl = baseUrl.resolve(previous.getRelativeHref());
        logger.debug("previousPage about to navigate to {}", previousUrl);
        Request request = getRequest(previousUrl);

        okhttp3.Response nextResponse = client.newCall(request).execute();

        return new PagedResponse<T>(nextResponse, mapper, response.getType());
    }

    private Request getRequest(HttpUrl url) {
        HttpUrl.Builder urlBuilder = url.newBuilder().addQueryParameter("apikey", apiKey);
        if (locale != null && url.queryParameter("locale") == null) {
            urlBuilder.addQueryParameter("locale", locale);
        }

        return new Request.Builder().url(urlBuilder.build()).addHeader(HttpHeaders.USER_AGENT, Version.getUserAgent()).build();
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
