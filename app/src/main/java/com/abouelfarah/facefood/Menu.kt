package com.abouelfarah.facefood

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import com.abouelfarah.facefood.fragment.favorite_fragment
import com.abouelfarah.facefood.fragment.menu_fragment
import com.abouelfarah.facefood.fragment.share_fragment

class Menu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var drawer: DrawerLayout? = null



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

        //verifyLogin()

        val tlb: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(tlb)

        drawer = findViewById(R.id.drawer_layer)

        val navigationViewer: NavigationView = findViewById(R.id.nav_view)

//        val headerview: View = navigationViewer.getHeaderView(0)

//        header.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View) {
//                val intss = Intent(this@Menu, update_img::class.java)
//                startActivity(intss)
//                drawer!!.closeDrawer(GravityCompat.START)
//            }
//        })

        navigationViewer.setNavigationItemSelectedListener(this)
//        navigationViewer.setCheckedItem(R.id.menuOfFood)
//        navigationViewer.menu.getItem(0).setChecked(true)
        onNavigationItemSelected(navigationViewer.menu.getItem(0).setChecked(true))

        val toogle =
            ActionBarDrawerToggle(this, drawer, tlb, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer!!.addDrawerListener(toogle)
        drawer!!.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(p0: Int) {

            }

            override fun onDrawerSlide(p0: View, p1: Float) {
            }

            override fun onDrawerClosed(p0: View) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val windowsh = window
                windowsh.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                windowsh.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                windowsh.statusBarColor = ContextCompat.getColor(p0.context, R.color.colorPrimaryDark)
            }
            }


            override fun onDrawerOpened(p0: View) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val windowsh = window
                windowsh.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                windowsh.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                windowsh.statusBarColor = ContextCompat.getColor(p0.context, R.color.hmmm)

            }
            }
        })
        toogle.syncState()


//        updateUserUI()

//        profile_img_from_menu.setOnClickListener {
//            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, update_img()).commit()
//        }

        //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, menu_fragment()).commit()

        //verifyLogin()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        val windowsh = window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                windowsh.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                windowsh.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                windowsh.statusBarColor = ContextCompat.getColor(this, R.color.hmmm)
            }

        when (p0.itemId) {
            // R.id.profile_img_from_menu -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, update_img()).commit()
            R.id.menuOfFood -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                menu_fragment()
            ).commit()
            R.id.specialOffer -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                favorite_fragment()
            ).commit()
            R.id.shared_picture -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                share_fragment()
            ).commit()
            R.id.about_us ->startActivity(Intent(this, aboutUs::class.java))



//                FirebaseAuth.getInstance().signOut()
//                val int = Intent(this, LoginActivity::class.java)
//                int.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                startActivity(int)

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            windowsh.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
        drawer!!.closeDrawer(Gravity.START)
        return true
    }
}
