package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Sale extends BaseModel {

  @JsonProperty("public")
  private Public publicSale;

  private Set<Presale> presales;

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Public extends BaseModel {

    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private Boolean startTBD;
  }

  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Presale extends BaseModel {

    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private String name;
  }
}
