@file:Suppress("SpellCheckingInspection")

package com.component.bindables

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/**
 * A binding interface that can be observed and notify changed properties.
 * This interface should be used with [PropertyChangeRegistry] and [androidx.databinding.Bindable] annotation
 * that can be applied to any getter accessor method of an [androidx.databinding.Observable].
 */
public interface BindingObservable : Observable {
  /**
   * Notifies a specific property has changed that matches in [PropertyChangeRegistry].
   * This function receives a [androidx.databinding.Bindable] property and if there is a change notification of any of the
   * listed properties, this value will be refreshed.
   *
   * @param property A [androidx.databinding.Bindable] property that should be changed.
   */
  public fun notifyPropertyChanged(property: KProperty<*>)

  /**
   * Notifies a specific property has changed that matches in [PropertyChangeRegistry].
   * This function receives a [androidx.databinding.Bindable] function and if there is a change notification of any of the
   * listed properties, this value will be refreshed.
   *
   * @param function A [androidx.databinding.Bindable] function that should be changed.
   */
  public fun notifyPropertyChanged(function: KFunction<*>)

  /**
   * Notifies a specific property has changed that matches in [PropertyChangeRegistry].
   * This function receives a data-binding id depending on its property name and if there is a change
   * notification of any of the listed properties, this value will be refreshed.
   *
   * @param bindingId A specific data-binding id (generated BR id) that should be changed.
   */
  public fun notifyPropertyChanged(bindingId: Int)

  /**
   * Notifies listeners that all properties of this instance have changed.
   */
  public fun notifyAllPropertiesChanged()

  /**
   * Clears all binding properties from the callback registry.
   */
  public fun clearAllProperties()
}
