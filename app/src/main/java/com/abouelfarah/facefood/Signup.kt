package com.abouelfarah.facefood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.signup_activity.*

class Signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        register_now.setOnClickListener {

            var fullname = username.text.toString()
            var email = email.text.toString()
            var pwd = pwd.text.toString()
            var repwd = repws.text.toString()

            if(email.isEmpty() || pwd.isEmpty() || repwd.isEmpty()){
                Toast.makeText(this, "Please check your email or your password", Toast.LENGTH_SHORT).show()
                if(fullname.isEmpty()){

                }
            }

        }
    }
}
