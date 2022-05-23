package com.example.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class Jwtconfig private constructor(secret:String){
    private  val algorithm=Algorithm.HMAC256(secret)

    val verifier:JWTVerifier=JWT
        .require(algorithm)
        .withIssuer(Issuer)
        .withAudience(Audience)
        .build()

    fun createAccessToken(Id:Int):String = JWT
        .create()
        .withAudience(Audience)
        .withIssuer(Issuer)
        .withClaim(claim,Id)
        .sign(algorithm)

    companion object{
        private const val Issuer="my-story-app"
        private const val Audience="my-story-app"
        const val claim="Id"

        lateinit var instance:Jwtconfig

        fun initialize(secret: String){
            synchronized(this){
                if(!this::instance.isInitialized) {
                    instance=Jwtconfig(secret)
                }
                }

        }
    }
}