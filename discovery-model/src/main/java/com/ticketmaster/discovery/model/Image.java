package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Image {

    private String url;
    private String ratio;
    private Integer width;
    private Integer height;
    private Boolean fallback;

}