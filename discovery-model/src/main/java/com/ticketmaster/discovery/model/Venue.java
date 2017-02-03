package com.ticketmaster.discovery.model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {"type", "_links"})
public class Venue extends ResourceSupport {

  private String id;
  private String name;
  private String locale;
  private String url;
  private List<Market> markets;
  private List<DMA> dmas;
  private Country country;
  private State state;
  private City city;
  private Location location;
  private String postalCode;
  private Address address;
  private String timezone;
  private Boolean test;
  private Source source;
  private String accessibleSeatingDetail;
  private String parkingDetail;
  private BoxOfficeInfo boxOfficeInfo;
  private GeneralInfo generalInfo;
  private Social social;
  private Map<String, Extension> extensions = new HashMap<>();
  private Set<Relationship> relationships = new HashSet<>();
  private List<Image> images;

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Location extends BaseModel {

    private String latitude;

    private String longitude;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Country extends BaseModel {

    private String name;
    private String countryCode;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class State extends BaseModel {

    private String name;
    private String stateCode;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class City extends BaseModel {

    private String name;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Address extends BaseModel {

    private String line1;

    private String line2;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class BoxOfficeInfo extends BaseModel {

    private String acceptedPaymentDetail;

    private String phoneNumberDetail;

    private String willCallDetail;

    private String openHoursDetail;
    
  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class GeneralInfo extends BaseModel {

    private String childRule;

    private String generalRule;

    private String willCallDetail;

  }

}
