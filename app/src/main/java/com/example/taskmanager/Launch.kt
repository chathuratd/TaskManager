package com.example.taskmanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanager.MainActivity
import com.example.notesapp.R

class Launch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        // Using a Handler to delay the start of MainActivity
        Handler().postDelayed({
            // Create an Intent to start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Start MainActivity
            startActivity(intent)
            // Finish Launch activity to prevent it from appearing when back button is pressed
            finish()
        }, 2000) // Delay in milliseconds (2 seconds)
    }
}
