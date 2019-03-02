package com.abouelfarah.facefood.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_favorite.*

class favorite_fragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapters = GroupAdapter<ViewHolder>()

        for(i in 1..10){
            adapters.add(thla())
        }

        recyclerViewer_from_favorite_fragment.adapter = adapters
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
}


class thla: Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.favorite_card
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
    }
}