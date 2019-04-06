package com.abouelfarah.facefood.fragment

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.models.post
import com.abouelfarah.facefood.post_card
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.add_suggestion.view.*
import kotlinx.android.synthetic.main.fragment_suggestion.*

class suggestion_fragment : Fragment() {

    companion object {
        val adapter = GroupAdapter<ViewHolder>()
    }

    private fun getSuggestion() {

        val posta = ArrayList<post>()

        val ref = FirebaseDatabase.getInstance().getReference("/suggestion").orderByChild("/timestamp")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                p0.children.reversed().forEach {
                    val post = it.getValue(post::class.java)
                    if (post != null) {
                        posta.add(post)
                        adapter.add(post_card(post))
                    }
                }
            }
        })

        Log.d("thlaa", posta.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tlb: Toolbar = activity!!.findViewById(R.id.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tlb.elevation = 0F
        }

        //val adapter = GroupAdapter<ViewHolder>()
        adapter.add(add_suggest())

        /*val ideas = ArrayList<post>()
        ideas.add(post("Ziad", "Momlin diro xi livraison or something like this", 1432943038))
        ideas.add(post("Nassim", "HARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAM", 1432943038))
        ideas.add(post("Mehdi", "Momlin Tzido more item flfood", 1432943038))

        var r = Random()
        for (i in 0..10) {
            var randomX = r.nextInt(ideas.size)
            val obj: post = ideas[randomX]
            adapter.add(post_card(obj))
        }*/

        getSuggestion()

        recyclerViewer_from_suggestion_fragment.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_suggestion, container, false)
    }
}

@Suppress("NAME_SHADOWING")
class add_suggest() : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.add_suggestion
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.cancel_button.setOnClickListener {
            viewHolder.itemView.name_from_add_suggestion.text.clear()
            viewHolder.itemView.idea_from_add_suggestion.text.clear()

        }

        viewHolder.itemView.post_button.setOnClickListener {

            var work: Int = 1
            var msg: String? = null


            val name = viewHolder.itemView.name_from_add_suggestion.text.toString()
            val idea = viewHolder.itemView.idea_from_add_suggestion.text.toString()

            if (name.isNotEmpty() && idea.isNotEmpty()) {

                viewHolder.itemView.post_button.text = "Posting..."
                viewHolder.itemView.post_button.isEnabled = false
                viewHolder.itemView.cancel_button.isEnabled = false

                val handler = Handler()
                handler.postDelayed(Runnable {

                    val currentTime = System.currentTimeMillis()
                    val ref = FirebaseDatabase.getInstance().getReference("/suggestion").push()
                    Log.d("thlla", "${name}, ${idea}")
                    val poste = post(name, idea, currentTime)
                    ref.setValue(poste).addOnCompleteListener {
                        work = 1
                    }.addOnFailureListener {
                        work = 0
                        msg = it.message.toString()
                    }


                    viewHolder.itemView.post_button.text = "Post"
                    viewHolder.itemView.post_button.isEnabled = true
                    viewHolder.itemView.cancel_button.isEnabled = true

                    suggestion_fragment.adapter.add(1, post_card(poste))


                    if (work == 0) {
                        Toast.makeText(it.context, "MA5EDAMX SAFI", Toast.LENGTH_SHORT).show()
                    } else if (work == 1) {
                        Toast.makeText(it.context, "Posted", Toast.LENGTH_SHORT).show()
                    }

                    viewHolder.itemView.name_from_add_suggestion.text.clear()
                    viewHolder.itemView.idea_from_add_suggestion.text.clear()

                }, 1000)
            } else {
                if (name.isEmpty()) {
                    viewHolder.itemView.name_from_add_suggestion.error = "Name should not be empty"
                } else if (idea.isEmpty()) {
                    viewHolder.itemView.idea_from_add_suggestion.error = "Idea should not be empty"
                }
            }

        }
    }
}