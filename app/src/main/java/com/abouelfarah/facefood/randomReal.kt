package com.abouelfarah.facefood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.abouelfarah.facefood.models.food
import com.abouelfarah.facefood.models.foodTemp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_random.*



class randomReal : AppCompatActivity() {

    /*fun thla(){

        val appBarLayout = findViewById(R.id.app_bar_layout) as AppBarLayout
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, offset ->
            val upArrow = ResourcesCompat.getDrawable(resources, R.drawable.drawer_icon, null)
            if (offset < -200) {
                upArrow!!.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP)
                supportActionBar!!.setHomeAsUpIndicator(upArrow)

                val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.option_menu_icon)
                drawable!!.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP)
                toolbar.setOverflowIcon(drawable)
            } else {

                upArrow!!.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP)
                supportActionBar!!.setHomeAsUpIndicator(upArrow)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)

                val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.option_menu_icon)
                drawable!!.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP)
                toolbar.setOverflowIcon(drawable)
            }
        })


    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        val food = intent.getParcelableExtra<food>("wholeObject")

        val toolbar = findViewById(R.id.hmam) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
                onBackPressed()
                //CustomIntent.customType(it.context, "right-to-left")
        }


        foodDescription.setText(food.foodDescription)
        Picasso.get().load(food.foodImage).into(foodPicture)
        foodName.title = food.foodName
    }
}
