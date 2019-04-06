package com.abouelfarah.facefood.fragment


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.menu_food_reverse
import com.abouelfarah.facefood.cards.normal_card
import com.abouelfarah.facefood.cards.reversed_card
import com.abouelfarah.facefood.makla
import com.abouelfarah.facefood.models.food
import com.abouelfarah.facefood.models.foodTemp
import com.abouelfarah.facefood.pushTheFood
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_favorite.*

import java.util.Random

class favorite_fragment: Fragment() {

    companion object {
        val adapter = GroupAdapter<ViewHolder>()
    }

    private fun giveMeMySpecialOffer(){

        val ref = FirebaseDatabase.getInstance().getReference("/special_offer")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {

                var i:Int = 0

                p0.children.forEach {
                    if (i % 2 == 0){
                        val food = it.getValue(food::class.java)
                        if (food != null){
                            adapter.add(normal_card(food))
                        }
                    }else{
                        val food = it.getValue(food::class.java)
                        if (food != null){
                            adapter.add(reversed_card(food))
                        }
                    }
                }


            }

            override fun onCancelled(p0: DatabaseError) {

            }


        })

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tlb:Toolbar= activity!!.findViewById(R.id.toolbar)



        val foodList  =ArrayList<foodTemp>()
        foodList.add(foodTemp(R.drawable.i1, "Like", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.i2, "Haha", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.i3, "Grr", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.steven_universe, "Steven Universe", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.thla, "THLA", R.string.thlaText))
        foodList.add(foodTemp(R.drawable.i4, "CJ", R.string.thlaText))

        var r = Random()
        for (i in 0..5) {
            var randomX = r.nextInt(foodList.size)
            val obj:foodTemp = foodList[randomX]
            adapter.add(makla(obj))

            randomX = r.nextInt(foodList.size)
            val hmm:foodTemp = foodList[randomX]
            adapter.add(menu_food_reverse(hmm))

        }

        giveMeMySpecialOffer()

        recyclerViewer_from_favorite_fragment.adapter = adapter

        recyclerViewer_from_favorite_fragment.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if(tlb == null) {
                    return
                }

                if(!recyclerView.canScrollVertically(-1)) {
                    // we have reached the top of the list
                    tlb!!.elevation = 0F
                } else {
                    // we are not at the top yet
                    tlb!!.elevation = 30F
                }

            }
        })

        add_to_favorite.setOnClickListener {
            val int = Intent(it.context, pushTheFood::class.java)
            int.putExtra("where", "special_offer")
            startActivity(int)
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_favorite, container, false)

    }
}


