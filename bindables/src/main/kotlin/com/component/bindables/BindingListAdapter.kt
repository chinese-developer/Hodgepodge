@file:Suppress("SpellCheckingInspection")

package com.component.bindables

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/**
 * A [ListAdapter] that provides a way in which UI can be notified of changes.
 * We can register an observable property using [androidx.databinding.Bindable] annotation and
 * [bindingProperty] delegates. The getter for an observable property should be annotated with [androidx.databinding.Bindable].
 */
public abstract class BindingListAdapter<T, VH : RecyclerView.ViewHolder>(
  public val callback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(callback), BindingObservable {

  /** 在多线程环境中，防止并发访问 [propertyCallbacks] 时引发数据竞争问题。*/
  private val lock: Any = Any()

  /** 定义一个可观察的 [isSubmitted] 属性，初始值为 false。当 [isSubmitted] 值发生变化时，调用 [notifyPropertyChanged] 来通知数据绑定系统。*/
  @get:Bindable
  public var isSubmitted: Boolean = false
    private set(value) {
      if (field != value) {
        field = value
        notifyPropertyChanged(::isSubmitted)
      }
    }

  /** 用于存储绑定的属性变化回调接口，管理属性变更通知。允许多个回调同时注册，以便在属性改变时触发 UI 更新。*/
  private var propertyCallbacks: PropertyChangeRegistry? = null

  /**
   * Adds a new [Observable.OnPropertyChangedCallback] to the property registry.
   *
   * @param callback A new [Observable.OnPropertyChangedCallback] should be added.
   */
  override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks
        ?: PropertyChangeRegistry().also { propertyCallbacks = it }
      propertyCallbacks.add(callback)
    }
  }

  /**
   * Removes an old [Observable.OnPropertyChangedCallback] from the property registry.
   *
   * @param callback An old [Observable.OnPropertyChangedCallback] should be removed.
   */
  override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks ?: return@lock
      propertyCallbacks.remove(callback)
    }
  }

  /**
   * 当某个 [property] 属性发生改变时，调用此方法通知所有注册的回调。每个属性都有唯一的 bindingId，用于标识。
   *
   * @param property A property that should be changed.
   */
  override fun notifyPropertyChanged(property: KProperty<*>) {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks ?: return@lock
      propertyCallbacks.notifyCallbacks(this, property.bindingId, null)
    }
  }

  /**
   * Notifies a specific property has changed that matches in [PropertyChangeRegistry].
   * This function receives a [androidx.databinding.Bindable] function and if there is a change notification of any of the
   * listed properties, this value will be refreshed.
   *
   * @param function A [androidx.databinding.Bindable] function that should be changed.
   */
  override fun notifyPropertyChanged(function: KFunction<*>) {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks ?: return@lock
      propertyCallbacks.notifyCallbacks(this, function.bindingId, null)
    }
  }

  /**
   * 通过 BR 类生成的 [bindingId] 通知属性更改。
   *
   * @param bindingId A specific data-binding id (generated BR id) that should be changed.
   */
  override fun notifyPropertyChanged(bindingId: Int) {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks ?: return@lock
      propertyCallbacks.notifyCallbacks(this, bindingId, null)
    }
  }

  /**
   * 使用 [BR._all] 标识符通知所有注册的回调，所有属性都发生了变化。
   * 适用场景如: 数据完全重新加载或数据模型发生重大变化等。
   */
  override fun notifyAllPropertiesChanged() {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks ?: return@lock
      propertyCallbacks.notifyCallbacks(this, BR._all, null)
    }
  }

  /**
   * Submits a new list to be diffed, and displayed.
   */
  override fun submitList(list: List<T>?) {
    super.submitList(list)
    isSubmitted = list != null
  }

  /**
   * Set the new list to be displayed.
   */
  override fun submitList(list: List<T>?, commitCallback: Runnable?) {
    super.submitList(list, commitCallback)
    isSubmitted = list != null
  }

  /**
   * Clears all binding properties from the callback registry.
   */
  override fun clearAllProperties() {
    synchronized(lock) lock@{
      val propertyCallbacks = propertyCallbacks ?: return@lock
      propertyCallbacks.clear()
    }
  }

  /**
   * Clears all binding properties when adapter is detached from the recyclerView.
   */
  override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
    super.onDetachedFromRecyclerView(recyclerView)
    clearAllProperties()
  }
}
