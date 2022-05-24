package com.example

import com.example.Repo.userRepo
import com.example.Repo.userRepoImpl
import com.example.db.dbfactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.routes.authroutes
import com.example.security.configureSecurity
import com.example.service.userService
import com.example.service.userServiceimpl
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.serialization.jackson.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        dbfactory.init()
        val service: userService = userServiceimpl()
        val repo: userRepo = userRepoImpl(service)
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }
        configureSecurity()
        authroutes(repo)

    }.start(wait = true)
}
