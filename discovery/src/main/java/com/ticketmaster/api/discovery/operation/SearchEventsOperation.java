package com.ticketmaster.api.discovery.operation;

import java.util.Arrays;
import java.util.List;

import com.ticketmaster.api.discovery.util.Preconditions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchEventsOperation extends PagedOperation<SearchEventsOperation> {

  private static final List<String> UNITS = Arrays.asList("miles", "km");

  public SearchEventsOperation keyword(String keyword) {
    return withParam("keyword", keyword);
  }

  public SearchEventsOperation attractionId(String attractionId) {
    return withParam("attractionId", attractionId);
  }

  public SearchEventsOperation attractionId(String... attractionIds) {
    return withCommaSeparatedParam("attractionId", attractionIds);
  }

  public SearchEventsOperation venueId(String venueId) {
    return withParam("venueId", venueId);
  }

  public SearchEventsOperation venueId(String... venueIds) {
    return withCommaSeparatedParam("venueId", venueIds);
  }

  public SearchEventsOperation promoterId(String promoterId) {
    return withParam("promoterId", promoterId);
  }

  public SearchEventsOperation promoterId(String... promoterIds) {
    return withCommaSeparatedParam("promoterId", promoterIds);
  }

  public SearchEventsOperation postalCode(String postalCode) {
    return withParam("postalCode", postalCode);
  }

  public SearchEventsOperation latlong(String latitude, String longitude) {
    return withParam("latlong", String.format("%s,%s", latitude, longitude));
  }

  public SearchEventsOperation radius(Integer radius) {
    return withParam("radius", radius);
  }

  public SearchEventsOperation unit(String unit) {
    Preconditions.checkArgument(UNITS.contains(unit),
            String.format("Invalid unit, supported units: %s", UNITS.toString()));
    return withParam("unit", unit);
  }

  public SearchEventsOperation marketId(String marketId) {
    return withParam("marketId", marketId);
  }

  public SearchEventsOperation marketIds(String... marketIds) {
    return withCommaSeparatedParam("marketId", marketIds);
  }

  public SearchEventsOperation endDateTime(String endDateTime) {
    return withParam("endDateTime", endDateTime);
  }

  public SearchEventsOperation startDateTime(String startDateTime) {
    return withParam("startDateTime", startDateTime);
  }

  public SearchEventsOperation city(String city) {
    return withParam("city", city);
  }

  public SearchEventsOperation countryCode(String countryCode) {
    return withParam("countryCode", countryCode);
  }

  public SearchEventsOperation countryCode(String... countryCodes) {
    return withCommaSeparatedParam("countryCode", countryCodes);
  }

  public SearchEventsOperation stateCode(String stateCode) {
    return withParam("stateCode", stateCode);
  }

  public SearchEventsOperation stateCode(String... stateCodes) {
    return withCommaSeparatedParam("stateCode", stateCodes);
  }

  public SearchEventsOperation dmaId(String dmaId) {
    return withParam("dmaId", dmaId);
  }

  public SearchEventsOperation dmaId(String... dmaIds) {
    return withCommaSeparatedParam("dmaIds", dmaIds);
  }

  public SearchEventsOperation includeTBA(String includeTBA) {
    return withParam("includeTBA", includeTBA);
  }

  public SearchEventsOperation includeTBD(String includeTBD) {
    return withParam("includeTBD", includeTBD);
  }

  public SearchEventsOperation sort(String sort) {
    return withParam("sort", sort);
  }

  public SearchEventsOperation onsaleEndDateTime(String onsaleEndDateTime) {
    return withParam("onsaleEndDateTime", onsaleEndDateTime);
  }

  public SearchEventsOperation onsaleStartDateTime(String onsaleStartDateTime) {
    return withParam("onsaleStartDateTime", onsaleStartDateTime);
  }

  @Override
  protected SearchEventsOperation getThis() {
    return this;
  }

}
