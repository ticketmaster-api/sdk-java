package com.ticketmaster.discovery.model;


public enum Domain {
  TICKETMASTERCOM(1, "ticketmaster.com"), TICKETMASTERCA(2, "ticketmaster.ca"), TICKETMASTERMX(5,
      "ticketmaster.mx"), TICKETMASTERAU(6, "ticketmaster.au"), TICKETMASTERNZ(7, "ticketmaster.nz"), LIVENATIONCOM(
      12, "livenation.com");

  private Integer domainId;
  private String domainName;

  Domain(Integer domainId, String domainName) {

    this.domainId = domainId;
    this.domainName = domainName;
  }

  public int getDomainId() {
    return domainId;
  }

  public String getDomainName() {
    return domainName;
  }

  public static Domain findById(final Integer domainId) {
    for (Domain d : Domain.values()) {
      if (d.domainId.equals(domainId)) {
        return d;
      }
    }

    return null;
  }

  public static Domain findByName(final String domainName) {
    for (Domain d : Domain.values()) {
      if (d.domainName.equals(domainName)) {
        return d;
      }
    }

    return null;
  }
}
