package com.ticketmaster.api.discovery.operation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchVenuesOperation extends PagedOperation<SearchVenuesOperation> {

  public SearchVenuesOperation keyword(String keyword) {
    return withParam("keyword", keyword);
  }

  public SearchVenuesOperation venueId(String venueId) {
    return withParam("venueId", venueId);
  }

  public SearchVenuesOperation venueId(String... venueIds) {
    return withCommaSeparatedParam("venueId", venueIds);
  }

  public SearchVenuesOperation sort(String sort) {
    return withParam("sort", sort);
  }

  public SearchVenuesOperation stateCode(String stateCode) {
    return withParam("stateCode", stateCode);
  }

  public SearchVenuesOperation stateCode(String... stateCode) {
    return withCommaSeparatedParam("stateCode", stateCode);
  }

  public SearchVenuesOperation countryCode(String countryCode) {
    return withParam("countryCode", countryCode);
  }

  public SearchVenuesOperation countryCode(String... countryCode) {
    return withCommaSeparatedParam("countryCode", countryCode);
  }

  @Override
  protected SearchVenuesOperation getThis() {
    return this;
  }
}
