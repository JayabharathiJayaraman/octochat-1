package com.example.octochat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class SettingsActivity : AppCompatActivity() {

    lateinit var navigateBackButton : ImageView
    lateinit var changePasswordConstraint : ConstraintLayout
    lateinit var logoutConstraint : ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        navigateBackButton = findViewById(R.id.navigateBackButton)
        changePasswordConstraint = findViewById(R.id.changePasswordConstraint)
        logoutConstraint = findViewById(R.id.logoutConstraint)

        navigateBackButton.setOnClickListener {
            finish()
        }

        changePasswordConstraint.setOnClickListener{
            Log.d("!!!", "changePasswordConstraint clicked!")
        }

        logoutConstraint.setOnClickListener{
            Log.d("!!!", "logoutConstraint clicked!")
        }

    }
}