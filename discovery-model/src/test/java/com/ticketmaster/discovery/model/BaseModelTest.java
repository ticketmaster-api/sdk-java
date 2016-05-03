package com.ticketmaster.discovery.model;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.google.common.collect.Sets;

public class BaseModelTest {

  private Reflections reflections = new Reflections("com.ticketmaster.discovery.model", new SubTypesScanner(false));
  
  /**
   * This test is to ensure that all new classes that are added to the model correctly extends BaseModel
   * @throws Exception
   */
  @Test
  public void validateAllClassExtendsBaseModel() throws Exception {
    Set<Class<?>> allClass = reflections.getSubTypesOf(Object.class);
    Set<Class<? extends BaseModel>> baseModelClass = reflections.getSubTypesOf(BaseModel.class);
    
    Assertions.assertThat(filterClasses(allClass)).containsExactlyElementsOf(baseModelClass);
  }

  /**
   * Remove all classes that doesn't extends BaseModel.class on purpose
   * @param allClass
   * @return
   */
  @SuppressWarnings("unchecked")
  private Set<Class<?>> filterClasses(Set<Class<?>> allClass) {
    return Sets.difference(allClass, Sets.newHashSet(BaseModel.class, // The class itself
        Events.class, Attractions.class, Venues.class, ResourceSupport.Embedded.class,  // Wrapper classes
        Page.class, Page.Link.class, Page.PageInfo.class, Page.PageLinks.class, // Page related classes
        BaseModelTest.class // Test class
        ));
  }
  
}
