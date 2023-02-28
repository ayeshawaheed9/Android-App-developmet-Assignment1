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

class Studentpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentpage)
        var ct:Int=0
     var nm:EditText=findViewById(R.id.etStName)
     var emailad:EditText=findViewById(R.id.etStEmail)
     var dob:EditText=findViewById(R.id.etStDob)
     var cgpaa:EditText=findViewById(R.id.etStCgpa)
     var sm:EditText=findViewById(R.id.etStSem)
     var phno:EditText=findViewById(R.id.etStPhno)
        val save :Button= findViewById(R.id.Stbuttonsave)
        val view :Button= findViewById(R.id.Stbtnviewsaved)
        save.setOnClickListener{
                var name =nm.text.toString()
                var email =emailad.text.toString()
                var dateofbirth =dob.text.toString()
                var cgpa =cgpaa.text.toString().toFloat()
                var num =phno.text.toString().toLong()
                var sem =sm.text.toString().toInt()
            if(name == ""||email==""||dateofbirth==""||cgpa == null||num==null||sem==null){
    Toast.makeText(this,"Please add Valid Data ",Toast.LENGTH_SHORT).show()
                }else{
                ct++
                var student : Studentclass= Studentclass()
                student.create(name,email,dateofbirth,cgpa,sem,num)
                // write to database
                val database = Firebase.database
                val db= database.getReference("StudentData")
                db.child("student".plus(ct)).setValue(student).addOnCompleteListener(this){
                    task ->
                 if(task.isSuccessful) {
                     var alert = AlertDialog.Builder(this)
                     alert.setMessage("Record Added successfully !")
                     alert.setPositiveButton("Ok") { dialog, which -> }
                     alert.create()
                     alert.show()
                     //clear input fields
                     nm.text.clear()
                     emailad.text.clear()
                     dob.text.clear()
                     cgpaa.text.clear()
                     sm.text.clear()
                     phno.text.clear()

                 }
                }
        }
        }
        view.setOnClickListener{
            val move = Intent(this, DisplayStudent::class.java)
            startActivity(move)

        }
    }
}