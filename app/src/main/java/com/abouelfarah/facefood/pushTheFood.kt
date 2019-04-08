package com.abouelfarah.facefood

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.abouelfarah.facefood.models.food
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_push_the_food.*
import java.util.UUID

class pushTheFood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_the_food)

        cancelFood.setOnClickListener {
            val int = Intent(this, Menu::class.java)
            int.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(int)
        }

        foodImageToPost.setOnClickListener {
            val int = Intent(Intent.ACTION_PICK)
            int.type = "image/*"
            startActivityForResult(int, 0)
        }

        val direction = intent.getStringExtra("where")
        postTheFood.setOnClickListener {
            uploadImage(direction)
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            Picasso.get().load(selectedPhotoUri).into(foodImageToPost)
        }
    }

    private fun uploadImage(direction:String){

        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$fileName")
        val uploadTask = ref.putFile(selectedPhotoUri!!)

        val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation ref.downloadUrl
        }).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                Log.d("download", downloadUri)
                saveFoodInDatabase(downloadUri, direction)
            } else {
                // Handle failures
                // ...
            }
        }



    }

    private fun saveFoodInDatabase(imgLocation:String, direction:String){
        val name = nameToPost.text.toString()
        val description = descriptionToPost.text.toString()
        val post = food(imgLocation, name, description)
        val ref = FirebaseDatabase.getInstance().getReference("/$direction/").push()
        ref.setValue(post).addOnCompleteListener {
            startActivity(Intent(this, Menu::class.java))
        }
    }
}
