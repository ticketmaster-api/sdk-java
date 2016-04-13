package com.ticketmaster.discovery.model;

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

    public Response(okhttp3.Response httpResponse, ObjectMapper mapper, Class<T> type) {
        this.rateLimit = new RateLimit(httpResponse);
        this.mapper = mapper;
        this.type = type;
        this.httpResponse = httpResponse;
    }

    protected void readContent() throws IOException {
        this.content = mapper.readValue(httpResponse.body().string(), type);
    }

    public T getContent() throws IOException {
        if (content == null) {
            readContent();
        }
        return content;
    }
}
