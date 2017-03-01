package com.ticketmaster.api.discovery;

import org.junit.Before;
import org.junit.Test;

import com.ticketmaster.api.discovery.DiscoveryApiConfiguration.DiscoveryApiConfigurationBuilder;

public class DiscoveryApiConfigurationTest {

  private DiscoveryApiConfigurationBuilder builder;

  public DiscoveryApiConfigurationAssert assertThat(DiscoveryApiConfiguration actual) {
    return new DiscoveryApiConfigurationAssert(actual);
  }

  @Before
  public void init(){
    builder = DiscoveryApiConfiguration.builder();
  }

  @Test
  public void shouldYieldDefaultUrlValuesWhenProxyUrlIsNotSet() {
    DiscoveryApiConfiguration config = builder.build();
    assertThat(config).hasProtocol("https").hasDomainName("app.ticketmaster.com").doesNotHavePort();
  }

  @Test
  public void shouldChangeDomainWhenGivenANewHost() {
    DiscoveryApiConfiguration config = builder.host("localhost").build();
    assertThat(config).hasProtocol("https").hasDomainName("localhost").doesNotHavePort();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectHostWhenMoreThanBasePathIsProvided() {
    builder.host("localhost/pathOnProxy");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectHostIfProtocolIsProvided() {
    builder.host("http://localhost");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectHostIfEmptyIsProvided() {
    builder.host("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectProxyBaseUrlWhenNotSupportedProtocol() {
    builder.host("ftp://localhost");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectPortIfBelowAllowedRange() {
    builder.port(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectPortIfAboveAllowedRange() {
    builder.port(65536);
  }

  @Test
  public void shouldAssignPortWhenInRange() {
    DiscoveryApiConfiguration config = builder.port(125).build();
    assertThat(config).hasProtocol("https").hasDomainName("app.ticketmaster.com").hasPort(125);
  }

  @Test
  public void shouldAssignProtocolWhenGivenHttp() {
    DiscoveryApiConfiguration config = builder.protocol("http").build();
    assertThat(config).hasProtocol("http").hasDomainName("app.ticketmaster.com").doesNotHavePort();
  }

  @Test
  public void shouldAssignProtocolWhenGivenHttps() {
    DiscoveryApiConfiguration config = builder.protocol("https").build();
    assertThat(config).hasProtocol("https").hasDomainName("app.ticketmaster.com").doesNotHavePort();
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectIfMoreThanProtocolIsGiven() {
    builder.protocol("https://localhost");
  }

  @Test
  public void testApiKeyQueryParam(){
    assertThat(builder.apiKeyQueryParam("apiKeyParam").build())
            .apiKeyQueryParamEquals("apiKeyParam");
  }

  @Test
  public void testApiKeyQueryParamDefault(){
    assertThat(builder.build())
            .apiKeyQueryParamEquals("apikey");
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldRejectIfMoreThanProtocolIsGivenBeforeActualValue() {
    builder.protocol("nohttp");
  }

}
