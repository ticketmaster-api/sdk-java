package com.ticketmaster.api.example;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Classification;
import com.ticketmaster.discovery.model.DiscoveryDate;
import com.ticketmaster.discovery.model.Events;

public class App {
  private static final String LAT = "34.0522";
  private static final String LONG = "-118.2437";

  /**
   * To run this example, you need to provide your API key. Provide it using:
   * 
   * <pre>
   *    System property:
   *      -Dticketmaster-api-key=<YOUR-KEY>
   *    or
   *    
   *    Update the <YOUR-API> variable below
   * </pre>
   */
  private static String apikey = System.getProperty("ticketmaster-api-key", "<YOUR-KEY>");

  public static void main(String[] args) throws IOException {
    System.out.println("Welcome in the Ticketmaster Java SDK!");

    // 1. Instantiate a DiscoveryApi client:
    DiscoveryApi discoveryApi = new DiscoveryApi(apikey);

    // 2. Make our first search
    PagedResponse<Events> response =
        discoveryApi.searchEvents(new SearchEventsOperation().latlong(LAT, LONG) // Adding a filter
                                                                                 // for events
                                                                                 // nearby Los
                                                                                 // Angeles
            .pageSize(50) // Asking for a maximum of 50 events per pages
            );

    // 4. Let's create a beautiful ASCII art table
    AsciiArtTable table = new AsciiArtTable("Name", "Date", "Classification", "Attractions");
    response.getContent().getEvents().stream().forEach(event -> {
      AsciiArtRow row = new AsciiArtRow();

      row.addField(event.getName());
      row.addField(getEventDate(event.getDates()));
      row.addField(getClassification(event.getClassifications()));
      row.addField(getAttractions(event.getAttractions()));

      table.addRow(row);
    });

    // 5. And we'll print that piece of art in the console
    System.out.println(table.toString());
  }

  private static String getEventDate(DiscoveryDate dates) {
    if (dates != null && dates.getStart() != null && dates.getStart().getDateTime() != null) {
      return dates.getStart().getDateTime().toString();
    } else {
      return "No date";
    }
  }

  private static String getAttractions(List<Attraction> attractions) {
    return Optional.ofNullable(attractions).map(attrs -> {
      StringBuilder sb = new StringBuilder();
      String separator = "";
      for (Attraction attr : attrs) {
        sb.append(separator).append(attr.getName());

        separator = ", ";
      }
      return sb.toString();
    }).orElse("N/A");
  }

  private static String getClassification(List<Classification> classifications) {
    return Optional.ofNullable(classifications).map(cls -> cls.get(0)).map(c -> {
      return c.getSegment().toString();
    }).orElse("No classification");
  }
}
