package com.ticketmaster.api.discovery;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.ticketmaster.api.discovery.util.Preconditions.*;

@Getter
@ToString
@EqualsAndHashCode
public class DiscoveryApiConfiguration {

  private static final int DEFAULT_SOCKET_CONNECT_TIMEOUT_MS = 500;
  private static final int DEFAULT_SOCKET_TIMEOUT_MS = 6000;

  public enum PathModifier {
    LEGACY("legacy"),
    TICKETMASTER_US("ticketmaster-us"),
    TICKETMASTER_UK("ticketmaster-uk"),
    UNIVERSE("universe"),
    FRONTGATE("frontgate"),
    TMR("tmr"),
    NONE("");

    private String modifier;

    PathModifier(String pathModifier) {
      this.modifier = pathModifier;
    }

    public String getModifier() {
      return modifier;
    }

    public static PathModifier fromString(String value) {
      PathModifier pathModifier = null;

      if (value != null) {
        pathModifier = PathModifier.valueOf(value.toUpperCase());
      }

      return pathModifier;
    }

  }

  private String protocol = "https";
  private String domainName = "app.ticketmaster.com";
  private Integer port = null;
  private String apiPackage = "discovery";
  private String apiVersion = "v2";
  private String defaultLocale = null;
  private PathModifier pathModifier = PathModifier.NONE;
  private int socketConnectTimeout = DEFAULT_SOCKET_CONNECT_TIMEOUT_MS;
  private int socketTimeout = DEFAULT_SOCKET_TIMEOUT_MS;
  private String apiKeyQueryParam ="apikey";

  private DiscoveryApiConfiguration(String protocol,
                                    String domainName,
                                    Integer port,
                                    String apiPackage,
                                    String apiVersion,
                                    String defaultLocale,
                                    PathModifier pathModifier,
                                    int socketConnectTimeout,
                                    int socketTimeout,
                                    String apiKeyField) {
    this.protocol = protocol;
    this.domainName = domainName;
    this.port = port;
    this.apiPackage = apiPackage;
    this.apiVersion = apiVersion;
    this.defaultLocale = defaultLocale;
    this.pathModifier = pathModifier;
    this.socketConnectTimeout = socketConnectTimeout;
    this.socketTimeout = socketTimeout;
    this.apiKeyQueryParam = apiKeyField;
  }

  public static DiscoveryApiConfigurationBuilder builder() {
    return new DiscoveryApiConfigurationBuilder();
  }

  public int getPort() {
    return port.intValue();
  }

  public boolean isPortSet() {
    return port != null;
  }

  public static class DiscoveryApiConfigurationBuilder {
    private String protocol = "https";
    private String domainName = "app.ticketmaster.com";
    private Integer port = null;
    private String apiPackage = "discovery";
    private String apiVersion = "v2";
    private String defaultLocale = null;
    private PathModifier pathModifier = PathModifier.NONE;
    private int socketConnectTimeout = DEFAULT_SOCKET_CONNECT_TIMEOUT_MS;
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT_MS;
    private String apiKeyQueryParam="apikey";

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

    public DiscoveryApiConfigurationBuilder pathModifier(String newPathModifier) {
      checkNotNull(newPathModifier);
      return pathModifier( PathModifier.fromString(newPathModifier) );
    }

    public DiscoveryApiConfigurationBuilder pathModifier(PathModifier newPathModifier) {
      pathModifier = newPathModifier == null ? PathModifier.NONE : newPathModifier;
      return this;
    }

    public DiscoveryApiConfigurationBuilder socketConnectTimeout(int timeout) {
      socketConnectTimeout = timeout;
      return this;
    }

    public DiscoveryApiConfigurationBuilder socketTimeout(int timeout) {
      socketTimeout = timeout;
      return this;
    }

    public DiscoveryApiConfigurationBuilder apiKeyQueryParam(String field){
      apiKeyQueryParam=field;
      return this;
    }

    public DiscoveryApiConfiguration build() {
      return new DiscoveryApiConfiguration(
              protocol,
              domainName,
              port,
              apiPackage,
              apiVersion,
              defaultLocale,
              pathModifier,
              socketConnectTimeout,
              socketTimeout,
              apiKeyQueryParam);
    }
  }
}
