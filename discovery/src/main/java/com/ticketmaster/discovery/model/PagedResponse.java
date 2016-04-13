package com.ticketmaster.discovery.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;

public class PagedResponse<T> extends Response<T> {

    private final JavaType javaType;
    private Page<T> page;

    public PagedResponse(okhttp3.Response httpResponse, ObjectMapper mapper, Class<T> type) {
        super(httpResponse, mapper, type);
        this.javaType = mapper.getTypeFactory().constructParametricType(Page.class, type);
    }

    protected void readContent() {
        try {
            this.page = mapper.readValue(httpResponse.body().string(), javaType);
        } catch (IOException ioe) {
            Throwables.propagate(ioe);
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

    public PageInfo getPageInfo(){
        if (page == null) {
            readContent();
        }
        return page.getInfo();
    }

    @Getter
    @NoArgsConstructor
    public static class Page<T> {

        @JsonProperty("_embedded")
        private T embedded;

        @JsonProperty("_links")
        private PageLinks links;

        @JsonProperty("page")
        private PageInfo info;

    }

    @Getter
    @NoArgsConstructor
    public static class PageLinks {

        private Link self;
        private Link next;
        @JsonProperty("prev")
        private Link previous;
    }

    @Getter
    @NoArgsConstructor
    public static class PageInfo {

        @JsonProperty("size")
        private Integer pageSize;
        private Integer totalElements;
        private Integer totalPages;
        @JsonProperty("number")
        private Integer currentPage;
    }
}

