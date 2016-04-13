package com.ticketmaster.discovery.v2.operation;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Joiner;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper=true)
public class SearchAttractionsOperation extends Operation {

    private SearchAttractionsOperation(SearchAttractionsOperationBuilder builder) {
        ofNullable(builder.keyword).ifPresent(e -> this.queryParams.put("keyword", e));
        ofNullable(builder.locale).ifPresent(e -> this.queryParams.put("locale", e));
        ofNullable(builder.attractionIds).ifPresent(e -> this.queryParams.put("attractionId", Joiner.on(",").skipNulls().join(e)));
        ofNullable(builder.sort).ifPresent(e -> this.queryParams.put("sort", e));
        ofNullable(builder.pageSize).ifPresent(e -> this.queryParams.put("size", Integer.toString(e)));
        ofNullable(builder.pageNumber).ifPresent(e -> this.queryParams.put("page", Integer.toString(e)));
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

        SearchAttractionsOperationBuilder() {
        }

        public SearchAttractionsOperation.SearchAttractionsOperationBuilder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SearchAttractionsOperation.SearchAttractionsOperationBuilder attractionId(String attractionId) {
            if (this.attractionIds == null)
                this.attractionIds = new ArrayList<String>();
            this.attractionIds.add(attractionId);
            return this;
        }

        public SearchAttractionsOperation.SearchAttractionsOperationBuilder attractionIds(Collection<String> attractionIds) {
            if (this.attractionIds == null)
                this.attractionIds = new ArrayList<String>();
            this.attractionIds.addAll(attractionIds);
            return this;
        }

        public SearchAttractionsOperation.SearchAttractionsOperationBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public SearchAttractionsOperation.SearchAttractionsOperationBuilder pageNumber(Integer pageNumber) {
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