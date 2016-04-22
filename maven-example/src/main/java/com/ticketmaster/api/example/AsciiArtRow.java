package com.ticketmaster.api.example;

import java.util.ArrayList;
import java.util.List;

public class AsciiArtRow {

  private List<String> cells = new ArrayList<String>();

  public void addCell(String value) {
    this.cells.add(value);
  }

  public List<String> getCells() {
    return cells;
  }

}