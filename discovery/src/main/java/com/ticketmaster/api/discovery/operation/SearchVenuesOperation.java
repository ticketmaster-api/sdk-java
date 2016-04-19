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
  
  @Override
  protected SearchVenuesOperation getThis() {
    return this;
  }
}
