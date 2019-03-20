package com.abouelfarah.facefood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.abouelfarah.facefood.models.foodTemp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_random.*

class random : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        val food = intent.getParcelableExtra<foodTemp>("wholeObject")


        foodDescription.setText(food.foodDescription)
        Picasso.get().load(food.foodImage).into(foodPicture)
        foodName.title = food.foodName
    }
}
