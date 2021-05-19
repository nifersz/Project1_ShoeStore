package com.udacity.shoestore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var floatingAdd: FloatingActionButton
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )
        Timber.plant(Timber.DebugTree())
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        floatingAdd = binding.fabAdd

        navController = getNavController()
        setupActionBar(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig)
            || super.onSupportNavigateUp()
    }

    private fun getNavController() = (supportFragmentManager.findFragmentById(
        R.id.nav_host_fragment
    ) as NavHostFragment).navController

    private fun setupActionBar(withNavController: NavController) {
        appBarConfig = AppBarConfiguration(withNavController.graph)
        setupActionBarWithNavController(withNavController, appBarConfig)
    }

    fun addFABClick(onClickListener: (View) -> Unit) {
        floatingAdd.setOnClickListener(onClickListener)
    }

    fun hideFAButton() {
        floatingAdd.visibility = View.GONE
    }

    fun navigateTo(fragmentNavAction: Int) {
        navController.navigate(fragmentNavAction)
    }

    fun showFAButton() {
        floatingAdd.visibility = View.VISIBLE
    }

}
