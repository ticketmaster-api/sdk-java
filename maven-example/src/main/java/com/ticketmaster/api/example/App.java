package com.ticketmaster.api.example;

import java.io.IOException;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;

public class App {
  private static final String LAT = "34.0522";
  private static final String LONG = "-118.2437";
  private static String apikey = System.getProperty("ticketmaster-api-key", "<YOUR-KEY>");
  
  public static void main(String[] args) throws IOException {
    System.out.println("Welcome in the Ticketmaster Java SDK!");
    
    DiscoveryApi api = new DiscoveryApi(apikey);
    PagedResponse<Events> response = api.searchEvents(new SearchEventsOperation().latlong(LAT, LONG));
    for (Event e : response.getContent().getEvents()) {
      System.out.println(e.getName());
    }
  }
}