package com.example.models

data class User(
    val Id :Int,
    val fullname:String,
    val avatar :String,
    val email :String,
    var authtoken:String? = null,
    val createdAt:String
    )