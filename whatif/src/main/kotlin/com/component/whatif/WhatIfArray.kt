@file:JvmName("WhatIfArray")
@file:JvmMultifileClass

package com.component.whatif

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 当[Array]不为null且至少有一个数据时，调用[whatIf]表达式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> Array<out T>?.whatIfNotNullOrEmpty(
  whatIf: (Array<out T>) -> Unit,
): Array<out T>? {
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
 * 当[Array]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，则将调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun <T> Array<out T>?.whatIfNotNullOrEmpty(
  whatIf: (Array<out T>) -> Unit,
  whatIfNot: () -> Unit,
): Array<out T>? {
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
 * 当[ByteArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun ByteArray?.whatIfNotNullOrEmpty(
  whatIf: (ByteArray) -> Unit,
): ByteArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
  }
  return this.whatIfNotNullOrEmpty(
    whatIf = whatIf,
    whatIfNot = { },
  )
}

/**
 * 当[ByteArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun ByteArray?.whatIfNotNullOrEmpty(
  whatIf: (ByteArray) -> Unit,
  whatIfNot: () -> Unit,
): ByteArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[ShortArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun ShortArray?.whatIfNotNullOrEmpty(
  whatIf: (ShortArray) -> Unit,
): ShortArray? {
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
 * 当[ShortArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun ShortArray?.whatIfNotNullOrEmpty(
  whatIf: (ShortArray) -> Unit,
  whatIfNot: () -> Unit,
): ShortArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[IntArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun IntArray?.whatIfNotNullOrEmpty(
  whatIf: (IntArray) -> Unit,
): IntArray? {
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
 * 当[IntArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun IntArray?.whatIfNotNullOrEmpty(
  whatIf: (IntArray) -> Unit,
  whatIfNot: () -> Unit,
): IntArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[LongArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun LongArray?.whatIfNotNullOrEmpty(
  whatIf: (LongArray) -> Unit,
): LongArray? {
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
 * 当[LongArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun LongArray?.whatIfNotNullOrEmpty(
  whatIf: (LongArray) -> Unit,
  whatIfNot: () -> Unit,
): LongArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[FloatArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun FloatArray?.whatIfNotNullOrEmpty(
  whatIf: (FloatArray) -> Unit,
): FloatArray? {
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
 * 当[FloatArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun FloatArray?.whatIfNotNullOrEmpty(
  whatIf: (FloatArray) -> Unit,
  whatIfNot: () -> Unit,
): FloatArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[DoubleArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun DoubleArray?.whatIfNotNullOrEmpty(
  whatIf: (DoubleArray) -> Unit,
): DoubleArray? {
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
 * 当[DoubleArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun DoubleArray?.whatIfNotNullOrEmpty(
  whatIf: (DoubleArray) -> Unit,
  whatIfNot: () -> Unit,
): DoubleArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[BooleanArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun BooleanArray?.whatIfNotNullOrEmpty(
  whatIf: (BooleanArray) -> Unit,
): BooleanArray? {
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
 * 当[BooleanArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun BooleanArray?.whatIfNotNullOrEmpty(
  whatIf: (BooleanArray) -> Unit,
  whatIfNot: () -> Unit,
): BooleanArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}

/**
 * 当[CharArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun CharArray?.whatIfNotNullOrEmpty(
  whatIf: (CharArray) -> Unit,
): CharArray? {
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
 * 当[CharArray]不为null且至少有一个数据时，调用[whatIf]的一个公式。
 * 反之，调用[whatIfNot]。
 *
 * @return Returns the original target object.
 */
@JvmSynthetic
@WhatIfInlineOnly
public inline fun CharArray?.whatIfNotNullOrEmpty(
  whatIf: (CharArray) -> Unit,
  whatIfNot: () -> Unit,
): CharArray? {
  contract {
    callsInPlace(whatIf, InvocationKind.AT_MOST_ONCE)
    callsInPlace(whatIfNot, InvocationKind.AT_MOST_ONCE)
  }
  if (this != null && this.isNotEmpty()) {
    whatIf(this)
  } else {
    whatIfNot()
  }
  return this
}
