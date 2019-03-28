package com.abouelfarah.facefood.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.models.suggetion
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_suggestion.*
import kotlinx.android.synthetic.main.suggest_card.view.*
import java.util.Random

class suggestion_fragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = GroupAdapter<ViewHolder>()

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

class suggestion_card(val idea:suggetion):Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.suggest_card
    }

    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.suggester_name.text = idea.name + " :"
        viewHolder.itemView.suggestion_idea.text = idea.content
    }
}