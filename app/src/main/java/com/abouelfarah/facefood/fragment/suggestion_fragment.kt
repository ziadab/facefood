package com.abouelfarah.facefood.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abouelfarah.facefood.R
import com.abouelfarah.facefood.models.post
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.add_suggestion.view.*
import kotlinx.android.synthetic.main.fragment_suggestion.*
import java.util.Random
import android.os.Handler
import android.widget.Toast
import com.abouelfarah.facefood.post_card
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class suggestion_fragment : Fragment() {

    private fun arangePost(aray : ArrayList<post>): List<post> {
        var sortedList = aray.sortedWith(compareBy({it.timestamp}))
        return sortedList.asReversed()
    }

    private fun getSuggestion(){

        val adapter = GroupAdapter<ViewHolder>()
        val posta = ArrayList<post>()

        val ref = FirebaseDatabase.getInstance().getReference("/suggestion")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                // TODO Don't forget to arange the suggestion post
                // TODO fetsh group at index 0

                val post_suggest:post = p0.getValue(post::class.java)!!
                posta.add(post_suggest)

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })

        val ranged = arangePost(posta)
        for(obj in ranged){
            adapter.add(post_card(obj))
        }

        recyclerViewer_from_suggestion_fragment.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tlb: Toolbar = activity!!.findViewById(R.id.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tlb.elevation = 0F
        }

        val adapter = GroupAdapter<ViewHolder>()
        adapter.add(add_suggest())

        val ideas = ArrayList<post>()
        ideas.add(post("Ziad", "Momlin diro xi livraison or something like this", 1432943038))
        ideas.add(post("Nassim", "HARAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAM", 1432943038))
        ideas.add(post("Mehdi", "Momlin Tzido more item flfood", 1432943038))

        var r = Random()
        for (i in 0..10) {
            var randomX = r.nextInt(ideas.size)
            val obj: post = ideas[randomX]
            adapter.add(post_card(obj))
        }

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

        val name = viewHolder.itemView.name_from_add_suggestion.text.toString()
        val idea = viewHolder.itemView.idea_from_add_suggestion.text.toString()

        viewHolder.itemView.cancel_button.setOnClickListener {
            viewHolder.itemView.name_from_add_suggestion.text.clear()
            viewHolder.itemView.idea_from_add_suggestion.text.clear()

        }

        viewHolder.itemView.post_button.setOnClickListener {

            var work:Int = 0
            var msg :String ?= null

            viewHolder.itemView.post_button.text = "Posting..."
            viewHolder.itemView.post_button.isEnabled = false
            viewHolder.itemView.cancel_button.isEnabled = false


            val name = viewHolder.itemView.name_from_add_suggestion.text.toString()
            val idea = viewHolder.itemView.idea_from_add_suggestion.text.toString()
            

            val handler = Handler()
            handler.postDelayed(Runnable {

                val currentTime = System.currentTimeMillis()
                val ref = FirebaseDatabase.getInstance().getReference("/suggestion").push()
                val data = post(name, idea, currentTime)
                ref.setValue(data).addOnFailureListener {
                    work = 0
                    msg = it.message.toString()
                }.addOnSuccessListener {
                    work = 1
                }


                viewHolder.itemView.post_button.text = "Post"
                viewHolder.itemView.post_button.isEnabled = true
                viewHolder.itemView.cancel_button.isEnabled = true

            }, 2000)

            if(work == 0){
                Toast.makeText(it.context, "MA5EDAMX SAFI", Toast.LENGTH_SHORT).show()
            }else if(work == 1){
                Toast.makeText(it.context, "Posted", Toast.LENGTH_SHORT).show()
            }



        }
    }


}