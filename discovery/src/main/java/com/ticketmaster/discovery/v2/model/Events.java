package com.ticketmaster.discovery.v2.model;

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
public class Events {

     private List<Event> events;

}
