package com.abouelfarah.facefood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
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

            if (fullname.isEmpty()){
                username_layer.error = "Please enter your fullname"
            }else if(email.isEmpty()){
                email_layer.error = "Please enter your email"
            }else if(isPasswordValid(pwd)){
                pwd_layer.error = "Password must contain at least 8 characters."
            }else if(pwd == repwd){
                repwd_layer.error = ""
            }

        }
    }

    private fun fun isPasswordValid(text:String?): Boolean {
        return text != null && text.length >= 8
    }
}
