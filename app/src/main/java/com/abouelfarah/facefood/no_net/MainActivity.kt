package com.abouelfarah.facefood.no_net

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.abouelfarah.facefood.Menu
import com.abouelfarah.facefood.R
import maes.tech.intentanim.CustomIntent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable {
            if (checkthenet()) {
                var intent = Intent(this, Menu::class.java)
                startActivity(intent)
                CustomIntent.customType(this, "bottom-to-up")
                finish()
            } else {
                var intent = Intent(this, Sorry_no_net::class.java)
                startActivity(intent)
                CustomIntent.customType(this, "bottom-to-up")
                finish()
            }

        }, 2000)
    }

    fun checkthenet(): Boolean {
        val connectivityManager = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
