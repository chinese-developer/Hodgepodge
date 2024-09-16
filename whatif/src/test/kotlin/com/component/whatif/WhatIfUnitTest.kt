@file:Suppress("KotlinConstantConditions")

package com.component.whatif

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class WhatIfUnitTest {

  @Test
  fun whatIfSingleLambdaTest() {
    var nullableBoolean: Boolean? = true

    whatIf(nullableBoolean) {
      nullableBoolean = false
    }

    assertThat(nullableBoolean, `is`(false))
  }

  @Test
  fun whatIfNotSingleLambdaTest() {
    var nullableString: String? = null

    whatIf(
      given = nullableString.isNullOrEmpty(),
      whatIf = { nullableString = "whatIf" },
      whatIfNot = { nullableString = "whatIfNot" },
    )

    assertThat(nullableString, `is`("whatIf"))

    whatIf(
      given = nullableString.isNullOrEmpty(),
      whatIf = { nullableString = "whatIf" },
      whatIfNot = { nullableString = "whatIfNot" },
    )

    assertThat(nullableString, `is`("whatIfNot"))
  }

  @Test
  fun whatIfDoSingleLambdaTest() {
    var nullableString: String? = null

    whatIf(
      given = { nullableString.isNullOrEmpty() },
      whatIf = { nullableString = "isNullOrEmpty" },
    )

    assertThat(nullableString, `is`("isNullOrEmpty"))
  }

  @Test
  fun whatIfDoNotSingleLambdaTest() {
    var nullableString: String? = null

    whatIf(
      given = { nullableString.isNullOrEmpty() },
      whatIf = { nullableString = "isNullOrEmpty" },
    )

    assertThat(nullableString, `is`("isNullOrEmpty"))

    whatIf(
      given = { nullableString.isNullOrEmpty() },
      whatIf = { nullableString = "isNullOrEmpty" },
      whatIfNot = { nullableString = "isNotNullOrEmpty" },
    )

    assertThat(nullableString, `is`("isNotNullOrEmpty"))
  }

  @Test
  fun whatIfNotNullWithTest() {
    var nullableString: String? = null

    nullableString = nullableString.whatIfNotNullWith(
      default = "default",
      whatIf = { "whatIf" },
    )

    assertThat(nullableString, `is`("default"))

    nullableString = nullableString.whatIfNotNullWith(
      whatIf = { "whatIf" },
      whatIfNot = { "whatIfNot" },
    )

    assertThat(nullableString, `is`("whatIf"))
  }

  @Test
  fun whatIfMapWithGivenTest() {
    var nullableString: String? = null

    nullableString = nullableString.whatIfNotNullWith(
      given = !nullableString.isNullOrEmpty(),
      default = "default",
      whatIf = { "whatIf" },
    )

    assertThat(nullableString, `is`("default"))

    nullableString = nullableString.whatIfNotNullWith(
      given = nullableString.isNotEmpty(),
      default = "default",
      whatIf = { "whatIf" },
    )

    assertThat(nullableString, `is`("whatIf"))
  }

  @Test
  fun whatIfNotNullTest() {
    var nullableString: String? = "notNull"

    nullableString.whatIfNotNull {
      nullableString = "whatIf $it"
    }

    assertThat(nullableString, `is`("whatIf notNull"))
  }

  @Test
  fun whatIfNotNullWhatIfNotTest() {
    var nullableString: String? = null

    nullableString.whatIfNotNull(
      whatIf = { nullableString = "whatIf" },
      whatIfNot = { nullableString = "whatIfNot" },
    )

    assertThat(nullableString, `is`("whatIfNot"))

    nullableString.whatIfNotNull(
      whatIf = { nullableString = it.length.toString() },
      whatIfNot = { nullableString = "whatIfNot" },
    )

    assertThat(nullableString, `is`("9"))
  }

  @Test
  fun whatIfNotNullWithDifferentTypeReturnTest() {
    val nullableString = "notNull"

    val length: Int = nullableString.whatIfNotNullWith(
      whatIf = { it.length },
      whatIfNot = { 0 },
    )

    assertThat(length, `is`(7))
  }

  @Test
  fun nullableBooleanWhatIfTest() {
    var nullableBoolean: Boolean? = null
    var testInteger = 0

    nullableBoolean.whatIf { testInteger = 1 }
    assertThat(testInteger, `is`(0))

    nullableBoolean = true

    nullableBoolean.whatIf { testInteger = 1 }
    assertThat(testInteger, `is`(1))
  }

  @Test
  fun nullableBooleanWhatIfNotTest() {
    var nullableBoolean: Boolean? = null
    var testInteger = 0

    nullableBoolean.whatIf(
      whatIf = { testInteger = 0 },
      whatIfNot = { testInteger = 1 },
    )
    assertThat(testInteger, `is`(1))

    nullableBoolean = true

    nullableBoolean.whatIf(
      whatIf = { testInteger = 0 },
      whatIfNot = { testInteger = 1 },
    )
    assertThat(testInteger, `is`(0))
  }

  @Test
  fun nullableBooleanWhatIfElseTest() {
    var nullableBoolean: Boolean? = null
    var testInteger = 0

    nullableBoolean.whatIf { testInteger = 1 }
    assertThat(testInteger, `is`(0))

    nullableBoolean = false

    nullableBoolean.whatIfElse { testInteger = 1 }
    assertThat(testInteger, `is`(1))
  }

  @Test
  fun whatIfNotNullAsTest() {
    var nullableIntList: MutableList<Int>? = null

    nullableIntList.whatIfNotNullAs<List<String>>(
      whatIf = { nullableIntList = arrayListOf(it.size) },
      whatIfNot = { nullableIntList = arrayListOf(123) },
    )

    assertThat(nullableIntList?.get(0), `is`(123))

    var nullableString: String? = "foo"

    nullableString.whatIfNotNullAs<CharSequence> {
      nullableString = it.toString() + "bar"
    }

    assertThat(nullableString, `is`("foobar"))
  }

  @Test
  fun whatIfNotNullAsCannotBeCastedTest() {
    var result = 0L
    val nullableString = "foo"

    nullableString.whatIfNotNullAs<Long>(
      whatIf = { result = it },
      whatIfNot = { result = -1 },
    )

    assertThat(result, `is`(-1L))

    nullableString.whatIfNotNullAs<CharSequence>(
      whatIf = { result = it.length.toLong() },
      whatIfNot = { result = -1 },
    )

    assertThat(result, `is`(3))
  }

  @Test
  fun nullableBooleanWhatIfAndTest() {
    var nullableBoolean: Boolean? = null
    var predicate: Boolean? = null
    var testInteger = 0

    nullableBoolean.whatIfAnd(predicate) { testInteger = 1 }
    assertThat(testInteger, `is`(0))

    nullableBoolean = true
    predicate = true

    nullableBoolean.whatIfAnd(predicate) { testInteger = 1 }
    assertThat(testInteger, `is`(1))
  }

  @Test
  fun nullableBooleanWhatIfOrTest() {
    var nullableBoolean: Boolean? = null
    val predicate: Boolean? = null
    var testInteger = 0

    nullableBoolean.whatIfOr(predicate) { testInteger = 1 }
    assertThat(testInteger, `is`(0))

    nullableBoolean = true

    nullableBoolean.whatIfOr(predicate) { testInteger = 1 }
    assertThat(testInteger, `is`(1))
  }
}
