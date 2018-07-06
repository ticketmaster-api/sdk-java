package com.ticketmaster.api.discovery.response;

import java.io.IOException;

import lombok.Getter;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;

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

    // The okhttp3.ResponseBody in the httpResponse must always be closed or it is a memory leak.
    // Call consumeResponseBody() here which effectively makes sure the okhttp3.ResponseBody is closed.
    // The OkHttp.ResponseBody.string() which is indirectly called as a result of call consumeResponseBody()
    // closes the ResponseBody. Reference, https://square.github.io/okhttp/3.x/okhttp/okhttp3/ResponseBody.html.
    consumeResponseBody();
  }

  public T getContent() throws IOException {
    if (content == null) {
    readContent();
  }
    return content;
  }

  protected void readContent() throws IOException {
      this.content = mapper.readValue(jsonPayload == null ? getJsonPayload() : jsonPayload, type);
  }

  protected void consumeResponseBody() {
    try {
      jsonPayload = httpResponse.body().string();
    } catch (Exception e) {
      // Not throwing exception here. Objective with this method is simply to consume
      // the ResponseBody if it exists and make sure the ResponseBody is closed and this is
      // only meant to be done at construction time. The call to httpResponse.body().string()
      // above will automatically close a body if it exists.
      // If the body was null or could not be consumed, the exception will be caught here, but
      // will wait to throw exceptions when user tries to actually consume the body via the methods
      // that are publicly exposed for this purpose.
    }
  }

  public String getJsonPayload() {
    try {
      if (jsonPayload == null) {
        jsonPayload = httpResponse.body().string();
      }
      return jsonPayload;
    } catch (IOException e) {
      // In order to a
      /* TODO: We should probably have our exception defined... will leave it here until we define it. */
      throw new RuntimeException("Error while retrieving response body: ", e);
    }
  }

  public int getHttpStatusCode(){
    return httpResponse.code();
  }
}
