package com.example.foodapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<D: ViewDataBinding>(
    private val inflateLayout: (LayoutInflater, ViewGroup?, Boolean) -> D
): Fragment(){


    private var _fragmentDataBinding: D? = null
    protected val fragmentDataBinding get() = _fragmentDataBinding!!

    open fun Bundle.getArgumentsToVariable() {}

    abstract fun getLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getArgumentsToVariable()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDataBinding = inflateLayout(layoutInflater, container, false)
        return fragmentDataBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentDataBinding = null
    }

    protected fun clearBinding() {
        _fragmentDataBinding = null
    }
}
