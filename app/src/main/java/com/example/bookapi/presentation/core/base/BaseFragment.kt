package com.example.bookapi.presentation.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.bookapi.BR

abstract class BaseFragment<vModel : BaseViewModel, viewDataBinding : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
    ): Fragment() {
    protected abstract val viewModel: vModel?

    protected lateinit var binding: viewDataBinding
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, layoutId, container, false);
        binding.setVariable(BR.viewModel,viewModel)
            observeViewModel()
        return binding.root
    }
    open fun init(){}
    protected abstract fun observeViewModel()

}