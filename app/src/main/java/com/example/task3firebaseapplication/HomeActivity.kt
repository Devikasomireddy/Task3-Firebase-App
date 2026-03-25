package com.example.task3firebaseapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        val welcomeText = findViewById<TextView>(R.id.welcomeText)

        val user = FirebaseAuth.getInstance().currentUser
        welcomeText.text = "Welcome ${user?.email}"

        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}