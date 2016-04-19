package com.ticketmaster.api.discovery.operation;

import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class OperationTest {

  private MockOperation operation = new MockOperation();
  private final String key = "key";
  
  @Before
  public void setup() {
    
  }
  
  @Test
  public void noCommaAddedWhenAddingSingleValue() {
    final String[] values = new String[]{ "value1" };
    
    operation.withCommaSeparatedParam(key, values);
    
    assertThat(operation.getQueryParameters()).containsExactly(entry(key, values[0]));
  }
  
  @Test
  public void singleCommaAddedWhenAddingTwoValues() {
    final String[] values = new String[]{ "value1", "value2" };
    
    operation.withCommaSeparatedParam(key, values);
    
    assertThat(operation.getQueryParameters()).hasSize(1).containsKey(key).containsValue("value1,value2");
  }
  
  @Test
  public void onlyTheLatestValueIsKeptWhenMultipleInvokationOccurs() {
    final String[] values1 = new String[]{ "value1", "value2" };
    final String[] values2 = new String[]{ "value3", "value4" };
    
    operation.withCommaSeparatedParam(key, values1);
    operation.withCommaSeparatedParam(key, values2);
    
    assertThat(operation.getQueryParameters()).hasSize(1).containsKey(key).containsValue("value3,value4");
  }
}
