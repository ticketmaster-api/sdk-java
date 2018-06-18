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

  public Response(okhttp3.Response httpResponse, ObjectMapper mapper, Class<T> type) throws IOException {
    this.rateLimit = new RateLimit(httpResponse);
    this.mapper = mapper;
    this.type = type;
    this.httpResponse = httpResponse;

    // The okhttp3.ResponseBody in the httpResponse must always be closed or it is a memory leak.
    // Call readContent() here which effectively makes sure the okhttp3.ResponseBody is closed.
    // The OkHttp.ResponseBody.string() which is indirectly called as a result of call readContent()
    // closes the ResponseBody. Reference, https://square.github.io/okhttp/3.x/okhttp/okhttp3/ResponseBody.html.
    readContent();
  }

  public T getContent() throws IOException {
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
