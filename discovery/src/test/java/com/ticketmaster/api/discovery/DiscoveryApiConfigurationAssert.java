package com.ticketmaster.api.discovery;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;

public class DiscoveryApiConfigurationAssert
    extends AbstractAssert<DiscoveryApiConfigurationAssert, DiscoveryApiConfiguration> {

  protected DiscoveryApiConfigurationAssert(DiscoveryApiConfiguration actual) {
    super(actual, DiscoveryApiConfigurationAssert.class);
  }

  public DiscoveryApiConfigurationAssert hasProtocol(String expectedProtocol) {
    isNotNull();
    assertThat(actual.getProtocol()).as("Protocol").isEqualTo(expectedProtocol);
    return this;
  }

  public DiscoveryApiConfigurationAssert hasDomainName(String expectedDomainName) {
    isNotNull();
    assertThat(actual.getDomainName()).as("DomainName").isEqualTo(expectedDomainName);
    return this;
  }

  public DiscoveryApiConfigurationAssert hasApiPackage(String expectedApiPackage) {
    isNotNull();
    assertThat(actual.getApiPackage()).as("ApiPackage").isEqualTo(expectedApiPackage);
    return this;
  }

  public DiscoveryApiConfigurationAssert hasApiVersion(String expectedApiVersion) {
    isNotNull();
    assertThat(actual.getApiVersion()).as("ApiVersion").isEqualTo(expectedApiVersion);
    return this;
  }

  public DiscoveryApiConfigurationAssert hasPort(int expectedPort) {
    isNotNull();
    if (actual.isPortSet() == false) {
      failWithMessage("Expected port to be %s but was not set.", expectedPort);
    }
    assertThat(actual.getPort()).as("Port").isEqualTo(expectedPort);
    return this;
  }

  public DiscoveryApiConfigurationAssert doesNotHavePort() {
    isNotNull();
    assertThat(actual.isPortSet()).as("Port").isFalse();
    return this;
  }
}
