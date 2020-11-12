package com.example.octochat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout


class SettingsActivity : AppCompatActivity() {

     var linerAcc : LinearLayout ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

    linerAcc = findViewById(R.id.layoutAccount)
        linerAcc?.setOnClickListener{
            val intent = Intent(this, Account::class.java)
            startActivity(intent)



        }
    }
}