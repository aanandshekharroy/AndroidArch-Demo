package com.example.theseus.cover

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.theseus.cover.ui.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() , OnFragmentInteractionListener{
    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun setProgressBarToComplete() {
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 100
    }

    override fun setProgressBarToHalf() {
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    private fun setupNavigation() {
        val navigationController = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navigationController)
    }

}
