package com.ticketmaster.api.discovery;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

import okhttp3.HttpUrl;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;
import org.springframework.util.ReflectionUtils.FieldFilter;

import com.ticketmaster.api.discovery.operation.SearchAttractionsOperation;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.operation.SearchVenuesOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Attractions;
import com.ticketmaster.discovery.model.BaseModel;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.discovery.model.Venue;
import com.ticketmaster.discovery.model.Venues;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class DiscoveryApiITest {

  /**
   * Get the API key from the system properties or in the environment variables
   **/
  private static String apikey = System.getProperty("tm-api-key", System.getenv("TM_API_KEY"));
  private DiscoveryApi api = new DiscoveryApi(apikey);

  @Test
  public void whenCallingSearchEvents_ensureAllFieldsAreInTheModel() throws Exception {
    for (int i = 0; i < 10; i++) {
      PagedResponse<Events> response =
          api.searchEvents(new SearchEventsOperation().pageSize(500).pageNumber(i));

      for (Event event : response.getContent().getEvents()) {
        validateEmptyOtherProperties(event);
      }
    }
  }

  @Test
  public void whenCallingSearchAttractions_ensureAllFieldsAreInTheModel() throws Exception {
    for (int i = 0; i < 10; i++) {
      PagedResponse<Attractions> response =
          api.searchAttractions(new SearchAttractionsOperation().pageSize(500).pageNumber(i));

      for (Attraction attraction : response.getContent().getAttractions()) {
        validateEmptyOtherProperties(attraction);
      }
    }
  }

  @Test
  public void whenCallingSearchVenues_ensureAllFieldsAreInTheModel() throws Exception {
    for (int i = 0; i < 10; i++) {
      PagedResponse<Venues> response =
          api.searchVenues(new SearchVenuesOperation().pageSize(500).pageNumber(i));

      for (Venue venue : response.getContent().getVenues()) {
        validateEmptyOtherProperties(venue);
      }
    }
  }

  @Test
  public void testPathModifierLocalized() throws Exception {
    DiscoveryApiConfiguration configuration = DiscoveryApiConfiguration.builder()
            .pathModifier(DiscoveryApiConfiguration.PathModifier.TICKETMASTER_UK)
            .build();

    DiscoveryApi api = new DiscoveryApi("aKey",configuration);

    HttpUrl url = api.urlBuilder("event").build();

    assertThat(url.encodedPath(),is("/discovery/v2/event/ticketmaster-uk"));
  }

  @Test
  public void testPathModifierNotLocalized() throws Exception {
    DiscoveryApiConfiguration configuration = DiscoveryApiConfiguration.builder()
            .pathModifier(DiscoveryApiConfiguration.PathModifier.NONE)
            .build();

    DiscoveryApi api = new DiscoveryApi("aKey",configuration);

    HttpUrl url = api.urlBuilder("event").build();

    assertThat(url.encodedPath(),is("/discovery/v2/event"));
  }

  private void validateEmptyOtherProperties(final Object object) {
    if (object != null && BaseModel.class.isAssignableFrom(object.getClass())) {
      assertThat(getOtherProperties(object).isEmpty(),is(true));
      recursiveValidate(object);
    }
    return;
  }

  @SuppressWarnings("unchecked")
  private Map<String, Object> getOtherProperties(final Object object) {
    Field otherProperties = ReflectionUtils.findField(object.getClass(), "otherProperties");
    ReflectionUtils.makeAccessible(otherProperties);
    return (Map<String, Object>) ReflectionUtils.getField(otherProperties, object);
  }

  private void recursiveValidate(final Object object) {
    ReflectionUtils.doWithFields(object.getClass(), new FieldCallback() {
      @Override
      public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        validateEmptyOtherProperties(field.get(object));
      }
    }, new FieldFilter() {
      @Override
      public boolean matches(Field field) {
        return BaseModel.class.isAssignableFrom(field.getDeclaringClass());
      }
    });
  }

}
