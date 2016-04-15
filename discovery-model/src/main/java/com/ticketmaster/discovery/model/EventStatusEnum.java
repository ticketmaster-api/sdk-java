package com.ticketmaster.discovery.model;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EventStatusEnum {
  ONSALE("onsale"), OFFSALE("offsale"), CANCELLED("cancelled"), POSTPONED("postponed"), RESCHEDULED(
      "rescheduled"), ACTIVE("active");

  private String status;

  EventStatusEnum(String status) {
    this.status = status;
  }

  @JsonValue
  public String getStatus() {
    return this.status;
  }
}
