package com.abouelfarah.facefood

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sorry_no_net.*

class Sorry_no_net : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorry_no_net)

        try_again_button_in_no_net.setOnClickListener {
            if (checkthenet()){
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                //Toast.makeText(this, "يا ايها الزنج الابيض هل تتمسخر معي ؟", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Sorry, we could not connect to the internet :')", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkthenet():Boolean{
        val connectivityManager=baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}
