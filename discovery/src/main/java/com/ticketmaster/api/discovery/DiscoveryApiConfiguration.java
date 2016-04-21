package com.ticketmaster.api.discovery;

import static com.ticketmaster.api.discovery.util.Preconditions.checkArgument;
import static com.ticketmaster.api.discovery.util.Preconditions.checkIllegalCharacters;
import static com.ticketmaster.api.discovery.util.Preconditions.checkNotEmpty;
import static com.ticketmaster.api.discovery.util.Preconditions.checkNotNull;

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

    public DiscoveryApiConfigurationBuilder host(String host) {
      checkNotEmpty(host, "host must be neither null nor empty.");
      checkIllegalCharacters(host, ":/"); // Not complete but sufficient.
      domainName = host;
      return this;
    }

    public DiscoveryApiConfigurationBuilder port(int newPort) {
      checkArgument(newPort > 0 && newPort < 0xFFFF, "port must be between 0 and 65535.");
      port = Integer.valueOf(newPort);
      return this;
    }

    public DiscoveryApiConfigurationBuilder protocol(String newProtocol) {
      checkNotNull(newProtocol, "protocol must not be null.");
      checkArgument(newProtocol.matches("^https?$"), "Protocols supported: http, https.");
      protocol = newProtocol;
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
