package com.example.campusmanagementapp

class FacultyClass {
    var name:String ?=null
    var email:String ?=null
    var cnic:Long ?=null
    var qualification:String ?=null
    var experience:String ?=null

    fun create(nm:String,em:String,cn:Long,qua:String,exp:String){
        name=nm
        email=em
        cnic=cn
        qualification=qua
        experience=exp

    }

}