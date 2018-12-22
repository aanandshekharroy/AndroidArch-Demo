package com.example.theseus.cover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        setSupportActionBar()
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, AutoComplete.newInstance())
//                .commitNow()
//        }
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    private fun setupNavigation() {
        val navigationController = Navigation.findNavController(this,R.id.nav_host_fragment)
//        set
//        setu
    }

}
