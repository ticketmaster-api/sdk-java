package com.ticketmaster.discovery.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Link {

    private static final String TEMPLATE_PATTERN = "\\{(.*?)\\}";

    private String href;
    private String rel;
    private Boolean templated;

    public String getRelativeHref() {
        if (templated) {
            return href.replaceAll(TEMPLATE_PATTERN, "");
        } else {
            return href;
        }
    }
}
