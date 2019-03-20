package com.abouelfarah.facefood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.abouelfarah.facefood.models.foodTemp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_random.*

class random : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        val food = intent.getParcelableExtra<foodTemp>("wholeObject")

        val toolbar = findViewById(R.id.hmam) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        toolbar.setNavigationOnClickListener {
                onBackPressed()
        }


        foodDescription.setText(food.foodDescription)
        Picasso.get().load(food.foodImage).into(foodPicture)
        foodName.title = food.foodName
    }
}
