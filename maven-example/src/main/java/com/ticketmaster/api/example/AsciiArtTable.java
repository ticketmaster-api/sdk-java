package com.ticketmaster.api.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;

public class AsciiArtTable {

  private List<AsciiArtRow> rows = new ArrayList<AsciiArtRow>();
  
  public AsciiArtTable(String... headers) {
    AsciiArtRow header = new AsciiArtRow();
    Arrays.asList(headers).stream().forEach(h -> {
      header.addField(h);
    });
    rows.add(header);
  }

  public void addRow(AsciiArtRow row) {
    rows.add(row);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String newLine = System.getProperty("line.separator");
    rows.stream().forEach(r -> {
      sb.append("| ").append(Joiner.on(" | ").join(r.getFields())).append(" |");
      sb.append(newLine);
    });
    return sb.toString();
  }

}
