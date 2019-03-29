package com.abouelfarah.facefood

import android.annotation.SuppressLint
import com.abouelfarah.facefood.models.suggetion
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.suggest_card.view.*

class suggestion_card(val idea: suggetion): Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.suggest_card
    }

    @SuppressLint("SetTextI18n")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.suggester_name.text = idea.name + " :"
        viewHolder.itemView.suggestion_idea.text = idea.content
    }
}