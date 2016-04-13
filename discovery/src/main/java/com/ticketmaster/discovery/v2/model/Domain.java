package com.ticketmaster.discovery.v2.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum Domain {
    TICKETMASTERCOM(1, "ticketmaster.com"),
    TICKETMASTERCA(2, "ticketmaster.ca"),
    TICKETMASTERMX(5, "ticketmaster.mx"),
    TICKETMASTERAU(6, "ticketmaster.au"),
    TICKETMASTERNZ(7, "ticketmaster.nz"),
    LIVENATIONCOM(12, "livenation.com");

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

    public static Optional<Domain> findById(Integer domainId) {
        return findDomain(x -> x.domainId.equals(domainId));
    }

    public static Optional<Domain> findByName(String domainName) {
        return findDomain(x -> x.domainName.equalsIgnoreCase(domainName));
    }

    private static Optional<Domain> findDomain(Predicate<Domain> domainPredicate) {
        return Arrays.asList(Domain.values())
                .stream()
                .filter(domainPredicate)
                .findFirst();
    }

}