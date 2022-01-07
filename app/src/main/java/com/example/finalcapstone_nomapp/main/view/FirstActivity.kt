package com.example.finalcapstone_nomapp.main.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.identity.LoginActivity


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)


         supportActionBar?.hide()

        val startButton : Button = findViewById(R.id.start_button)

        startButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

    }
}}