package com.ticketmaster.discovery.v2.operation;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Optional.ofNullable;

@ToString
@EqualsAndHashCode
public class SearchEventsOperation extends Operation {

    private static final List<String> UNITS = Arrays.asList("miles", "km");

    private SearchEventsOperation(SearchEventsOperationBuilder builder) {
        ofNullable(builder.keyword).ifPresent(e -> this.queryParams.put("keyword", e));
        ofNullable(builder.locale).ifPresent(e -> this.queryParams.put("locale", e));
        ofNullable(builder.attractionIds).ifPresent(e -> this.queryParams.put("attractionId", Joiner.on(",").skipNulls().join(e)));
        ofNullable(builder.venueIds).ifPresent(e -> this.queryParams.put("venueId", Joiner.on(",").skipNulls().join(e)));
        ofNullable(builder.promoterIds).ifPresent(e -> this.queryParams.put("promoterId", Joiner.on(",").skipNulls().join(e)));
        ofNullable(builder.marketId).ifPresent(e -> this.queryParams.put("marketId", Integer.toString(e)));
        ofNullable(builder.postalCode).ifPresent(e -> this.queryParams.put("postalCode", e));
        if (builder.latitude != null || builder.longitude != null) {
            Preconditions.checkNotNull(builder.latitude, "Latitude is required when passing longitude");
            Preconditions.checkNotNull(builder.longitude, "Longitude is required when passing latitude");
            this.queryParams.put("latlong", builder.latitude + "," + builder.longitude);
        }
        ofNullable(builder.radius).ifPresent(e -> this.queryParams.put("radius", Integer.toString(e)));
        ofNullable(builder.unit).ifPresent(e -> this.queryParams.put("unit", e));
        ofNullable(builder.pageSize).ifPresent(e -> this.queryParams.put("size", Integer.toString(e)));
        ofNullable(builder.pageNumber).ifPresent(e -> this.queryParams.put("page", Integer.toString(e)));
        ofNullable(builder.sort).ifPresent(e -> this.queryParams.put("sort", e));
    }

    public static SearchEventsOperationBuilder builder() {
        return new SearchEventsOperationBuilder();
    }


    public static class SearchEventsOperationBuilder {
        private String keyword;
        private ArrayList<String> attractionIds;
        private ArrayList<String> venueIds;
        private ArrayList<String> promoterIds;
        private String postalCode;
        private String latitude;
        private String longitude;
        private Integer radius;
        private String unit;
        private Integer pageSize;
        private Integer pageNumber;
        private String sort;
        private String locale;
        private Integer marketId;

        SearchEventsOperationBuilder() {
        }

        public SearchEventsOperation.SearchEventsOperationBuilder keyword(String keyword) {
            this.keyword = keyword;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder attractionId(String attractionId) {
            if (this.attractionIds == null) this.attractionIds = new ArrayList<String>();
            this.attractionIds.add(attractionId);
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder attractionIds(Collection<String> attractionIds) {
            if (this.attractionIds == null) this.attractionIds = new ArrayList<String>();
            this.attractionIds.addAll(attractionIds);
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder venueId(String venueId) {
            if (this.venueIds == null) this.venueIds = new ArrayList<String>();
            this.venueIds.add(venueId);
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder venueIds(Collection<String> venueIds) {
            if (this.venueIds == null) this.venueIds = new ArrayList<String>();
            this.venueIds.addAll(venueIds);
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder promoterId(String promoterId) {
            if (this.promoterIds == null) this.promoterIds = new ArrayList<String>();
            this.promoterIds.add(promoterId);
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder promoterIds(Collection<String> promoterIds) {
            if (this.promoterIds == null) this.promoterIds = new ArrayList<String>();
            this.promoterIds.addAll(promoterIds);
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder latitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder longitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder radius(Integer radius) {
            this.radius = radius;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder unit(String unit) {
            Preconditions.checkArgument(UNITS.contains(unit), "Invalid unit, supported units: %s", UNITS.toString());
            this.unit = unit;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder pageNumber(Integer pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder locale(String locale) {
            this.locale = locale;
            return this;
        }

        public SearchEventsOperation.SearchEventsOperationBuilder marketId(Integer marketId) {
            this.marketId = marketId;
            return this;
        }

        public SearchEventsOperation build() {
            return new SearchEventsOperation(this);
        }
    }
}