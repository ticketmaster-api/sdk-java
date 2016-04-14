# Ticketmaster Java SDK [![Build Status](https://travis-ci.org/ticketmaster-api/sdk-java.svg?branch=master)](https://travis-ci.org/ticketmaster-api/sdk-java)

Detailed information about the APIs can be found here:  
http://developer.ticketmaster.com/

## Basic Usage

```java
String apikey = "<YOUR KEY>";
DiscoveryApi api = DiscoveryApi.builder(apikey).build();

PagedResponse<Events> page = api.searchEvents(SearchEventsOperation.builder().keyword("<SEARCH TERM>").build());
List<Event> events = page.getContent().getEvents();
```

## Rate Limit

The Rate Limit information documented [here](http://developer.ticketmaster.com/products-and-docs/apis/getting-started/) can be retrieved using:
```java
PagedResponse<Events> page = ... ;
RateLimit rateLimit = page.getRateLimit();

Response<Event> response = ... ;
RateLimit rateLimit = response.getRateLimit();
```

## Android compatibility
 
 The compatibility with Android was not tested yet, but most of the dependencies seems to be compatible with Android.  
 Feel free to open issues and pull requests.
