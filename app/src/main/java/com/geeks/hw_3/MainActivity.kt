package com.geeks.hw_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.geeks.hw_3.data.local.Pref
import com.geeks.hw_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = Pref(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        if (pref.isOnBoardingShown()) {
            navGraph.setStartDestination(R.id.mainFragment)
        } else {
            navGraph.setStartDestination(R.id.onBoardFragment)
        }

        navController.graph = navGraph
    }
}