package com.ticketmaster.api.discovery;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;

public class TestRunner {

  @RunWith(Categories.class)
  @ExcludeCategory(IntegrationTests.class)
  public class UnitTestSuite {
    // Will run A.b and B.c, but not A.a
  }
  
  @RunWith(Categories.class)
  @IncludeCategory(IntegrationTests.class)
  public class IntegrationTestSuite {
    // Will run A.b and B.c, but not A.a
  }
  
  public interface IntegrationTests { /* category marker */ }
}
