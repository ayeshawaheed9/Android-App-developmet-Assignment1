package com.example.campusmanagementapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.campusmanagementapp.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val btn1:Button = findViewById(R.id.button1)
        val btn2:Button = findViewById(R.id.button2)
        val btn3:Button = findViewById(R.id.button3)

        btn1.setOnClickListener{
        val s = Intent(this, Studentpage::class.java)
        startActivity(s)
    }
        btn2.setOnClickListener{
        val s2 = Intent(this,Faculty::class.java)
        startActivity(s2)
    }
        btn3.setOnClickListener{
        val s3 = Intent(this,Staff::class.java)
        startActivity(s3)
    }
    }
}