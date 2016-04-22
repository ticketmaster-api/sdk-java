package com.ticketmaster.api.example;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Classification;
import com.ticketmaster.discovery.model.DiscoveryDate;
import com.ticketmaster.discovery.model.DiscoveryDate.Start;
import com.ticketmaster.discovery.model.Events;

public class AsciiArtTableExample {
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

      row.addCell(event.getName());
      row.addCell(getEventDate(event.getDates()));
      row.addCell(getClassification(event.getClassifications()));
      row.addCell(getAttractions(event.getAttractions()));

      table.addRow(row);
    });

    // 5. And we'll print that piece of art in the console
    System.out.println(table.toString());
  }
  
  

  /**
   * Note: The following methods are used to get a String representation of the different POJO (while handling null value) 
   */
  private static String getEventDate(DiscoveryDate dates) {
    return Optional.ofNullable(dates).map(DiscoveryDate::getStart).map(Start::getDateTime)
        .map(dt -> dt.toString()).orElse("No date");
  }

  private static String getAttractions(List<Attraction> attractions) {
    if (attractions != null) {
      return Joiner.on(" / ").useForNull("N/A").join(attractions.stream().map(Attraction::getName).collect(Collectors.toList()));
    }
    return "N/A";
  }

  private static String getClassification(List<Classification> classifications) {
    return Optional.ofNullable(classifications).map(cls -> cls.get(0))
        .map(c -> {
          return Joiner.on(" / ").join(c.getSegment().getName(), c.getGenre().getName(), c.getSubGenre().getName());
        }).orElse("No classification");
  }
}
