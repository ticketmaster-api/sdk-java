package com.ticketmaster.api.discovery.response;

import java.io.IOException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketmaster.discovery.model.Page;
import com.ticketmaster.discovery.model.Page.Link;
import com.ticketmaster.discovery.model.Page.PageInfo;

public class PagedResponse<T> extends Response<T> {

  private final JavaType javaType;
  private Page<T> page;

  public PagedResponse(okhttp3.Response httpResponse, ObjectMapper mapper, Class<T> type) throws IOException {
    super(httpResponse, mapper, type);
    this.javaType = mapper.getTypeFactory().constructParametricType(Page.class, type);
  }

  protected void readContent() {
    try {
      this.page = mapper.readValue(httpResponse.body().string(), javaType);
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
    this.content = page.getEmbedded();
  }

  public T getContent() {
    if (content == null) {
      readContent();
    }
    return content;
  }

  public Link getNextPageLink() {
    if (page == null) {
      readContent();
    }
    return page.getLinks().getNext();
  }

  public Link getPreviousPageLink() {
    if (page == null) {
      readContent();
    }
    return page.getLinks().getPrevious();
  }

  public PageInfo getPageInfo() {
    if (page == null) {
      readContent();
    }
    return page.getInfo();
  }

}
