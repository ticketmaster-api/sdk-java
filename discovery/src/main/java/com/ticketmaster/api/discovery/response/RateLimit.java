package com.ticketmaster.api.discovery.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class RateLimit {

  private String limit;
  private String available;
  private String over;
  private String reset;

  public RateLimit(okhttp3.Response httpResponse) {
    this.limit = httpResponse.header("Rate-Limit");
    this.available = httpResponse.header("Rate-Limit-Available");
    this.over = httpResponse.header("Rate-Limit-Over");
    this.reset = httpResponse.header("Rate-Limit-Reset");
  }
}
