package com.example.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import  java.time.LocalDateTime

object UserTable :Table("users"){
    val id =integer("id").autoIncrement()
    val fullName=varchar("fullname",256)
    val avatar=text("avatar")
    val email=varchar("email",256)
    val password=text("password")
    val createdAt=datetime("created_at").clientDefault { LocalDateTime.now()}

    override val primaryKey=PrimaryKey(id)
}