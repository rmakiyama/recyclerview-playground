package com.rmakiyama.recyclerviewplayground.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.databinding.ActivityMainBinding
import com.rmakiyama.recyclerviewplayground.ui.home.HomeFragmentDirections
import dagger.android.support.DaggerAppCompatActivity
import java.util.*

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            onDestinationChange(destination)
        }

        binding.fab.setOnClickListener {
            navController.navigate(
                HomeFragmentDirections.actionHomeToDetail(
                    UUID.randomUUID().toString()
                )
            )
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onDestinationChange(destination: NavDestination) {
        when (destination.id) {
            R.id.home -> binding.fab.show()
            else -> binding.fab.hide()
        }
    }
}
