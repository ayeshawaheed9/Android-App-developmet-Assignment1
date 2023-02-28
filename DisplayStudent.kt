package com.example.campusmanagementapp

import android.app.ProgressDialog
import android.icu.lang.UCharacter.GraphemeClusterBreak.LV
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

class DisplayStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_student)

        var pro: ProgressDialog = ProgressDialog(this)
        pro.setMessage("Loading your data...")
        pro.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pro.setCancelable(false)
        pro.show()

        var listview =findViewById<ListView>(R.id.LV)
        val database = Firebase.database
        val db= database.getReference("StudentData")
        var adapter :ArrayAdapter<String>?=null
        var data = ArrayList<String>()
        db.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
            for (obj in snapshot.children){
             var temp = obj.getValue<Studentclass>()
                var currentStudent :String="Name : ${temp?.name} \nEmail: ${temp?.email} \nDate Of Birth : ${temp?.dob} \nCgpa : ${temp?.cgpa} \nSemester : ${temp?.sem} \nPhno : ${temp?.phno}"
                data.add(currentStudent)
            }
               adapter=ArrayAdapter<String>(baseContext,R.layout.customlayout1,R.id.tv,data)
                pro.hide()
             // newRecyclerview=findViewById<RecyclerView>(R.id.recyclerView)
            listview.adapter=adapter
            }
            override fun onCancelled(error: DatabaseError) {
                pro.hide()
                Toast.makeText(this@DisplayStudent,"Operation is cancelled ", Toast.LENGTH_SHORT).show()
            }
        })
    }
}