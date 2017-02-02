package com.ticketmaster.discovery.model;

import java.awt.geom.Area;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketmaster.discovery.model.Venue.Address;
import com.ticketmaster.discovery.model.Venue.City;
import com.ticketmaster.discovery.model.Venue.Country;
import com.ticketmaster.discovery.model.Venue.Location;
import com.ticketmaster.discovery.model.Venue.State;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Place extends BaseModel {

    private City city;
    private Country country;
    private Address address;
    private Location location;
    private Area area;
    private State state;
    private String postalCode;
}

