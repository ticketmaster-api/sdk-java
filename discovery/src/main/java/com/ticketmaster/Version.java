package com.ticketmaster;

public class Version {

  public static final String SDK_VERSION = "0.0.1";

  public static final String getUserAgent() {
    return "Ticketmaster Java SDK/" + SDK_VERSION;
  }

  private Version() {

  }

}
