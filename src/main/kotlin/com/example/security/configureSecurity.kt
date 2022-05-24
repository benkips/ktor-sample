package com.example.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity(){
    Jwtconfig.initialize("my-story-app")
    install(Authentication){
        jwt {
            verifier(Jwtconfig.instance.verifier)
            validate {
                val claim=it.payload.getClaim(Jwtconfig.claim).asInt()
                if (claim!=null){
                    UserIdPrincipal(claim)
                }else{
                    null
                }
            }
        }
    }
}