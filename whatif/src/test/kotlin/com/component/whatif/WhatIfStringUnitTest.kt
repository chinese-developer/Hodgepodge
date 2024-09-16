@file:Suppress("KotlinConstantConditions")

package com.component.whatif

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class WhatIfStringUnitTest {

  @Test
  fun whatIfNotNullOrEmptyStringNullTest() {
    var nullableString: String? = null

    nullableString.whatIfNotNullOrEmpty(
      whatIf = { nullableString = it },
      whatIfNot = { nullableString = "NullOrEmpty" },
    )

    assertThat(nullableString, `is`("NullOrEmpty"))

    nullableString.whatIfNotNullOrEmpty {
      nullableString = "NotNullOrEmpty"
    }

    assertThat(nullableString, `is`("NotNullOrEmpty"))
  }

  @Test
  fun whatIfNotNullOrEmptyStringEmptyTest() {
    var nullableString: String? = ""

    nullableString.whatIfNotNullOrEmpty(
      whatIf = { nullableString = it },
      whatIfNot = { nullableString = "NullOrEmpty" },
    )

    assertThat(nullableString, `is`("NullOrEmpty"))

    nullableString.whatIfNotNullOrEmpty {
      nullableString = "Not$it"
    }

    assertThat(nullableString, `is`("NotNullOrEmpty"))
  }
}
