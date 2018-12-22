package com.example.theseus.cover

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.theseus.cover.ui.insuranceChoice.InsuranceChoice

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, InsuranceChoice.newInstance())
                .commitNow()
        }
    }

}
