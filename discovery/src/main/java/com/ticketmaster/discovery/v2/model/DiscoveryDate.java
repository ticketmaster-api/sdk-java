package com.ticketmaster.discovery.v2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DiscoveryDate {

    private Start start;
    private End end;
    private String timezone;
    private DisplayOptions displayOptions;
    private Status status;
    private AccessDates access;

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Start {

        private DateTime dateTime;
        private String localDate;
        private String localTime;
        private Boolean dateTBD;
        private Boolean dateTBA;
        private Boolean timeTBA;
        private Boolean noSpecificTime;
    }

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class End {

        private DateTime dateTime;
        private String localDate;
        private String localTime;

    }

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class DisplayOptions {

        private Range range;

    }

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Range {

        private String localStartDate;
        private String localEndDate;

    }

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Status {

        private EventStatusEnum code;

    }

    @ToString
    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public final static class AccessDates {

        private DateTime startDateTime;
        private Boolean startApproximate;
        private DateTime endDateTime;
        private Boolean endApproximate;

    }
}