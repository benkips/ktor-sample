package com.example.service

import com.example.db.UserTable
import com.example.db.dbfactory.dbQuery
import com.example.models.User
import com.example.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class userServiceimpl:userService {
    override suspend fun registeruser(params: CreateUserParams): User? {
         var statement:InsertStatement<Number>?=null
        dbQuery{
            statement=UserTable.insert {
                it[email]=params.email
                it[password]= hash( params.password)
                it[fullName]=params.fullname
                it[avatar]=params.avatar
            }
        }
        return rowToUser(statement?.resultedValues?.get(0 ))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user=dbQuery{
            UserTable.select {
                 UserTable.email.eq(email)
            }.map { rowToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row:ResultRow?): User? {
        return if(row==null) {
            null
        } else {
            User(
                Id = row[UserTable.id],
                fullname = row[UserTable.fullName],
                avatar = row[UserTable.avatar],
                email = row[UserTable.email],
                createdAt = row[UserTable.createdAt].toString()
            )
        }
    }

}