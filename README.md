# Ticketmaster Java SDK [![Build Status](https://travis-ci.org/ticketmaster-api/sdk-java.svg?branch=master)](https://travis-ci.org/ticketmaster-api/sdk-java)

Detailed information about the APIs can be found here:  
http://developer.ticketmaster.com/

## Usage

### Basic Usage

```java
String apikey = "<YOUR KEY>";
DiscoveryApi api = new DiscoveryApi(apikey);

PagedResponse<Events> page = api.searchEvents(new SearchEventsOperation().keyword("<SEARCH TERM>"));
List<Event> events = page.getContent().getEvents();
```

### Rate Limit

The Rate Limit information documented [here](http://developer.ticketmaster.com/products-and-docs/apis/getting-started/) can be retrieved using:
```java
PagedResponse<Events> page = ... ;
RateLimit rateLimit = page.getRateLimit();

Response<Event> response = ... ;
RateLimit rateLimit = response.getRateLimit();
```

## Android compatibility
 
Although the compatibility with Android is not fully tested yet, we choose to use Java 7 and compatible dependencies.     
Feel free to open issues and pull requests regarding that.

## Contributing

Please check out [this page](../blob/master/CONTRIBUTING.md)