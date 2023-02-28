package com.example.campusmanagementapp

class Studentclass {
    var name:String ?=null
    var email:String ?=null
    var dob:String ?=null
    var cgpa:Float ?=null
    var sem:Int ?=null
    var phno:Long ?=null

   fun create(nm:String,em:String,dateofb:String,cgpaa:Float,sm:Int,ph:Long){
        name=nm
       email=em
       dob=dateofb
       cgpa=cgpaa
       sem=sm
       phno=ph

   }
}