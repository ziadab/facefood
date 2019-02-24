package com.abouelfarah.facefood

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth

class Menu : AppCompatActivity() {

    var drawer: DrawerLayout? = null

    private fun verifyLogin() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val it = Intent(this, LoginActivity::class.java)
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(it)
        }
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tlb: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(tlb)

        drawer = findViewById(R.id.drawer_layer)

        val toogle =
            ActionBarDrawerToggle(this, drawer, tlb, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer!!.addDrawerListener(toogle)
        toogle.syncState()



        verifyLogin()
    }
}
