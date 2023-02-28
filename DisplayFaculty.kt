package com.example.campusmanagementapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class DisplayFaculty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_faculty)

        var pro: ProgressDialog =ProgressDialog(this)
        pro.setMessage("Loading your data...")
        pro.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pro.setCancelable(false)
        pro.show()
        var listview =findViewById<ListView>(R.id.LV2)
        val database = Firebase.database
        val db= database.getReference("FacultyData")
        var adapter : ArrayAdapter<String>?=null
        var data = ArrayList<String>()
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (obj in snapshot.children){
                    var temp = obj.getValue<FacultyClass>()
                    var currentobj :String="Name : ${temp?.name} \nEmail: ${temp?.email} \nCnic : ${temp?.cnic} \nQualification : ${temp?.qualification} \nExperience: ${temp?.experience}"
                    data.add(currentobj)
                }
                adapter= ArrayAdapter<String>(baseContext,R.layout.customlayout3,R.id.tv,data)
                pro.hide()
                listview.adapter=adapter
            }
            override fun onCancelled(error: DatabaseError) {
             pro.hide()
                Toast.makeText(this@DisplayFaculty,"Operation is cancelled ", Toast.LENGTH_SHORT).show()

            }

        })
    }
}