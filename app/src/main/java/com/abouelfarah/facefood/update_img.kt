package com.abouelfarah.facefood

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_update_img.*
import java.util.UUID

class update_img : AppCompatActivity() {

    var selectedPhotoUri: Uri? = null

    private fun uploadImgToFirebase(){
        val userId = FirebaseAuth.getInstance().uid

        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$fileName")
        ref.putFile(selectedPhotoUri!!).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                val hell = FirebaseDatabase.getInstance().getReference("/users/$userId")
                val has = HashMap<String, String>()
                has.put("profileImg", it.toString())
                //hell.setValue(has)
                hell.updateChildren(has as Map<String, String>)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_img)
        imgSelecter.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/JPEG"
            startActivityForResult(intent, 0)
        }

        change_my_profile_img.setOnClickListener {
            uploadImgToFirebase()
            val intent = Intent(this, Menu::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            Log.d("DATAFROMIMAGE", selectedPhotoUri.toString())
            //var bit = MediaStore.Images.Media.getBitmap(, selectedPhotoUri)
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            Log.d("DATAFROMIMAGE", bitmap.toString())
            imgShow.setImageBitmap(bitmap)
            imgSelecter.alpha = 0f
        }
    }
}
