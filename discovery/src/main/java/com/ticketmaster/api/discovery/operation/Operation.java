package com.ticketmaster.api.discovery.operation;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Operation<T extends Operation<T>> {

  protected Map<String, String> queryParams = new HashMap<String, String>();

  protected abstract T getThis();
  
  public Map<String, String> getQueryParameters() {
    return ImmutableMap.copyOf(queryParams);
  }

  public T locale(String locale) {
    return with("locale", locale);
  }

  protected T with(String key, String value) {
    Preconditions.checkNotNull(value);
    Preconditions.checkArgument(!value.trim().isEmpty());
    
    queryParams.put(key, value);
    return getThis();
  }

  protected T with(String key, Integer value) {
    Preconditions.checkNotNull(value);
    Preconditions.checkArgument(value > 0);
    
    queryParams.put(key, value.toString());
    return getThis();
  }
}
