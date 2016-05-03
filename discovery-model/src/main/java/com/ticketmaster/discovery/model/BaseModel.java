package com.ticketmaster.discovery.model;

import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@ToString
@EqualsAndHashCode
public class BaseModel {

  private Map<String, Object> otherProperties = new HashMap<String, Object>();
  
  @JsonAnyGetter
  public Map<String,Object> getOtherProperties() {
      return otherProperties;
  }

  @JsonAnySetter
  public void setOtherProperties(String name, Object value) {
    otherProperties.put(name, value);
  }
}
