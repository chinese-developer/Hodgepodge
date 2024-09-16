package com.component.whatif.android

import com.component.whatif.android.MainTestFragment.Companion
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
internal class WhatIfFragmentActivityTest {

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
  fun whatIfFindFragmentTest() {
    assertNotNull(mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG))

    mainTestActivity.whatIfFindFragment<MainTestFragment>(Companion.TAG) {
      assertNotNull(it)
      assertNotNull(it.activity)
      assertThat(it, instanceOf(MainTestFragment::class.java))
      assertThat(it.activity, instanceOf(MainTestActivity::class.java))
    }
  }

  @Test
  fun whatIfNotNullFindFragmentTest() {
    assertNotNull(mainTestActivity.supportFragmentManager.findFragmentByTag(Companion.TAG))

    var flag = false
    mainTestActivity.whatIfFindFragment<MainTestFragment2>(
      tag = Companion.TAG,
      whatIf = {
        assertNotNull(it)
        assertNotNull(it.activity)
        assertThat(it, instanceOf(MainTestFragment::class.java))
        assertThat(it.activity, instanceOf(MainTestActivity::class.java))
      },
      whatIfNot = {
        flag = true
      },
    )
    assertThat(flag, `is`(true))
  }
}
