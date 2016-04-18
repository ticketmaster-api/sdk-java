package com.ticketmaster.api.discovery.operation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchAttractionsOperation extends PagedOperation<SearchAttractionsOperation> {

    public SearchAttractionsOperation keyword(String keyword) {
      return with("keyword", keyword);
    }

    public SearchAttractionsOperation attractionId(String attractionId) {
      return with("attractionId", attractionId);
    }

    @Override
    protected SearchAttractionsOperation getThis() {
      return this;
    }
}
