package com.example.assertjdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExceptionTest {
  private static class SpecialIllegalArgumentException extends IllegalArgumentException {
    public SpecialIllegalArgumentException(final String message) {
      super(message);
    }

    public String errorCode() {
      return "actual errorCode";
    }
  }

  private void subjectUnderTest() throws RuntimeException {
    throw new ArithmeticException("actual message");
  }

  /*
    Output:
    org.opentest4j.AssertionFailedError:
    Expected :expected message
    Actual   :actual message
   */
  @Test
  @DisplayName("Use JUnit to assert message.")
  @Order(1)
  public void useJUnitToAssertMessage() {
    try {
      subjectUnderTest();
      fail("Must not reach this line");
    } catch (final Exception e) {
      assertEquals("expected message", e.getMessage());
    }
  }

  /*
    Output:
    org.opentest4j.AssertionFailedError:
    Expecting message to be:
      <"expected message">
    but was:
      <"actual message">

    Throwable that failed the check:

    java.lang.ArithmeticException: actual message
      …

    Expected :expected message
    Actual   :actual message
   */
  @Test
  @DisplayName("Use AssertJ to assert message.")
  @Order(2)
  public void useAssertJToAssertMessage() {
    final Throwable thrown = catchThrowable(this::subjectUnderTest);

    assertThat(thrown)
        .hasMessage("expected message");
  }

  /*
    Output:
    org.opentest4j.AssertionFailedError:
    Expected :class com.example.assertjdemo.ExceptionTest$SpecialIllegalArgumentException
    Actual   :class java.lang.ArithmeticException
   */
  @Test
  @DisplayName("Use JUnit to assert exception type and message.")
  @Order(3)
  public void useJUnitToAssertExceptionTypeAndMessage() {
    try {
      subjectUnderTest();
      fail("Must not reach this line");
    } catch (final SpecialIllegalArgumentException e) {
      assertEquals("actual message", e.getMessage());
      assertEquals("expected errorCode", e.errorCode());
    } catch (final Exception e) {
      assertEquals(SpecialIllegalArgumentException.class, e.getClass());
    }
  }

  /*
    Output:
    java.lang.AssertionError:
    Expecting:
      <java.lang.ArithmeticException: actual message>
    to be an instance of:
      <com.example.assertjdemo.ExceptionTest.SpecialIllegalArgumentException>
    but was:
      <"java.lang.ArithmeticException: actual message
      …
    ">
   */
  @Test
  @DisplayName("Use AssertJ to assert exception type and message.")
  @Order(4)
  public void useAssertJToAssertExceptionTypeAndMessage() {
    final Throwable thrown = catchThrowable(this::subjectUnderTest);

    assertThat(thrown)
        .hasMessage("actual message")
        .isInstanceOfSatisfying(SpecialIllegalArgumentException.class, e ->
            assertThat(e.errorCode())
                .isEqualTo("expected errorCode"));
  }
}
