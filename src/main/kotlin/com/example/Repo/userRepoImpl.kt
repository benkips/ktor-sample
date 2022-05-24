package com.example.Repo

import com.example.security.Jwtconfig
import com.example.service.CreateUserParams
import com.example.service.userService
import com.example.utils.BaseResponse

class userRepoImpl(
    private val uservice:userService) : userRepo {

    override suspend fun registeruser(params: CreateUserParams):BaseResponse<Any> {
     return  if(isEmailexist(params.email)){
         BaseResponse.ErrorResponse(message = "email is already registered")
      }else{
            val user=uservice.registeruser(params)
            if(user!=null){
                val token= Jwtconfig.instance.createAccessToken(user.Id)
                user.authtoken=token
                BaseResponse.SuccessResponse(data=user, message = "Success user is registered successfully")
            }else{
               BaseResponse.ErrorResponse()
            }
     }
    }

    override suspend fun login(email: String, password: String):BaseResponse<Any> {
        TODO("Not yet implemented")
    }
    private suspend fun  isEmailexist(email: String):Boolean{
        return uservice.findUserByEmail(email)!=null
    }
}