package com.aa.fakestore.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    abstract val LOG_TAG: String

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        setup()
    }

    abstract fun getLayoutResId(): Int

    abstract fun setup()

    protected fun log(value: Any) {
        Log.v(LOG_TAG, value.toString())
    }
}
