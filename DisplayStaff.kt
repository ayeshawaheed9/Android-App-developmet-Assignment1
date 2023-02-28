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

class DisplayStaff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_staff)

        var pro: ProgressDialog = ProgressDialog(this)
        pro.setMessage("Loading your data...")
        pro.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pro.setCancelable(false)
        pro.show()

        var listview =findViewById<ListView>(R.id.LV1)
        val database = Firebase.database
        val db= database.getReference("StaffData")
        var adapter : ArrayAdapter<String>?=null
        var data = ArrayList<String>()
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (obj in snapshot.children){
                    var temp = obj.getValue<StaffClass>()
                    var currentstaff :String="Name : ${temp?.name} \nCnic : ${temp?.cnic} \nDesignation : ${temp?.designation} \nSalary : ${temp?.salary}"
                    data.add(currentstaff)

                }
                adapter= ArrayAdapter<String>(baseContext,R.layout.customlayout2,R.id.tv,data)
                pro.hide()
                listview.adapter=adapter
            }
            override fun onCancelled(error: DatabaseError) {
               pro.hide()
                Toast.makeText(this@DisplayStaff,"Operation is cancelled ", Toast.LENGTH_SHORT).show()

            }

        })


    }
}