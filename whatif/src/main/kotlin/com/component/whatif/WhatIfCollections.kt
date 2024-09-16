@file:JvmName("WhatIfCollections")
@file:JvmMultifileClass

package com.component.whatif

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 当[List]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> List<T>?.whatIfNotNullOrEmpty(
  whatIf: (List<T>) -> Unit,
): List<T>? {
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
 * 当[List]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> List<T>?.whatIfNotNullOrEmpty(
  whatIf: (List<T>) -> Unit,
  whatIfNot: () -> Unit,
): List<T>? {
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

/**
 * 当[Set]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> Set<T>?.whatIfNotNullOrEmpty(
  whatIf: (Set<T>) -> Unit,
): Set<T>? {
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
 * 当[Set]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> Set<T>?.whatIfNotNullOrEmpty(
  whatIf: (Set<T>) -> Unit,
  whatIfNot: () -> Unit,
): Set<T>? {
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

/**
 * 当[Map]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T, R> Map<T, R>?.whatIfNotNullOrEmpty(
  whatIf: (Map<T, R>) -> Unit,
): Map<T, R>? {
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
 * 当[Map]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T, R> Map<T, R>?.whatIfNotNullOrEmpty(
  whatIf: (Map<T, R>) -> Unit,
  whatIfNot: () -> Unit,
): Map<T, R>? {
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

/**
 * An expression for adding an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element An element should be added into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.addWhatIfNotNull(
  element: E?,
  whatIf: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this.addWhatIfNotNull(
    element = element,
    whatIf = whatIf,
    whatIfNot = { },
  )
  return this
}

/**
 * An expression for adding an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element An element should be added into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 * @param whatIfNot An executable lambda function if the [element] it null or empty.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.addWhatIfNotNull(
  element: E?,
  whatIf: (T) -> Unit,
  whatIfNot: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  element?.whatIfNotNullAs<E>(
    whatIf = {
      add(it)
      whatIf(this)
    },
    whatIfNot = {
      whatIfNot(this)
    },
  )
  return this
}

/**
 * An expression for adding an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element n collection of elements should be added into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.addAllWhatIfNotNull(
  element: Collection<E>?,
  whatIf: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this.addAllWhatIfNotNull(
    element = element,
    whatIf = whatIf,
    whatIfNot = { },
  )
  return this
}

/**
 * An expression for adding an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element n collection of elements should be added into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 * @param whatIfNot An executable lambda function if the [element] it null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.addAllWhatIfNotNull(
  element: Collection<E>?,
  whatIf: (T) -> Unit,
  whatIfNot: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  element?.whatIfNotNull(
    whatIf = {
      addAll(it)
      whatIf(this)
    },
    whatIfNot = {
      whatIfNot(this)
    },
  )
  return this
}

/**
 * An expression for removing an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element An element should be removed into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.removeWhatIfNotNull(
  element: E?,
  whatIf: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this.removeWhatIfNotNull(
    element = element,
    whatIf = whatIf,
    whatIfNot = { },
  )
  return this
}

/**
 * An expression for removing an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element An element should be removed into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 * @param whatIfNot An executable lambda function if the [element] it null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.removeWhatIfNotNull(
  element: E?,
  whatIf: (T) -> Unit,
  whatIfNot: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  element?.whatIfNotNullAs<E>(
    whatIf = {
      remove(it)
      whatIf(this)
    },
    whatIfNot = {
      whatIfNot(this)
    },
  )
  return this
}

/**
 * An expression for removing a collection of [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element A collection of elements should be removed into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.removeAllWhatIfNotNull(
  element: Collection<E>?,
  whatIf: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  this.removeAllWhatIfNotNull(
    element = element,
    whatIf = whatIf,
    whatIfNot = { },
  )
  return this
}

/**
 * An expression for removing an [element] and invoking [whatIf] when the [element] is not null.
 *
 * @param element A collection of elements should be removed into the target.
 * @param whatIf An executable lambda function if the [element] it not null.
 * @param whatIfNot An executable lambda function if the [element] it null.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified T : MutableCollection<E>, reified E> T.removeAllWhatIfNotNull(
  element: Collection<E>?,
  whatIf: (T) -> Unit,
  whatIfNot: (T) -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  element?.whatIfNotNull(
    whatIf = {
      removeAll(it)
      whatIf(this)
    },
    whatIfNot = {
      whatIfNot(this)
    },
  )
  return this
}

/**
 * An expression for operating `And` operator to a list of the nullable-Boolean.
 *
 * @param whatIf An executable lambda function if the result of the `And` operation is true.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Iterable<Boolean?>.whatIfAnd(
  whatIf: () -> Unit,
): Iterable<Boolean?> {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIfAnd(
    whatIf = whatIf,
    whatIfNot = { },
  )
}

/**
 * An expression for operating `And` operator to a list of the nullable-Boolean.
 *
 * @param whatIf An executable lambda function if the result of the `And` operation is true.
 * @param whatIfNot An executable lambda function if the result of the `And` operation is false.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Iterable<Boolean?>.whatIfAnd(
  whatIf: () -> Unit,
  whatIfNot: (() -> Unit),
): Iterable<Boolean?> {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  var predicate: Boolean? = null

  this.forEach {
    val p = predicate
    predicate = if (p == null) {
      it
    } else {
      p and (it == true)
    }
  }

  if (predicate == true) {
    whatIf()
  } else {
    whatIfNot()
  }
  return this
}

/**
 * An expression for operating `Or` operator to a list of the nullable-Boolean.
 *
 * @param whatIf An executable lambda function if the result of the `Or` operation is true.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Iterable<Boolean?>.whatIfOr(
  whatIf: () -> Unit,
): Iterable<Boolean?> {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIfOr(
    whatIf = whatIf,
    whatIfNot = { },
  )
}

/**
 * An expression for operating `Or` operator to a list of the nullable-Boolean.
 *
 * @param whatIf An executable lambda function if the result of the `Or` operation is true.
 * @param whatIfNot An executable lambda function if the result of the `Or` operation is false.
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Iterable<Boolean?>.whatIfOr(
  whatIf: () -> Unit,
  whatIfNot: (() -> Unit),
): Iterable<Boolean?> {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  var predicate: Boolean? = null

  this.forEach {
    val p = predicate
    predicate = if (p == null) {
      it
    } else {
      p or (it == true)
    }
  }

  if (predicate == true) {
    whatIf()
  } else {
    whatIfNot()
  }
  return this
}
