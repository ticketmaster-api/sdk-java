package com.ticketmaster.api.discovery.operation;

import java.util.Arrays;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.google.common.base.Preconditions;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SearchEventsOperation extends PagedOperation<SearchEventsOperation> {

  private static final List<String> UNITS = Arrays.asList("miles", "km");

    public SearchEventsOperation keyword(String keyword) {
      return with("keyword", keyword);
    }

    public SearchEventsOperation attractionId(String attractionId) {
      return with("attractionId", attractionId);
    }

    public SearchEventsOperation venueId(String venueId) {
      return with("venueId", venueId);
    }

    public SearchEventsOperation promoterId(String promoterId) {
      return with("promoterId", promoterId);
    }

    public SearchEventsOperation postalCode(String postalCode) {
      return with("postalCode", postalCode);
    }

    public SearchEventsOperation latlong(String latitude, String longitude) {
      return with("latlong", String.format("%s,%s", latitude, longitude));
    }

    public SearchEventsOperation radius(Integer radius) {
      return with("radius", radius);
    }

    public SearchEventsOperation unit(String unit) {
      Preconditions.checkArgument(UNITS.contains(unit), "Invalid unit, supported units: %s",
          UNITS.toString());
      return with("unit", unit);
    }

    public SearchEventsOperation marketId(Integer marketId) {
      return with("marketId", marketId);
    }

    @Override
    protected SearchEventsOperation getThis() {
      return this;
    }
}
