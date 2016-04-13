package com.ticketmaster.discovery.v2.operation;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Operation {

    protected Map<String, String> queryParams = new HashMap<String, String>();

    public Map<String, String> getQueryParameters() {
        return ImmutableMap.copyOf(queryParams);
    }

}
