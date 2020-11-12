package com.example.octochat

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.profilePicture
import kotlinx.android.synthetic.main.activity_main.*
import java.util.HashMap

class EditProfile : AppCompatActivity() {
    private val RequestCode = 0
    private var selectedPhotoUri : Uri?= null
    lateinit var imageView: ImageView
    lateinit var changeProfilePic: ImageView
    lateinit var userName :EditText
    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        db = FirebaseFirestore.getInstance()

        edit_userName.setEnabled(false)

        imageView = findViewById(R.id.editUserName)
        userName = findViewById(R.id.edit_userName)
        changeProfilePic = findViewById(R.id.editProfilePic)

        imageView.setOnClickListener {
            edit_userName.setEnabled(true)
            }
        editProfilePic.setOnClickListener{
            val i = Intent()
            i.setType("image/*")
            i.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(i, "Choose Picture"),RequestCode)
        }


        saveButton.setOnClickListener{
            val username = userName.text.toString()
            upload()
            updateUserProfile(username)
            edit_userName.setEnabled(false)
            imageView.setVisibility(View.INVISIBLE)
            changeProfilePic.setVisibility(View.INVISIBLE)
        }

      }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RequestCode && resultCode == Activity.RESULT_OK &&  data != null){
            selectedPhotoUri = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)
            profilePicture.setImageBitmap(bitmap)
        }
    }
    private fun upload() {
        if (selectedPhotoUri != null) {
            val pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()
            val fileRef = FirebaseStorage.getInstance().reference.child("Users Profile Image")
            fileRef.putFile(selectedPhotoUri!!)
                .addOnSuccessListener {p0 ->
                    pd.dismiss()
                    Log.d("!!!","File uploaded")
                    Toast.makeText(applicationContext, "File uploaded", Toast.LENGTH_SHORT).show()
                }

        }
    }

    fun updateUserProfile (username: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["userName"] = username
        if (username == null) {
            error("Please enter name")
        }

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "record added successfully ", Toast.LENGTH_SHORT ).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "record Failed to add ", Toast.LENGTH_SHORT ).show()
            }

    }

}