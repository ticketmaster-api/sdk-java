package com.ticketmaster.api.discovery.operation;

import java.util.HashMap;
import java.util.Map;

import com.ticketmaster.api.discovery.util.Preconditions;

public abstract class Operation<T extends Operation<T>> {

  protected Map<String, String> queryParams = new HashMap<String, String>();

  protected abstract T getThis();

  public Map<String, String> getQueryParameters() {
    return queryParams;
  }

  public T locale(String locale) {
    return withParam("locale", locale);
  }
  
  public T withCommaSeparatedParam(String key, String... values) {
    StringBuilder sb = new StringBuilder();
    String separator = "";
    for (String value : values) {
      sb.append(separator).append(value);
      separator = ",";
    }
    
    return withParam(key, sb.toString());
  }

  public T withParam(String key, String value) {
    return withParam(key, value, false);
  }

  private T withParam(String key, String value, boolean emptyAllowed) {
    Preconditions.checkNotNull(value);
    if (!emptyAllowed) {
      Preconditions.checkArgument(!value.trim().isEmpty());
    }

    queryParams.put(key, value);
    return getThis();
  }

  public T withParam(String key, Integer value) {
    return withParam(key, value, 0);
  }

  public T withParam(String key, Integer value, Integer minValue) {
    Preconditions.checkNotNull(value);
    Preconditions.checkNotNull(minValue);
    Preconditions.checkArgument(value >= minValue);

    queryParams.put(key, value.toString());
    return getThis();
  }
}
