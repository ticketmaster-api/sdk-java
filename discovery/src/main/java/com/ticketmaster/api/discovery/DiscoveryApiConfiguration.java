package com.ticketmaster.api.discovery;

import static com.ticketmaster.api.discovery.util.Preconditions.checkArgument;
import static com.ticketmaster.api.discovery.util.Preconditions.checkIllegalCharacters;
import static com.ticketmaster.api.discovery.util.Preconditions.checkNotEmpty;
import static com.ticketmaster.api.discovery.util.Preconditions.checkNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import com.ticketmaster.api.discovery.util.Preconditions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class DiscoveryApiConfiguration {

  private String protocol = "https";
  private String domainName = "app.ticketmaster.com";
  private Integer port = null;
  private String apiPackage = "discovery";
  private String apiVersion = "v2";
  private String defaultLocale = null;

  public static DiscoveryApiConfigurationBuilder builder() {
    return new DiscoveryApiConfiguration().new DiscoveryApiConfigurationBuilder();
  }

  private DiscoveryApiConfiguration() {}

  public int getPort() {
    return port.intValue();
  }

  public boolean isPortSet() {
    return port != null;
  }

  public class DiscoveryApiConfigurationBuilder {

    public DiscoveryApiConfigurationBuilder proxyHost(String host) {
      checkNotEmpty(host, "proxyHost must be neither null nor empty.");
      checkIllegalCharacters(host, ":/"); // Not complete but sufficient.
      domainName = host;
      return this;
    }

    public DiscoveryApiConfigurationBuilder proxyPort(int proxyPort) {
      checkArgument(proxyPort > 0 && proxyPort < 0xFFFF, "proxyPort must be between 0 and 65535.");
      port = Integer.valueOf(proxyPort);
      return this;
    }

    public DiscoveryApiConfigurationBuilder proxyProtocol(String pxyProtocol) {
      checkNotNull(pxyProtocol, "proxyProtocol must not be null.");
      checkArgument(pxyProtocol.matches("^https?$"), "proxyProtocol supports: http, https.");
      protocol = pxyProtocol;
      return this;
    }

    public DiscoveryApiConfigurationBuilder defaultLocale(String locale) {
      checkNotNull(locale);
      // TODO: Validate the locale is allowed
      defaultLocale = locale;
      return this;
    }

    public DiscoveryApiConfiguration build() {
      return DiscoveryApiConfiguration.this;
    }
  }
}
