package com.example.campusmanagementapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Staff : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)
        var ct:Int=0
        var nm: EditText =findViewById(R.id.etSfName)
        var cnic: EditText =findViewById(R.id.etSfCnic)
        var des: EditText =findViewById(R.id.etSfDesignation)
        var sal: EditText =findViewById(R.id.etSfSalary)
        val save :Button= findViewById(R.id.Sfbuttonsave)
        val view :Button= findViewById(R.id.Sfbtnviewsaved)
        save.setOnClickListener{
            var strname =nm.text.toString()
            var strcnic =cnic.text.toString().toLong()
            var strdesignation =des.text.toString()
            var strsalary =sal.text.toString().toDouble()
            if(strname == ""||strcnic==null||strdesignation == ""||strsalary==null){
                Toast.makeText(this,"Please add Valid Data ",Toast.LENGTH_SHORT).show()
            }else{
                ct++
                var staff : StaffClass= StaffClass()
            staff.create(strname,strcnic,strdesignation,strsalary)
            // write to database
            val database = Firebase.database
            val db2= database.getReference("StaffData")
            db2.child("staff".plus(ct)).setValue(staff).addOnCompleteListener(this){
                    task ->
                if(task.isSuccessful) {
                    var alert = AlertDialog.Builder(this)
                    alert.setMessage("Record Added successfully !")
                    alert.setPositiveButton("Ok") { dialog, which -> }
                    alert.create()
                    alert.show()
                    //clear input fields
                    nm.text.clear()
                    cnic.text.clear()
                    des.text.clear()
                    sal.text.clear()

                }
            }
        }}
        view.setOnClickListener{
            val move = Intent(this, DisplayStaff::class.java)
            startActivity(move)

        }







    }
}