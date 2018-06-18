package com.ticketmaster.api;

public class Version {

  public static final String SDK_VERSION = "0.1.7";

  public static final String getUserAgent() {
    return "Ticketmaster Discovery Java SDK/" + SDK_VERSION;
  }

  private Version() {

  }

}
