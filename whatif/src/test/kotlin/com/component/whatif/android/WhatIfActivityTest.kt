package com.component.whatif.android

import android.content.Intent
import android.net.Uri
import com.component.whatif.Poster
import com.component.whatif.PosterSerializable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
internal class WhatIfActivityTest {

  private lateinit var controller: ActivityController<MainTestActivity>
  private lateinit var mainTestActivity: MainTestActivity

  @Before
  fun createActivity() {
    val intent = Intent().apply {
      putExtra("foo", "bar")
      putExtra("string", "string")
      putExtra("charSequence", "charSequence")
      putExtra("parcelable", Poster.create())
      putExtra("serializable", PosterSerializable.create())
      data = Uri.parse("mvvm")
    }
    this.controller = Robolectric.buildActivity(
      MainTestActivity::class.java,
      intent,
    ).create().start()
    this.mainTestActivity = controller.get()
  }

  @Test
  fun whatIfHasExtrasTest() {
    var foo: String? = null

    this.mainTestActivity.whatIfHasExtras(
      whatIf = { foo = it.getString("foo") },
      whatIfNot = { foo = null },
    )

    assertThat(foo, `is`("bar"))

    this.mainTestActivity.whatIfHasExtras {
      foo = "${it.getString("foo")} single"
    }

    assertThat(foo, `is`("bar single"))
  }

  @Test
  fun whatIfHasStringExtraTest() {
    var string: String? = null

    this.mainTestActivity.whatIfHasStringExtra("string") {
      string = it
    }

    assertThat(string, `is`("string"))

    this.mainTestActivity.whatIfHasStringExtra(
      name = "null",
      whatIf = { string = it },
      whatIfNot = { string = null },
    )

    assertNull(string)
  }

  @Test
  fun whatIfHasCharSequenceExtraTest() {
    var charSequence: CharSequence? = null

    this.mainTestActivity.whatIfHasCharSequenceExtra("charSequence") {
      charSequence = it
    }

    assertThat(charSequence, `is`("charSequence"))

    this.mainTestActivity.whatIfHasCharSequenceExtra(
      name = "null",
      whatIf = { charSequence = it },
      whatIfNot = { charSequence = null },
    )

    assertNull(charSequence)
  }

  @Test
  fun whatIfHasParcelableExtraTest() {
    var poster: Poster? = null

    this.mainTestActivity.whatIfHasParcelableExtra<Poster>("parcelable") {
      poster = it
    }

    assertThat(poster, `is`(Poster.create()))

    this.mainTestActivity.whatIfHasParcelableExtra<Poster>(
      name = "null",
      whatIf = { poster = it },
      whatIfNot = { poster = null },
    )

    assertNull(poster)
  }

  @Test
  fun whatIfHasSerializableExtraTest() {
    var poster: PosterSerializable? = null

    this.mainTestActivity.whatIfHasSerializableExtra<PosterSerializable>("serializable") {
      poster = it
    }

    assertThat(poster, `is`(PosterSerializable.create()))

    this.mainTestActivity.whatIfHasSerializableExtra<PosterSerializable>(
      name = "null",
      whatIf = { poster = it },
      whatIfNot = { poster = null },
    )

    assertNull(poster)
  }

  @Test
  fun whatIfHasDeepLinkUriTest() {
    mainTestActivity.intent.data = Uri.parse("mvvm")
    var deepLinkUri: Uri? = null

    this.mainTestActivity.whatIfHasDeepLinkUri {
      deepLinkUri = it
    }

    assertThat(deepLinkUri.toString(), `is`("mvvm"))
  }

  @Test
  fun whatIfHasDeepLinkUriNotNullTest() {
    mainTestActivity.intent.data = null
    var deepLinkUri: Uri? = null
    var isNotNull = false

    this.mainTestActivity.whatIfHasDeepLinkUri(
      whatIf = { deepLinkUri = it },
      whatIfNot = { isNotNull = true },
    )

    assertNull(deepLinkUri)
    assertThat(isNotNull, `is`(true))
  }
}
