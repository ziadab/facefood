package com.abouelfarah.facefood

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup2.*

class Signup2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)

        val first_name = intent.getStringExtra("firstName")
        val last_name = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val pwd = pwd.text.toString()
        val repwd = re_pwd.text.toString()

        almost_there.text = "Almost there $first_name !!"

        finish.setOnClickListener {

                if (pwd == repwd) {
                    Toast.makeText(this, "I'm Writing code after connecting to the net :')", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd).addOnCompleteListener {
                        if(!it.isSuccessful) return@addOnCompleteListener
                        Log.d("Registration", "Shit Is done with id : ${it.result!!.user.uid}")
                    }.addOnFailureListener {
                        if("password" in it.message!!.toLowerCase()){
                            pwd_layer.error = "Password must have at lease 8 characters"
                        }else{
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    re_pwd_layer.error = "Password are not the SAME"
                }

        }



        back.setOnClickListener {
            val int = Intent(this, Signup::class.java)
            startActivity(int)
        }
    }

}
