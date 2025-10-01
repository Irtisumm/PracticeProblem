package com.example.practiceproblem

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.PasswordTransformationMethod
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practiceproblem.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        styleSignUpText()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupClickListeners() {
        // Back Arrow Click
        binding.backArrow.setOnClickListener {
            finish() // Closes the current activity
        }

        // Sign In Button Click
        binding.buttonSignIn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // TODO: Add your authentication logic here
                Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Forgot Password Click
        binding.linkForgotPassword.setOnClickListener {
            // TODO: Navigate to Forgot Password screen
            Toast.makeText(this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }

        // Google Sign In Click
        binding.buttonGoogleSignIn.setOnClickListener {
            // TODO: Implement Google Sign-In logic
            Toast.makeText(this, "Google Sign-In Clicked", Toast.LENGTH_SHORT).show()
        }

        // Password Visibility Toggle
        // We use a custom touch listener on the EditText to detect clicks on the drawable
        binding.inputPassword.setOnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == android.view.MotionEvent.ACTION_UP) {
                if (event.rawX >= (binding.inputPassword.right - binding.inputPassword.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                    togglePasswordVisibility()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Hide password
            binding.inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            binding.inputPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0)
        } else {
            // Show password
            binding.inputPassword.transformationMethod = null // null shows the text
            binding.inputPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_on, 0)
        }
        isPasswordVisible = !isPasswordVisible
        // Move cursor to the end
        binding.inputPassword.setSelection(binding.inputPassword.text.length)
    }

    private fun styleSignUpText() {
        val fullText = "Don't have an account? Sign Up"
        val spannableString = SpannableString(fullText)

        val startIndex = fullText.indexOf("Sign Up")
        val endIndex = startIndex + "Sign Up".length

        // Set the color for the "Sign Up" part
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#4169E1")), // Same blue as the button
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textSignUpPrompt.text = spannableString

        // Make the "Sign Up" part clickable
        binding.textSignUpPrompt.setOnClickListener {
            // Create an instruction to open SignUpActivity
            val intent = Intent(this, SignUpActivity::class.java)

            // Execute the instruction
            startActivity(intent)
        }
    }
}