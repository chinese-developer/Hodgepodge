@file:JvmName("WhatIfString")
@file:JvmMultifileClass

package com.component.whatif

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 当[String]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun String?.whatIfNotNullOrEmpty(
  whatIf: (String) -> Unit,
): String? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this.whatIfNotNullOrEmpty(
    whatIf = whatIf,
    whatIfNot = { },
  )
  return this
}

/**
 * 当[String]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun String?.whatIfNotNullOrEmpty(
  whatIf: (String) -> Unit,
  whatIfNot: () -> Unit,
): String? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (!this.isNullOrEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}
