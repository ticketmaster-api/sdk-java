package com.ticketmaster.discovery.v2.operation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class ByIdOperation extends Operation {

  protected final String id;

  private ByIdOperation(GetByIdOperationBuilder builder) {
    this.id = builder.id;
    if (builder.locale != null) {
      queryParams.put("locale", builder.locale);
    }
  }

  public String getId() {
    return id;
  }

  public static GetByIdOperationBuilder builder() {
    return new GetByIdOperationBuilder();
  }

  public static class GetByIdOperationBuilder {
    private String id;
    private String locale;

    GetByIdOperationBuilder() {}

    public GetByIdOperationBuilder id(String id) {
      this.id = id;
      return this;
    }

    public GetByIdOperationBuilder locale(String locale) {
      this.locale = locale;
      return this;
    }

    public ByIdOperation build() {
      return new ByIdOperation(this);
    }
  }

}
