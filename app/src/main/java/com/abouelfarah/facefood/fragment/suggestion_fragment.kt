package com.abouelfarah.facefood.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.models.suggetion
import com.abouelfarah.facefood.suggestion_card
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.add_suggestion.view.*
import kotlinx.android.synthetic.main.fragment_suggestion.*
import java.util.Random

class suggestion_fragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tlb: Toolbar = activity!!.findViewById(R.id.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tlb.elevation = 0F
        }

        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(add_suggest())

        val ideas = ArrayList<suggetion>()
        ideas.add(suggetion("Ziad", "Momlin diro xi livraison or something like this"))
        ideas.add(suggetion("Nassim", "HARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAM"))
        ideas.add(suggetion("Mehdi", "Momlin Tzido more item flfood"))

        var r = Random()
        for (i in 0..10) {
            var randomX = r.nextInt(ideas.size)
            val obj: suggetion = ideas[randomX]
            adapter.add(suggestion_card(obj))
        }

        recyclerViewer_from_suggestion_fragment.adapter = adapter


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_suggestion, container, false)
    }
}


class add_suggest():Item<ViewHolder>(){

    override fun getLayout(): Int {
        return R.layout.add_suggestion
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.cancel_button.setOnClickListener {
            viewHolder.itemView.name_from_add_suggestion.text.clear()
            viewHolder.itemView.idea_from_add_suggestion.text.clear()

        }

        viewHolder.itemView.post_button.setOnClickListener {
            viewHolder.itemView.post_button.text = "Posting..."
            viewHolder.itemView.post_button.isEnabled = false
            viewHolder.itemView.cancel_button.isEnabled = false
            val handler = Handler()
            handler.postDelayed(object:Runnable{
                override fun run() {
                    viewHolder.itemView.post_button.text = "Post"
                    viewHolder.itemView.post_button.isEnabled = true
                    viewHolder.itemView.cancel_button.isEnabled = true
                }
            }, 3000)
        }

    }
}