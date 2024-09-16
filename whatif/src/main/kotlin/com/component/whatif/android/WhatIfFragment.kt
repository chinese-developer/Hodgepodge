@file:Suppress("unused")
@file:JvmName("WhatIfFragment")
@file:JvmMultifileClass

package com.component.whatif.android

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.component.whatif.WhatIfInlineOnly
import com.component.whatif.whatIfNotNull
import com.component.whatif.whatIfNotNullAs
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * An expression for invoking [whatIf] when the [Context] is not null.
 *
 * @param whatIf An executable lambda function if the [Fragment]'s context is not null.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Fragment?.whatIfNotNullContext(
  whatIf: (Context) -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this?.context.whatIfNotNull {
    whatIf(it)
  }
}

/**
 * An expression for invoking [whatIf] when the [Context] is not null.
 * If the activity is null, [whatIfNot] will be invoked instead of the [whatIf].
 *
 * @param whatIf An executable lambda function if the [Fragment]'s context is not null.
 * @param whatIfNot An executable lambda function if the [Fragment]'s context is null.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Fragment?.whatIfNotNullContext(
  whatIf: (Context) -> Unit,
  whatIfNot: () -> Unit,
) {
  this?.context.whatIfNotNull(
    whatIf = whatIf,
    whatIfNot = whatIfNot,
  )
}

/**
 * An expression for invoking [whatIf] when the [Activity] is not null.
 *
 * @param whatIf An executable lambda function if the [Fragment]'s parent Activity is not null.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Fragment?.whatIfNotNullActivity(
  whatIf: (FragmentActivity) -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this?.activity.whatIfNotNull {
    whatIf(it)
  }
}

/**
 * An expression for invoking [whatIf] when the [Activity] is not null.
 * If the activity is null, [whatIfNot] will be invoked instead of the [whatIf].
 *
 * @param whatIf An executable lambda function if the [Fragment]'s parent Activity is not null.
 * @param whatIfNot An executable lambda function if the [Fragment]'s parent Activity is null.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Fragment?.whatIfNotNullActivity(
  whatIf: (FragmentActivity) -> Unit,
  whatIfNot: () -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  this?.activity.whatIfNotNull(
    whatIf = whatIf,
    whatIfNot = whatIfNot,
  )
}

/**
 * An expression for invoking [whatIf] when the [Fragment.getArguments] is not null.
 *
 * @param whatIf An executable lambda function if the [Fragment] has arguments.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Fragment?.whatIfHasArguments(
  whatIf: (Bundle) -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  whatIfHasArguments(whatIf) { }
}

/**
 * An expression for invoking [whatIf] when the [Fragment.getArguments] is not null.
 *
 * @param whatIf An executable lambda function if the [Fragment] has arguments.
 * @param whatIfNot An executable lambda function if the [Fragment] has not any arguments.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Fragment?.whatIfHasArguments(
  whatIf: (Bundle) -> Unit,
  whatIfNot: () -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  this?.arguments.whatIfNotNull(whatIf, whatIfNot)
}

/**
 * An expression for invoking [whatIf] when the [Fragment] has an [T] interface as a parent.
 *
 * @param whatIf An executable lambda function if the [Fragment] has an [T] interface as a parent.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T> Fragment?.whatIfFindParentInterface(
  whatIf: (T) -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  whatIfFindParentInterface(whatIf) { }
}

/**
 * An expression for invoking [whatIf] when the [Fragment] has an [T] interface as a parent.
 *
 * @param whatIf An executable lambda function if the [Fragment] has an [T] interface as a parent.
 * @param whatIfNot An executable lambda function if the [Fragment] has not an [T] interface as a parent.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T> Fragment?.whatIfFindParentInterface(
  whatIf: (T) -> Unit,
  whatIfNot: () -> Unit,
) {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  this?.activity.whatIfNotNullAs(whatIf, whatIfNot)
}
