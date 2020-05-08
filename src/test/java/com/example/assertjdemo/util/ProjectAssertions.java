package com.example.assertjdemo.util;

import com.example.assertjdemo.CustomAssertionTest;
import org.assertj.core.api.Assertions;

public class ProjectAssertions extends Assertions {
  public static SubjectUnderTestAssert assertThat(final CustomAssertionTest.SubjectUnderTest actual) {
    return new SubjectUnderTestAssert(actual);
  }
}
