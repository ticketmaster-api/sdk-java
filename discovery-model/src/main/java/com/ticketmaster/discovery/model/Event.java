package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {"type", "_links"})
public class Event extends ResourceSupport {

  private String id;
  private String name;
  private String locale;
  private String eventUrl;
  private Promoter promoter;
  private List<Image> images;
  private List<Classification> classifications;
  private DiscoveryDate dates;
  private Boolean test;
  private DiscoverySales sales;
  private String groupId;

  public List<Venue> getVenues() {
    return embedded != null ? embedded.getVenues() : null;
  }

  public List<Attraction> getAttractions() {
    return embedded != null ? embedded.getAttractions() : null;
  }

  public List<Category> getCategories() {
    return embedded != null ? embedded.getCategories() : null;
  }
}
