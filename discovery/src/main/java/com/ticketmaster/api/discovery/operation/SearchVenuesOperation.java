package com.ticketmaster.api.discovery.operation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchVenuesOperation extends PagedOperation<SearchVenuesOperation> {

  public SearchVenuesOperation keyword(String keyword) {
    return with("keyword", keyword);
  }
  
  public SearchVenuesOperation venueId(String venueId) {
    return with("venueId", venueId);
  }

  @Override
  protected SearchVenuesOperation getThis() {
    return this;
  }
}
