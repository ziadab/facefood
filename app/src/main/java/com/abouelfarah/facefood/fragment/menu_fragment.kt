package com.abouelfarah.facefood.fragment

//import android.support.v7.widget.GridLayoutManager
//import android.transition.Slide
//import com.abouelfarah.facefood.detail
//import kotlinx.android.synthetic.main.food_card.view.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.food_card.view.*
import kotlinx.android.synthetic.main.fragment_menu.*

class menu_fragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapters = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }


        for (i in 0..10) {
            adapters.add(menu_food())
        }

        recyclerViewer_from_fragment_menu.adapter = adapters
//        recyclerViewer_from_fragment_menu.apply {
//            layoutManager = GridLayoutManager(this@menu_fragment.context, adapters.spanCount).apply {
//                spanSizeLookup = adapters.spanSizeLookup
//            }
//        }.adapter = adapters
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
}

class menu_food() : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.food_card
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.StevenCard.setOnClickListener {
            val activity = it.getContext() as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(
                R.id.FrameFromMenu,
                detailFromMEnu()
            ).addToBackStack(null).commit()
        }
    }
}

// override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 3
