package com.example.yourapp 
import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.PasswordTransformationMethod
import android.text.style.StyleSpan
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yourapp.databinding.ActivitySignUpBinding 

class SignUpActivity : AppCompatActivity() {

    // Make sure your XML file is named 'activity_sign_up.xml' for this binding to be generated
    private lateinit var binding: ActivitySignUpBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        styleTermsAndConditionsText()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupClickListeners() {
        // Back Arrow Click
        binding.backArrow.setOnClickListener {
            finish() // Closes the current activity and returns to the previous one
        }

        // Create Account Button Click
        binding.buttonCreateAccount.setOnClickListener {
            val fullName = binding.inputFullName.text.toString()
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // TODO: Add your user registration logic here
                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Google Sign Up Click
        binding.buttonGoogleSignUp.setOnClickListener {
            // TODO: Implement Google Sign-Up logic
            Toast.makeText(this, "Google Sign-Up Clicked", Toast.LENGTH_SHORT).show()
        }

        // Password Visibility Toggle
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

    private fun styleTermsAndConditionsText() {
        val fullText = "By signing up you agree to our Terms and Conditions of Use"
        val spannableString = SpannableString(fullText)

        // Make "Terms" bold
        val termsStartIndex = fullText.indexOf("Terms")
        val termsEndIndex = termsStartIndex + "Terms".length
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            termsStartIndex,
            termsEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Make "Conditions of Use" bold
        val conditionsStartIndex = fullText.indexOf("Conditions of Use")
        val conditionsEndIndex = conditionsStartIndex + "Conditions of Use".length
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            conditionsStartIndex,
            conditionsEndIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textTerms.text = spannableString
    }
}