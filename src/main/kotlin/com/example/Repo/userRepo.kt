package com.example.Repo

import com.example.service.CreateUserParams
import com.example.utils.BaseResponse

interface userRepo {
    suspend fun registeruser(params:CreateUserParams): BaseResponse<Any>
    suspend fun login(email:String,password:String):BaseResponse<Any>
}