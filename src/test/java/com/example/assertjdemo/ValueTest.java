package com.example.assertjdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValueTest {
  private String subjectUnderTest() {
    return "actual value";
  }

  /*
    Output:
    org.opentest4j.AssertionFailedError:
    Expected :actual value
    Actual   :expected value
   */
  @Test
  @DisplayName("Use JUnit to assert value.")
  @Order(1)
  public void useJUnitToAssertValue() {
    assertEquals(subjectUnderTest(), "expected value");
  }

  /*
    Output:
    Expecting:
     <"actual value">
    to be equal to:
     <"expected value">
    but was not.
    Expected :expected value
    Actual   :actual value
   */
  @Test
  @DisplayName("Use AssertJ to assert value.")
  @Order(2)
  public void useAssertJToAssertValue() {
    Assertions.assertThat(subjectUnderTest())
        .isEqualTo("expected value");
  }
}
