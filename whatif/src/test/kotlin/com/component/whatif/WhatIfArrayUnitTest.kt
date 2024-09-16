@file:Suppress("KotlinConstantConditions")

package com.component.whatif

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class WhatIfArrayUnitTest {

  @Test
  fun whatIfNotNullOrEmptyArrayTest() {
    var array: Array<String>? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { array = arrayOf("NotNullOrEmpty") },
      whatIfNot = { array = arrayOf("NullOrEmpty") },
    )

    assertThat(array?.get(0), `is`("NullOrEmpty"))

    array.whatIfNotNullOrEmpty {
      array = arrayOf("Not${it[0]}")
    }

    assertThat(array?.get(0), `is`("NotNullOrEmpty"))
  }

  @Test
  fun whatIfNotNullOrEmptyByteArrayTest() {
    var array: ByteArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { array = byteArrayOf(0) },
      whatIfNot = { array = byteArrayOf(11) },
    )

    assertThat(array!!, `is`(byteArrayOf(11)))

    array.whatIfNotNullOrEmpty {
      array = byteArrayOf(123)
    }

    assertThat(array!!, `is`(byteArrayOf(123)))
  }

  @Test
  fun whatIfNotNullOrEmptyShortArrayTest() {
    var array: ShortArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = shortArrayOf(11) },
    )

    assertThat(array!!, `is`(shortArrayOf(11)))

    array.whatIfNotNullOrEmpty {
      array = shortArrayOf(123)
    }

    assertThat(array!!, `is`(shortArrayOf(123)))
  }

  @Test
  fun whatIfNotNullOrEmptyIntArrayTest() {
    var array: IntArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = intArrayOf(11) },
    )

    assertThat(array!!, `is`(intArrayOf(11)))

    array.whatIfNotNullOrEmpty {
      array = intArrayOf(123)
    }

    assertThat(array!!, `is`(intArrayOf(123)))
  }

  @Test
  fun whatIfNotNullOrEmptyLongArrayTest() {
    var array: LongArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = longArrayOf(11) },
    )

    assertThat(array!!, `is`(longArrayOf(11)))

    array.whatIfNotNullOrEmpty {
      array = longArrayOf(123)
    }

    assertThat(array!!, `is`(longArrayOf(123)))
  }

  @Test
  fun whatIfNotNullOrEmptyFloatArrayTest() {
    var array: FloatArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = floatArrayOf(11f) },
    )

    assertThat(array!!, `is`(floatArrayOf(11f)))

    array.whatIfNotNullOrEmpty {
      array = floatArrayOf(123f)
    }

    assertThat(array!!, `is`(floatArrayOf(123f)))
  }

  @Test
  fun whatIfNotNullOrEmptyDoubleArrayTest() {
    var array: DoubleArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = doubleArrayOf(11.1) },
    )

    assertThat(array!!, `is`(doubleArrayOf(11.1)))

    array.whatIfNotNullOrEmpty {
      array = doubleArrayOf(123.1)
    }

    assertThat(array!!, `is`(doubleArrayOf(123.1)))
  }

  @Test
  fun whatIfNotNullOrEmptyBooleanArrayTest() {
    var array: BooleanArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = booleanArrayOf(true) },
    )

    assertThat(array!!, `is`(booleanArrayOf(true)))

    array.whatIfNotNullOrEmpty {
      array = booleanArrayOf(!it[0])
    }

    assertThat(array!!, `is`(booleanArrayOf(false)))
  }

  @Test
  fun whatIfNotNullOrEmptyCharArrayTest() {
    var array: CharArray? = null

    array.whatIfNotNullOrEmpty(
      whatIf = { },
      whatIfNot = { array = charArrayOf('0') },
    )

    assertThat(array!!, `is`(charArrayOf('0')))

    array.whatIfNotNullOrEmpty {
      array = charArrayOf('7')
    }

    assertThat(array!!, `is`(charArrayOf('7')))
  }
}
