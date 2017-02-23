package com.ticketmaster.api.discovery.response;

import java.io.IOException;

import lombok.Getter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Response<T> {

  protected final okhttp3.Response httpResponse;
  protected final ObjectMapper mapper;
  @Getter
  protected final RateLimit rateLimit;
  @Getter
  protected Class<T> type;
  protected T content;
  protected String jsonPayload;

  public Response(okhttp3.Response httpResponse, ObjectMapper mapper, Class<T> type) {
    this.rateLimit = new RateLimit(httpResponse);
    this.mapper = mapper;
    this.type = type;
    this.httpResponse = httpResponse;
  }

  public T getContent() throws IOException {
    if (content == null) {
      readContent();
    }
    return content;
  }

  protected void readContent() throws IOException {
    this.content = mapper.readValue(getJsonPayload(), type);
  }

  public String getJsonPayload() {
    try {
      if (jsonPayload == null) {
        jsonPayload = httpResponse.body().string();
      }
      return jsonPayload;
    } catch (IOException e) {
      /* TODO: We should probably have our exception defined... will leave it here until we define it. */
      throw new RuntimeException("Error while retrieving response body: ", e);
    }
  }

  public int getHttpStatusCode(){
    return httpResponse.code();
  }
}
