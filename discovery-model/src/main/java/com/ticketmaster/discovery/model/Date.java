package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Date extends BaseModel {

  private Start start;
  private End end;
  private String timezone;
  private DisplayOptions displayOptions;
  private Status status;
  private AccessDates access;

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Start extends BaseModel {

    private DateTime dateTime;
    private String localDate;
    private String localTime;
    private Boolean dateTBD;
    private Boolean dateTBA;
    private Boolean timeTBA;
    private Boolean noSpecificTime;
  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class End extends BaseModel {

    private DateTime dateTime;
    private String localDate;
    private String localTime;
    private Boolean approximate;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class DisplayOptions extends BaseModel {

    private Range range;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Range extends BaseModel {

    private String localStartDate;
    private String localEndDate;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Status extends BaseModel {

    private EventStatusEnum code;

  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public final static class AccessDates extends BaseModel {

    private DateTime startDateTime;
    private Boolean startApproximate;
    private DateTime endDateTime;
    private Boolean endApproximate;

  }
}
