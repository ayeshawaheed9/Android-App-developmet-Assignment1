package com.example.campusmanagementapp

class StaffClass {
    var name:String ?=null
    var cnic:Long ?=null
    var designation:String ?=null
    var salary:Double ?=null

    fun create(nm:String,cn:Long,des:String,sal:Double){
        name=nm
        cnic=cn
        designation=des
        salary=sal
    }

}