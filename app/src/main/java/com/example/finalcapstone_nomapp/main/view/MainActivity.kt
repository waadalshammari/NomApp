package com.example.finalcapstone_nomapp.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
// global variables for binding and nav
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        // for bottom nav menu
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)

    }

    }
