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

class Faculty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty)
        var ct:Int=0
        var nm: EditText =findViewById(R.id.etFcName)
        var email: EditText =findViewById(R.id.etFcEmail)
        var cnic: EditText =findViewById(R.id.etFcCnic)
        var exp: EditText =findViewById(R.id.etFcExperience)
        var qua: EditText =findViewById(R.id.etFcQualification)
        val save :Button= findViewById(R.id.Fcbuttonsave)
        val view :Button= findViewById(R.id.Fcbtnviewsaved)
        save.setOnClickListener{
            var strname =nm.text.toString()
            var stremail =email.text.toString()
            var strcnic =cnic.text.toString().toLong()
            var strexp=exp.text.toString()
            var strqua=qua.text.toString()
            var faculty : FacultyClass= FacultyClass()

            if(strname == ""||strcnic==null||stremail == ""||strexp==""||strqua==""){
                Toast.makeText(this,"Please add Valid Data ",Toast.LENGTH_SHORT).show()
            }else{
            faculty.create(strname,stremail,strcnic,strexp,strqua)
            // write to database
            val database = Firebase.database
            val db1= database.getReference("FacultyData")
            ct++
            db1.child("faculty".plus(ct)).setValue(faculty).addOnCompleteListener(this){
                    task ->
                if(task.isSuccessful) {
                    var alert = AlertDialog.Builder(this)
                    alert.setMessage("Record Added successfully !")
                    alert.setPositiveButton("Ok") { dialog, which -> }
                    alert.create()
                    alert.show()
                    //clear input fields
                    nm.text.clear()
                    email.text.clear()
                    cnic.text.clear()
                    exp.text.clear()
                    qua.text.clear()
                }
            }
        }
        }
        view.setOnClickListener{
            val move = Intent(this, DisplayFaculty::class.java)
            startActivity(move)

        }

    }
}