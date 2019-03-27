package com.abouelfarah.facefood

import android.content.Intent
import android.support.v4.content.ContextCompat
import com.abouelfarah.facefood.models.foodTemp
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.other_type_of_card_reverse.view.*

class menu_food_reverse(val food : foodTemp) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.other_type_of_card_reverse
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(food.foodImage).into(viewHolder.itemView.foodImage3)
        viewHolder.itemView.foodName3.setText(food.foodName.toUpperCase())
        viewHolder.itemView.descriptionFood3.setText(food.foodDescription)


        viewHolder.itemView.StevenCard3.setOnClickListener {
            val int = Intent(it.context, random::class.java)
            int.putExtra("wholeObject", food)
            ContextCompat.startActivity(it.context, int, null)
            //customType(it.context, "left-to-right")
        }
    }
}