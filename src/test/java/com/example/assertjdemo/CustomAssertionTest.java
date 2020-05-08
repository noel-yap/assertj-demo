package com.example.assertjdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.assertjdemo.util.ProjectAssertions.assertThat;

public class CustomAssertionTest {
  public static class SubjectUnderTest {
    public String getCustomField() {
      return "actual value";
    }
  }

  /*
    Output:
    java.lang.AssertionError: Expected custom field to be <expected value> but was <actual value>
   */
  @Test
  @DisplayName("Should use custom assertion.")
  public void shouldUseCustomAssertion() {
    assertThat(new SubjectUnderTest())
        .hasCustomField("expected value");
  }
}
