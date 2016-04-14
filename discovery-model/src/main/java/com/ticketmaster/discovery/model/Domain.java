package com.ticketmaster.discovery.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public enum Domain {
	TICKETMASTERCOM(1, "ticketmaster.com"), TICKETMASTERCA(2, "ticketmaster.ca"), TICKETMASTERMX(
			5, "ticketmaster.mx"), TICKETMASTERAU(6, "ticketmaster.au"), TICKETMASTERNZ(
			7, "ticketmaster.nz"), LIVENATIONCOM(12, "livenation.com");

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

	public static Optional<Domain> findById(final Integer domainId) {
        return findDomain(new Predicate<Domain>() {
			@Override
			public boolean test(Domain t) {

				return t.domainId.equals(domainId);
			}
		});
    }

	public static Optional<Domain> findByName(final String domainName) {
		return findDomain(new Predicate<Domain>() {
			@Override
			public boolean test(Domain t) {

				return t.domainName.equalsIgnoreCase(domainName);
			}
		});
	}

	private static Optional<Domain> findDomain(Predicate<Domain> domainPredicate) {
		return Arrays.asList(Domain.values()).stream().filter(domainPredicate)
				.findFirst();
	}

}