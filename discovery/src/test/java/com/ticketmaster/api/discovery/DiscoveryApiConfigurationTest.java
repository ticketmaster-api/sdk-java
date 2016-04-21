package com.ticketmaster.api.discovery;

import org.junit.Test;

import com.ticketmaster.api.discovery.DiscoveryApiConfiguration.DiscoveryApiConfigurationBuilder;

public class DiscoveryApiConfigurationTest {

  private DiscoveryApiConfigurationBuilder builder = DiscoveryApiConfiguration.builder();

  public DiscoveryApiConfigurationAssert assertThat(DiscoveryApiConfiguration actual) {
    return new DiscoveryApiConfigurationAssert(actual);
  }

  @Test
  public void shouldYieldDefaultUrlValuesWhenProxyUrlIsNotSet() {
    DiscoveryApiConfiguration config = builder.build();
    assertThat(config).hasProtocol("https").hasDomainName("app.ticketmaster.com").doesNotHavePort();
  }

  @Test
  public void shouldChangeDomainWhenGivenANewHost() {
    DiscoveryApiConfiguration config = builder.proxyHost("localhost").build();
    assertThat(config).hasProtocol("https").hasDomainName("localhost").doesNotHavePort();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectHostWhenMoreThanBasePathIsProvided() {
    builder.proxyHost("localhost/pathOnProxy");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectHostIfProtocolIsProvided() {
    builder.proxyHost("http://localhost");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectHostIfEmptyIsProvided() {
    builder.proxyHost("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectProxyBaseUrlWhenNotSupportedProtocol() {
    builder.proxyHost("ftp://localhost");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectPortIfBelowAllowedRange() {
    builder.proxyPort(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectPortIfAboveAllowedRange() {
    builder.proxyPort(65536);
  }

  @Test
  public void shouldAssignPortWhenInRange() {
    DiscoveryApiConfiguration config = builder.proxyPort(125).build();
    assertThat(config).hasProtocol("https").hasDomainName("app.ticketmaster.com").hasPort(125);
  }

  @Test
  public void shouldAssignProtocolWhenGivenHttp() {
    DiscoveryApiConfiguration config = builder.proxyProtocol("http").build();
    assertThat(config).hasProtocol("http").hasDomainName("app.ticketmaster.com").doesNotHavePort();
  }

  @Test
  public void shouldAssignProtocolWhenGivenHttps() {
    DiscoveryApiConfiguration config = builder.proxyProtocol("https").build();
    assertThat(config).hasProtocol("https").hasDomainName("app.ticketmaster.com").doesNotHavePort();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectIfMoreThanProtocolIsGiven() {
    builder.proxyProtocol("https://localhost");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectIfMoreThanProtocolIsGivenBeforeActualValue() {
    builder.proxyProtocol("nohttp");
  }

}
