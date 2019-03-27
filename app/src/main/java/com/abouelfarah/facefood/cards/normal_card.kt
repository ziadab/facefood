package com.abouelfarah.facefood.cards

import android.content.Intent
import android.support.v4.content.ContextCompat
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.models.food
import com.abouelfarah.facefood.random
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.other_type_of_card.view.*

class normal_card(val food: food) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.other_type_of_card
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(food.foodImage).into(viewHolder.itemView.foodImage2)
        viewHolder.itemView.foodName2.setText(food.foodName.toUpperCase())
        viewHolder.itemView.descriptionFood2.setText(food.foodDescription)


        viewHolder.itemView.StevenCard2.setOnClickListener {
            val int = Intent(it.context, random::class.java)
            int.putExtra("wholeObject", food)
            ContextCompat.startActivity(it.context, int, null)
            //customType(it.context, "left-to-right")
        }
    }
}
