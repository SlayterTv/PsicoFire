package com.slaytertv.psicofire.data.model

data class UserItem(
    var id: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val phone_name:String = "",
    val work_name: String = "",
    val work_phone:String = "",
    val work_direc:String ="",
    val work_nit:String ="",
    val email: String = "",
    val password: String = "",
    val inidate: String = "",
    val enddate: String = "",
    val statedate: String = "open"
)
