package com.abouelfarah.facefood

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup2.*

class Signup2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)

        val first_name = intent.getStringExtra("firstName")
        val last_name = intent.getStringExtra("lastName")


        almost_there.text = "Almost there ${first_name.toUpperCase()} !!"

        finish.setOnClickListener {
            val email = intent.getStringExtra("email")
            val pwd = pwd_from_signup2.text.toString()
            val repwd = re_pwd_from_signup2.text.toString()

            Log.d("THaT_EMPTY_STRINGS", "$first_name $last_name $email $pwd  $repwd")
                if (pwd.isNotEmpty() && repwd.isNotEmpty()) {
                    if (pwd == repwd) {
                        //Toast.makeText(this, "I'm Writing code after connecting to the net :')", Toast.LENGTH_SHORT).show()

                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pwd).addOnCompleteListener {
                            if (!it.isSuccessful) return@addOnCompleteListener
                            Log.d("Registration", "Shit Is done with id : ${it.result!!.user.uid}")

                            saveUserInDataBase(it.result!!.user.uid)

                            val it = Intent(this, Menu::class.java)
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(it)

                        }.addOnFailureListener {
                            if ("password" in it.message!!.toLowerCase()) {
                                pwd_layer.error = "Password must have at lease 8 characters"
                            } else {
                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        re_pwd_layer.error = "Password are not the SAME"
                    }
                }else{
                    Toast.makeText(this, "There is something wrong :(((", Toast.LENGTH_SHORT).show()
                }
        }

        back.setOnClickListener {
            val int = Intent(this, Signup::class.java)
            startActivity(int)
        }
    }


    private fun saveUserInDataBase(uid:String){
        val ref = FirebaseDatabase.getInstance().getReference("/users/${uid}")
        val user = User(
            uid,
            first_name.text.toString(),
            last_name.text.toString(),
            email.text.toString(),
            pwd_from_signup2.text.toString()
        )
        ref.setValue(user).addOnSuccessListener {
            val it = Intent(this, Menu::class.java)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(it)
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}


class User(val uid: String, val FirstName:String, val LastName:String, val email:String, val pwd:String){
    constructor() : this("", "", "", "", "")
}