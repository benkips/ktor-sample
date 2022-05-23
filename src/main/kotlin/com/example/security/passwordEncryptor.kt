package com.example.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val SECRET_KEY="44336543"
private val algorithm="HmacSHA1"

private  val HASH_KEY= hex(SECRET_KEY)
private  val MmAC_Key=SecretKeySpec(HASH_KEY, algorithm)

fun hash (passwrd:String):String{
    val hmac = Mac.getInstance(algorithm)
    hmac.init(MmAC_Key)
    return  hex(hmac.doFinal(passwrd.toByteArray(Charsets.UTF_8)))

}