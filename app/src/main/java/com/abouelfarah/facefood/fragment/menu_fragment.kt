package com.abouelfarah.facefood.fragment

//import android.support.v7.widget.GridLayoutManager
//import android.transition.Slide
//import com.abouelfarah.facefood.detail
//import kotlinx.android.synthetic.main.food_card.view.*
import com.abouelfarah.facefood.models.foodTemp
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.random
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.food_card.view.*
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.Random

class menu_fragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapters = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }

        val foodList  =ArrayList<foodTemp>()
        foodList.add(foodTemp(R.drawable.i1, "Like", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.i2, "Haha", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.i3, "Grr", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.steven_universe, "Steven Universe", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.thla, "THLA", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.i4, "CJ", R.string.thlaText))

        var r = Random()
        for (i in 0..10) {
            val randomX = r.nextInt(foodList.size)
            val obj:foodTemp = foodList[randomX]
            adapters.add(menu_food(obj))
        }
        recyclerViewer_from_fragment_menu.adapter = adapters

    }

//        recyclerViewer_from_fragment_menu.adapter = adapters
//        recyclerViewer_from_fragment_menu.apply {
//            layoutManager = GridLayoutManager(this@menu_fragment.context, adapters.spanCount).apply {
//                spanSizeLookup = adapters.spanSizeLookup
//            }
//        }.adapter = adapters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
}

class menu_food(val food : foodTemp) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.food_card
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load(food.foodImage).into(viewHolder.itemView.foodImage)
        viewHolder.itemView.foodName.setText(food.foodName.toUpperCase())
        viewHolder.itemView.descriptionFood.setText(food.foodDescription)


        viewHolder.itemView.StevenCard.setOnClickListener {
            val int = Intent(it.context, random::class.java)
            int.putExtra("wholeObject", food)
            startActivity(it.context, int, null)
            //customType(it.context, "left-to-right")
        }
    }
}