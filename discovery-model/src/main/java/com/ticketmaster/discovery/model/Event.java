package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {"type", "_links"})
public class Event extends ResourceSupport {

  private String id;
  private String name;
  private String description;
  private String locale;
  private String url;
  private Promoter promoter;
  private List<Image> images;
  private List<Classification> classifications;
  private Date dates;
  private Boolean test;
  private Sale sales;
  private String groupId;
  private String info;
  private String pleaseNote;
  private Place place;
  private Set<PriceRange> priceRanges;
  private Source source;

  public List<Venue> getVenues() {
    return embedded != null ? embedded.getVenues() : null;
  }

  public List<Attraction> getAttractions() {
    return embedded != null ? embedded.getAttractions() : null;
  }

  public List<Category> getCategories() {
    return embedded != null ? embedded.getCategories() : null;
  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class PriceRange extends BaseModel {

    private String currency;

    private Double min;

    private Double max;

    private String type;

  }
  
}
