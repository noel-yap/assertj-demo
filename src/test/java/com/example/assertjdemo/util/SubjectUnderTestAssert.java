package com.example.assertjdemo.util;

import com.example.assertjdemo.CustomAssertionTest;
import org.assertj.core.api.AbstractObjectAssert;

import java.util.Objects;

public class SubjectUnderTestAssert extends AbstractObjectAssert<SubjectUnderTestAssert, CustomAssertionTest.SubjectUnderTest> {
  public SubjectUnderTestAssert(final CustomAssertionTest.SubjectUnderTest actual) {
    super(actual, SubjectUnderTestAssert.class);
  }

  public SubjectUnderTestAssert hasCustomField(final String actualField0) {
    isNotNull();

    if (!Objects.equals(actual.getCustomField(), actualField0)) {
      failWithMessage("Expected custom field to be <%s> but was <%s>", actualField0, actual.getCustomField());
    }

    return this;
  }
}
