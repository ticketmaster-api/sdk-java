package com.ticketmaster.api.example;

import java.util.ArrayList;
import java.util.List;

public class AsciiArtRow {

  private List<String> fields = new ArrayList<String>();

  public void addField(String value) {
    this.fields.add(value);
  }

  public List<String> getFields() {
    return fields;
  }
  
}
