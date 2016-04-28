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


@ToString
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
  private Country country;
  private State state;
  private City city;
  private Location location;
  private String postalCode;
  private Address address;
  private String timezone;
  private Boolean test;

  @ToString
  @Getter
  @Setter
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Location {

    private String latitude;

    private String longitude;

  }

  @ToString
  @Getter
  @Setter
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Country {

    private String name;
    private String countryCode;

  }

  @ToString
  @Getter
  @Setter
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class State {

    private String name;
    private String stateCode;

  }

  @ToString
  @Getter
  @Setter
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class City {

    private String name;

  }

  @ToString
  @Getter
  @Setter
  @EqualsAndHashCode
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Address {

    private String line1;

    private String line2;

  }
}
