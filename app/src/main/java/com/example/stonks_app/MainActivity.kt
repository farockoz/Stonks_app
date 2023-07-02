package com.example.stonks_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stonks_app.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.Activity_main, MainFragment.newInstance())
            .commit()
    }
}