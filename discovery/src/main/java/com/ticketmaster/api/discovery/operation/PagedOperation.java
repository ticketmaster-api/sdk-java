package com.ticketmaster.api.discovery.operation;


public abstract class PagedOperation<T extends Operation<T>> extends Operation<T> {

  public T pageSize(Integer pageSize) {
    return withParam("size", pageSize);
  }

  public T pageNumber(Integer pageNumber) {
    return withParam("page", pageNumber);
  }

  public T sort(String sort) {
    return withParam("sort", sort);
  }
}
