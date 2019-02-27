package com.abouelfarah.facefood

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.abouelfarah.facefood.fragment.favorite_fragment
import com.abouelfarah.facefood.fragment.menu_fragment
import com.abouelfarah.facefood.fragment.share_fragment
import com.abouelfarah.facefood.login.LoginActivity
import com.abouelfarah.facefood.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.nav_header.*

class Menu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawer: DrawerLayout? = null

    private fun updateUserUI() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                val user = p0.getValue(User::class.java)
                Log.d("USERXXX", p0.toString())
                if (user != null) {
                    email_from_menu.text = user!!.email
                    if(user!!.firstName == null || user!!.lastName == null){
                        updateUserUI()
                    }else{
                        username_from_menu.text = "${user!!.firstName} ${user!!.lastName}"
                    }

                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }

    private fun verifyLogin() {
        Log.d("VERIFYING", "IT4S DOING")
        val uid = FirebaseAuth.getInstance().currentUser
        //Log.d("VERIFYING", uid)
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
        val dialog:Dialog?=null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        verifyLogin()

        val tlb: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(tlb)

        drawer = findViewById(R.id.drawer_layer)

        var navigationViewer:NavigationView = findViewById(R.id.nav_view)

        val headerview:View = navigationViewer.getHeaderView(0)
        var header = headerview.findViewById<LinearLayout>(R.id.header)

        header.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v:View) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, update_img()).commit()
                drawer!!.closeDrawer(GravityCompat.START)
            }
        })

        navigationViewer.setNavigationItemSelectedListener(this)
//        navigationViewer.setCheckedItem(R.id.menuOfFood)
//        navigationViewer.menu.getItem(0).setChecked(true)
        onNavigationItemSelected(navigationViewer.menu.getItem(0).setChecked(true))

        val toogle =
            ActionBarDrawerToggle(this, drawer, tlb, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer!!.addDrawerListener(toogle)
        toogle.syncState()

        updateUserUI()



//        profile_img_from_menu.setOnClickListener {
//            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, update_img()).commit()
//        }

        //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, menu_fragment()).commit()

        //verifyLogin()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId){
            // R.id.profile_img_from_menu -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, update_img()).commit()
            R.id.menuOfFood -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, menu_fragment()).commit()
            R.id.specialOffer -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, favorite_fragment()).commit()
            R.id.shared_picture -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, share_fragment()).commit()
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                val int = Intent(this, LoginActivity::class.java)
                int.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(int)
            }
        }

        drawer!!.closeDrawer(Gravity.START)
        return true
    }

}
