package com.example.assertjdemo;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasItems;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrayTest {
  private String[] subjectUnderTest() {
    return new String[] {
        "actual value 0",
        "actual value 1",
        "actual value 2"
    };
  }

  /*
    Output:
    java.lang.AssertionError:
    Expected: (a collection containing "expected value")
         but: a collection containing "expected value" mismatches were: [was "actual value 0", was "actual value 1", was "actual value 2"]
   */
  @Test
  @DisplayName("Use JUnit to assert element.")
  @Order(1)
  public void useJUnitToAssertElement() {
    Assert.assertThat(Arrays.asList(subjectUnderTest()), hasItems("expected value"));
  }

  /*
    Output:
    java.lang.AssertionError:
    Expecting String[]:
     <["actual value 0", "actual value 1", "actual value 2"]>
    to contain:
     <["expected value"]>
    but could not find the following string(s):
     <["expected value"]>
   */
  @Test
  @DisplayName("Use AssertJ to assert element.")
  @Order(2)
  public void useAssertJToAssertElement() {
    Assertions.assertThat(subjectUnderTest())
        .contains("expected value");
  }
}
