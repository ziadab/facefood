package com.abouelfarah.facefood.Signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.abouelfarah.facefood.login.LoginActivity
import com.abouelfarah.facefood.R
import kotlinx.android.synthetic.main.activity_signup.*
import maes.tech.intentanim.CustomIntent

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        next_button.setOnClickListener {

            var firstName = first_name.text.toString()
            var lastName = last_name.text.toString()
            var email = email.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()) {
                val int = Intent(this, Signup2::class.java)
                int.putExtra("firstName", firstName)
                int.putExtra("lastName", lastName)
                int.putExtra("email", email)
                startActivity(int)
                CustomIntent.customType(this, "left-to-right")
            } else {
                if (firstName.isEmpty()) {
                    firstName_layer.error = "Please check your first name"
                } else if (email.isEmpty()) {
                    email_layer.error = "Please check your email"
                } else if (lastName.isEmpty()) {
                    lastName_layer.error = "Please check your last name"
                } else {
                    firstName_layer.error = null
                    email_layer.error = null
                    lastName_layer.error = null
                }
            }
        }

        login_from_signup.setOnClickListener {
            val int = Intent(this, LoginActivity::class.java)
            startActivity(int)
            CustomIntent.customType(this, "right-to-left")
        }
    }
}
