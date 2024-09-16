package com.component.whatif

/** 限定在内联的情况下才能调用此函数。 */
@Target(
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER,
)
@DslMarker
@Retention(AnnotationRetention.BINARY)
internal annotation class WhatIfInlineOnly
