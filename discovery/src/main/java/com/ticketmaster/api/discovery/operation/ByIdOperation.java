package com.ticketmaster.api.discovery.operation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class ByIdOperation extends Operation<ByIdOperation> {

  @Getter
  protected String id;

  public ByIdOperation id(String id) {
    this.id = id;
    return this;
  }

  @Override
  protected ByIdOperation getThis() {
    return this;
  }

}
