package com.ticketmaster.discovery.v2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.nullToEmpty;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Coordinate {
    private String latitude;
    private String longitude;

    public static Coordinate from(String latLong) {
        List<String> coordinates = Splitter.on(",").splitToList(nullToEmpty(latLong).trim());

        //noinspection ConstantConditions
        String latitude = emptyToNull(Iterables.get(coordinates, 0, "").trim());
        //noinspection ConstantConditions
        String longitude = emptyToNull(Iterables.get(coordinates, 1, "").trim());

        return new Coordinate(latitude, longitude);
    }
}