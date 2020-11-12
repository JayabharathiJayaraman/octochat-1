package com.example.octochat

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.google.firebase.storage.StorageTask as StorageTask

class ProfileActivity : AppCompatActivity() {


    lateinit var userName :EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        userName = findViewById(R.id.editText_userName)
        //val userName = editText_userName.text

        //val phNum = editText_phoneNumber.text
        //val email = editText_email.text
        userName.setEnabled(false)

    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_editprofile -> {
                val intent = Intent(this, EditProfile::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    /*private fun uploadImageToFirebaseStorage(){
        if(selectedPhotoUri != null){
            val fileRef = storageRef!!.child(System.currentTimeMillis().toString()+".jpg")
            var uploadTask : StorageTask<*>
            uploadTask  = fileRef.putFile (selectedPhotoUri!!)
            uploadTask.continueWithTask(Continuation <UploadTask.TaskSnapshot, Task<Uri>>{ task ->

                    if(!task.isSuccessful)
                    {
                        task.exception?.let {

                            throw it
                        }
                    }
                return@Continuation fileRef.downloadUrl
            }).addOnCompleteListener { task ->

                if(!task.isSuccessful) {
                    val downloadUrl = task.result
                    val url = downloadUrl.toString()
                }
            }

        }


    }*/

}




