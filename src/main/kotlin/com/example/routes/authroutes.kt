package com.example.routes

import com.example.Repo.userRepo
import com.example.service.CreateUserParams
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authroutes (repo:userRepo){
    routing {
        route("/auth"){
                post("/register"){
                    val params=call.receive<CreateUserParams>()
                    val result=repo.registeruser(params)
                    call.respond(result.statusCode,result)

                }
        }

    }
}