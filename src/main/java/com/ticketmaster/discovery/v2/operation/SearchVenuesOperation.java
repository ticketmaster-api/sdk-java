package com.ticketmaster.discovery.v2.operation;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Joiner;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class SearchVenuesOperation extends Operation {

    private SearchVenuesOperation(SearchVenuesOperationBuilder builder) {
        ofNullable(builder.keyword).ifPresent(e -> this.queryParams.put("keyword", e));
        ofNullable(builder.locale).ifPresent(e -> this.queryParams.put("locale", e));
        ofNullable(builder.venueIds).ifPresent(e -> this.queryParams.put("venueId", Joiner.on(",").skipNulls().join(e)));
        ofNullable(builder.sort).ifPresent(e -> this.queryParams.put("sort", e));
        ofNullable(builder.pageSize).ifPresent(e -> this.queryParams.put("size", Integer.toString(e)));
        ofNullable(builder.pageNumber).ifPresent(e -> this.queryParams.put("page", Integer.toString(e)));
    }

    public static SearchVenuesOperationBuilder builder() {
        return new SearchVenuesOperationBuilder();
    }

    public static class SearchVenuesOperationBuilder {
        private String keyword;
        private ArrayList<String> venueIds;
        private Integer pageSize;
        private Integer pageNumber;
        private String sort;
        private String locale;

        SearchVenuesOperationBuilder() {
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder venueId(String venueId) {
            if (this.venueIds == null)
                this.venueIds = new ArrayList<String>();
            this.venueIds.add(venueId);
            return this;
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder venueIds(Collection<String> venueIds) {
            if (this.venueIds == null)
                this.venueIds = new ArrayList<String>();
            this.venueIds.addAll(venueIds);
            return this;
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder pageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public SearchVenuesOperation.SearchVenuesOperationBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public SearchVenuesOperation build() {
            return new SearchVenuesOperation(this);
        }
    }
}