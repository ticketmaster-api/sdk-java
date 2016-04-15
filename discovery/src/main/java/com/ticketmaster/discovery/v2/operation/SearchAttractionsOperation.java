package com.ticketmaster.discovery.v2.operation;

import java.util.ArrayList;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.google.common.base.Joiner;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchAttractionsOperation extends Operation {

  private SearchAttractionsOperation(SearchAttractionsOperationBuilder builder) {
    // TODO: That boilerplate code could be improved using annotations
    if (builder.keyword != null && !builder.keyword.isEmpty()) {
      this.queryParams.put("keyword", builder.keyword);
    }
    if (builder.locale != null && !builder.locale.isEmpty()) {
      this.queryParams.put("locale", builder.locale);
    }
    if (builder.attractionIds != null && !builder.attractionIds.isEmpty()) {
      this.queryParams.put("attractionId", Joiner.on(",").skipNulls().join(builder.attractionIds));
    }
    if (builder.sort != null && !builder.sort.isEmpty()) {
      this.queryParams.put("sort", builder.sort);
    }
    if (builder.pageSize != null) {
      this.queryParams.put("size", Integer.toString(builder.pageSize));
    }
    if (builder.pageNumber != null) {
      this.queryParams.put("page", Integer.toString(builder.pageNumber));
    }
  }

  public static SearchAttractionsOperationBuilder builder() {
    return new SearchAttractionsOperationBuilder();
  }

  public static class SearchAttractionsOperationBuilder {
    private String keyword;
    private ArrayList<String> attractionIds;
    private Integer pageSize;
    private Integer pageNumber;
    private String sort;
    private String locale;

    SearchAttractionsOperationBuilder() {}

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder keyword(String keyword) {
      this.keyword = keyword;
      return this;
    }

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder attractionId(
        String attractionId) {
      if (this.attractionIds == null)
        this.attractionIds = new ArrayList<String>();
      this.attractionIds.add(attractionId);
      return this;
    }

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder attractionIds(
        Collection<String> attractionIds) {
      if (this.attractionIds == null)
        this.attractionIds = new ArrayList<String>();
      this.attractionIds.addAll(attractionIds);
      return this;
    }

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder pageSize(Integer pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder pageNumber(
        Integer pageNumber) {
      this.pageNumber = pageNumber;
      return this;
    }

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder sort(String sort) {
      this.sort = sort;
      return this;
    }

    public SearchAttractionsOperation.SearchAttractionsOperationBuilder locale(String locale) {
      this.locale = locale;
      return this;
    }

    public SearchAttractionsOperation build() {
      return new SearchAttractionsOperation(this);
    }
  }
}
