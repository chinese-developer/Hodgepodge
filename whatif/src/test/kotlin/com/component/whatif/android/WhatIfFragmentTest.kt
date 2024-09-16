package com.component.whatif.android

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.component.whatif.android.MainTestFragment.Companion
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
internal class WhatIfFragmentTest {

  private lateinit var controller: ActivityController<MainTestActivity>
  private lateinit var mainTestActivity: MainTestActivity

  @Before
  fun createActivity() {
    this.controller = Robolectric.buildActivity(
      MainTestActivity::class.java,
    ).create().start()
    this.mainTestActivity = controller.get()
    this.mainTestActivity.supportFragmentManager.apply {
      beginTransaction()
        .add(MainTestFragment(), Companion.TAG)
        .commit()
      executePendingTransactions()
    }
  }

  @Test
  fun whatIfNotNullContextTest() {
    val fragment = mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG)

    var flag = false
    fragment.whatIfNotNullContext {
      assertThat(it, instanceOf(Context::class.java))
      flag = true
    }
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfNotNullElseContextTest() {
    val fragment = MainTestFragment()

    var flag = false
    fragment.whatIfNotNullContext(
      whatIf = {},
      whatIfNot = { flag = true },
    )
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfNotNullActivityTest() {
    val fragment = mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG)

    var flag = false
    fragment.whatIfNotNullActivity {
      assertThat(it, instanceOf(FragmentActivity::class.java))
      flag = true
    }
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfNotNullElseActivityTest() {
    val fragment = MainTestFragment()

    var flag = false
    fragment.whatIfNotNullActivity(
      whatIf = {},
      whatIfNot = { flag = true },
    )
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfHasArgumentsTest() {
    val fragment = MainTestFragment().apply {
      arguments = Bundle().apply {
        putInt("param", 123)
      }
    }

    var flag = false
    fragment.whatIfHasArguments {
      assertThat(it.getInt("param", 0), `is`(123))
      flag = true
    }
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfHasArgumentsNotNullTest() {
    val fragment = MainTestFragment()

    var flag = false
    fragment.whatIfHasArguments(
      whatIf = {},
      whatIfNot = { flag = true },
    )
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfFindParentInterfaceTest() {
    val fragment = mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG)

    var flag = false
    fragment.whatIfFindParentInterface<MainTestActivity> {
      assertNotNull(it)
      assertThat(it, instanceOf(MainTestActivity::class.java))
      flag = true
    }
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfFindParentInterfaceAsInterfaceTest() {
    val fragment = mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG)

    var flag = false
    fragment.whatIfFindParentInterface<MainTestActivityInterface> {
      assertNotNull(it)
      assertThat(it, instanceOf(MainTestActivityInterface::class.java))
      flag = true
    }
    assertThat(flag, Is.`is`(true))
  }

  @Test
  fun whatIfFindParentInterfaceNotNullTest() {
    val fragment = mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG)

    var flag = false
    fragment.whatIfFindParentInterface<MainTestFragment2>(
      whatIf = {
        assertThat(1, `is`(2))
      },
      whatIfNot = {
        flag = true
      },
    )
    assertThat(flag, Is.`is`(true))
  }
}
