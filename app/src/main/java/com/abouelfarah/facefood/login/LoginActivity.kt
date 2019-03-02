package com.abouelfarah.facefood.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abouelfarah.facefood.Menu
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.Signup.Signup
import maes.tech.intentanim.CustomIntent.customType
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {

            if (email_from_login.text!!.isEmpty()) {
                email_layer_from_login.error = "Please enter your email"
            } else if (pwd_login.text!!.isEmpty()) {
                pwd_layer_from_login.error = "Please enter your password"
            } else {
                email_layer_from_login.error = null
                pwd_layer_from_login.error = null

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email_from_login.text.toString(), pwd_login.text.toString())
                    .addOnSuccessListener {
                        val itents = Intent(this, Menu::class.java)
                        itents.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(itents)
                        customType(this, "bottom-to-up")

                    }.addOnFailureListener {
                        Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    }
            }

        }

        register_now.setOnClickListener {
            val int = Intent(this, Signup::class.java)
            startActivity(int)
            customType(this, "left-to-right")
        }
    }
}
