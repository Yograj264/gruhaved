package com.logicalsolutions.gruhaved.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.logicalsolutions.gruhaved.R
import com.logicalsolutions.gruhaved.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val intent = Intent(
//            this@Main2Activity,
//            OrderListActivity::class.java
//        )
//        startActivity(intent)
//        finish()
//
//        val intent = Intent(
//            this@Main2Activity,
//            ProductMasterActivity::class.java
//        )
//        startActivity(intent)
//        finish()


//        //create order button
//        val intent = Intent(this@Main2Activity, OrderActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}