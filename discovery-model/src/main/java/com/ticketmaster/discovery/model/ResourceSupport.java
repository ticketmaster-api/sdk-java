package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class ResourceSupport extends BaseModel {

  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  @JsonProperty("_embedded")
  protected Embedded embedded;

  @Getter
  @Setter
  @ToString
  @EqualsAndHashCode
  @NoArgsConstructor
  protected static class Embedded {
    private List<Venue> venues;
    private List<Attraction> attractions;
    private List<Category> categories;
  }

}
