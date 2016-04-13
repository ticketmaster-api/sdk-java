package com.ticketmaster.discovery.v2.model;

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
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = { "type", "_links"})
public class Attraction extends ResourceSupport {

    private String id;
    private String url;
    private Image image;
    private String name;
    private String description;
    private String locale;
    private Boolean test;
    private List<Classification> classifications;
    private List<Image> images;

}
