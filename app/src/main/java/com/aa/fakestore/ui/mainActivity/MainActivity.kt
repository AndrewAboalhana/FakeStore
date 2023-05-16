package com.aa.fakestore.ui.mainActivity



import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.aa.fakestore.R
import com.aa.fakestore.databinding.ActivityMainBinding
import com.aa.fakestore.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(){
    override val LOG_TAG: String = "MainActivity"
    override fun getLayoutResId() = R.layout.activity_main


    override fun onResume() {
        super.onResume()
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun setup() {
    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp()
                || super.onSupportNavigateUp()
    }


}