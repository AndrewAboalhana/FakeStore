package com.aa.fakestore.ui



import com.aa.fakestore.R
import com.aa.fakestore.databinding.ActivityMainBinding
import com.aa.fakestore.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(){
    override val LOG_TAG: String = "MainActivity"
    override fun getLayoutResId() = R.layout.activity_main
    override fun setup() {
    }
}