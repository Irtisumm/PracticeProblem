package com.example.practiceproblem

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceproblem.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Handle back arrow click
        binding.backArrow.setOnClickListener {
            finish() // Closes this screen and goes back to the previous one
        }

        // Handle "Next" button click
        binding.buttonNext.setOnClickListener {
            val email = binding.inputEmail.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Add your actual password reset logic here (e.g., Firebase call)
                Toast.makeText(this, "Password reset link sent to your email.", Toast.LENGTH_LONG).show()
            }
        }
    }
}