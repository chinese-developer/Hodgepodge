@file:Suppress("unused")
@file:JvmName("WhatIf")
@file:JvmMultifileClass

package com.component.whatif

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * WhatIf是kotlin扩展，用于表达单个if-else陈述、可空和布尔值。
 */

/**
 * 如果 [given] 表达式为 true，则执行 [whatIf] 匿名函数
 *
 * @return Returns the original target object [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> T.whatIf(
  given: (T) -> Boolean?,
  whatIf: () -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  if (given(this) == true) {
    whatIf()
  }
  return this
}

/**
 * 如果 [given] 返回 true，则执行 [whatIf]，否则执行 [whatIfNot]，类似于 if-else 语句。
 *
 * @return Returns the original target object [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> T.whatIf(
  given: (T) -> Boolean?,
  whatIf: () -> Unit,
  whatIfNot: () -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (given(this) == true) {
    whatIf()
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 类似于 [apply] 函数，在 [given] 为 true 时执行 [whatIf]，这在链式调用中非常有用。
 *
 * @return Returns the original target object [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> T.whatIf(
  given: Boolean?,
  whatIf: T.() -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  if (given == true) {
    this.apply { whatIf() }
  }
  return this
}

/**
 * 类似于 [apply] 函数，在 [given] 为 true 时执行 [whatIf]，否则执行 [whatIfNot]，这在链式调用中非常有用。
 *
 * @return Returns the original target object [T].
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> T.whatIf(
  given: Boolean?,
  whatIf: T.() -> Unit,
  whatIfNot: T.() -> Unit,
): T {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (given == true) {
    this.apply { whatIf() }
  } else {
    this.apply { whatIfNot() }
  }
  return this
}

/**
 * 当目标对象[Boolean]不为空且为真时，用于调用[whatIf]的表达。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Boolean?.whatIf(
  whatIf: () -> Unit,
): Boolean? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIf(
    whatIf = whatIf,
    whatIfNot = { },
  )
}

/**
 * 当目标对象[Boolean]不为空且为真时，用于调用[whatIf]的表达。
 * 如果目标为空或假，将调用[whatIfNot]。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Boolean?.whatIf(
  whatIf: () -> Unit,
  whatIfNot: () -> Unit,
): Boolean? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this == true) {
    whatIf()
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当 [T] 不为 null 时，执行 [whatIf] 并返回其结果，否则返回 [default] 值，适用于需要在条件满足时进行转换的情况。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T, R> T?.whatIfNotNullWith(
  default: R,
  whatIf: (T) -> R,
): R {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIfNotNullWith(
    whatIf = whatIf,
    whatIfNot = { default },
  )
}

/**
 * 如果[given]条件通过，执行[whatIf]并返回其结果，
 * 如果[given]条件未通过，则返回[default]值。
 * 当接收者[T]和结果[R]不同时，它很有用。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T, R> T.whatIfNotNullWith(
  given: Boolean?,
  default: R,
  whatIf: (T) -> R,
): R {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIfNotNullWith(
    given = given,
    whatIf = whatIf,
    whatIfNot = { default },
  )
}

/**
 * 如果[given]条件通过，执行[whatIf]并返回其结果，
 * 如果[given]条件未通过，执行[whatIfNot]并返回其结果，
 * 当接收者[T]和结果[R]不同时，它很有用。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T, R> T.whatIfNotNullWith(
  given: Boolean?,
  whatIf: (T) -> R,
  whatIfNot: (T) -> R,
): R {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (given == true) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/**
 * 当 [T] 不为 null 时执行 [whatIf]，返回原始对象[T]，用于处理可空对象的场景。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> T?.whatIfNotNull(
  whatIf: (T) -> Unit,
): T? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIfNotNull(
    whatIf = whatIf,
    whatIfNot = { },
  )
}

/**
 * 当[T]目标对象不为空时调用[whatIf]的一个表达。
 * 如果[T]目标为空，则将调用[whatIfNot]。
 * 最后返回原始对象[T]
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> T?.whatIfNotNull(
  whatIf: (T) -> Unit,
  whatIfNot: () -> Unit,
): T? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null) {
    whatIf(this)
    return this
  }
  whatIfNot()
  return this
}

@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T, R> T?.whatIfNotNullWith(
  whatIf: (T) -> R,
  whatIfNot: (T?) -> R,
): R {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null) {
    return whatIf(this)
  }
  return whatIfNot(this)
}

/**
 * 当[Any]目标对象不为 null 且可以被转换为类型 [R] 时执行 [whatIf]，用于类型安全的类型转换操作。
 *
 * ```
 * parcelable.whatIfNotNullAs<Poster> { poster ->
 *  log(poster.name)
 * }
 * ```
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified R> Any?.whatIfNotNullAs(
  whatIf: (R) -> Unit,
): Any? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return whatIfNotNullAs(
    whatIf = whatIf,
    whatIfNot = { },
  )
}

/**
 * 当[Any]目标对象不为 null 且可以被转换为类型 [R] 时执行 [whatIf]，用于类型安全的类型转换操作。
 * 如果目标为空，则将调用[whatIfNot]。
 *
 * ```
 *  serializable.whatIfNotNullAs<Poster>(
 *  whatIf = { poster -> log(poster.name) },
 *  whatIfNot = {
 *    // do something
 *  })
 * ```
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <reified R> Any?.whatIfNotNullAs(
  whatIf: (R) -> Unit,
  whatIfNot: () -> Unit,
): Any? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this is R) {
    whatIf(this as R)
    return this
  }
  whatIfNot()
  return this
}

/**
 * 当目标对象[Boolean]不为空且为假时，用于调用[whatIf]的表达。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Boolean?.whatIfElse(
  whatIf: () -> Unit,
): Boolean? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  if (this == false) {
    whatIf()
  }
  return this
}

/**
 * 当目标对象[Boolean]为真且[predicate]也为真时，用于调用[whatIf]的一个公式。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Boolean?.whatIfAnd(
  predicate: Boolean?,
  whatIf: () -> Unit,
): Boolean? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  if (this == true && predicate == true) {
    whatIf()
  }
  return this
}

/**
 * 当目标对象[Boolean]为真或[predicate]为真时，用于调用[whatIf]的一个公式。
 *
 * @return Returns the desired type of object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun Boolean?.whatIfOr(
  predicate: Boolean?,
  whatIf: () -> Unit,
): Boolean? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  if (this == true || predicate == true) {
    whatIf()
  }
  return this
}
