package com.example.service

import com.example.models.User

interface userService  {
    suspend fun  registeruser (params: CreateUserParams): User?
    suspend fun findUserByEmail(email:String):User?
}