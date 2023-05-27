package com.example.bookapi.presentation.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<Binding : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
    ) : AppCompatActivity() {

    protected lateinit var binding: Binding
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preSetContentViewInit()
        binding = DataBindingUtil.setContentView(this, layoutId)
        init()
        observeViewModel()
    }
    open fun preSetContentViewInit(){}
    open fun init() {}

    open fun observeViewModel(){}

}