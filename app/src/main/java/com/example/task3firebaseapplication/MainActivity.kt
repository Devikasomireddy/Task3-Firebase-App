package com.example.task3firebaseapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var signupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)
        signupBtn = findViewById(R.id.signupBtn)

        // SIGNUP
        signupBtn.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        // LOGIN
        loginBtn.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                            // ✅ NAVIGATION
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}