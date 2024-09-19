package com.component.bindables

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

/**
 * Base class for dialog fragments that wish to bind content layout with [DataBindingUtil].
 * Provides a [binding] property that extends [ViewDataBinding] from abstract information.
 * The [binding] property ensures to be initialized in [onCreateView].
 *
 * @param T A generic class that extends [ViewDataBinding] and generated by DataBinding on compile time.
 * @property contentLayoutId A content layout Id for inflating as a content view.
 */
abstract class BindingDialogFragment<T : ViewDataBinding> constructor(
  @LayoutRes private val contentLayoutId: Int
) : DialogFragment() {

  /** A backing field for providing an immutable [binding] property.  */
  private var _binding: T? = null

  /**
   * A data-binding property will be initialized in [onCreateView].
   * And provide the inflated view which depends on [contentLayoutId].
   */
  @BindingOnly
  protected val binding: T
    get() = checkNotNull(_binding) {
      "DialogFragment $this binding cannot be accessed before onCreateView() or after onDestroyView()"
    }

  /**
   * An executable inline binding function that receives a binding receiver in lambda.
   *
   * @param block A lambda block will be executed with the binding receiver.
   * @return T A generic class that extends [ViewDataBinding] and generated by DataBinding on compile time.
   */
  @BindingOnly
  protected inline fun binding(block: T.() -> Unit): T {
    return binding.apply(block)
  }

  /**
   * Ensures the [binding] property should be executed and provide the inflated view which depends on [contentLayoutId].
   */
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding =
      DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
    return binding.root
  }

  /**
   * Destroys the [_binding] backing property for preventing leaking the [ViewDataBinding] that references the Context.
   */
  override fun onDestroyView() {
    super.onDestroyView()
    _binding?.unbind()
    _binding = null
  }
}
