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
        hideActionBar()
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun setup() {
    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun hideActionBar() {
        findNavController(R.id.nav_host_fragment_content_main)
            .addOnDestinationChangedListener { _, destination, arguments ->
            when (destination.id) {
                R.id.testFragment -> {
                    this.supportActionBar?.hide()
                }
                else -> {
                    supportActionBar?.show()
                }
            }
        }
    }
}