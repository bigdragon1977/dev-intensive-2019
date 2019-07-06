package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (
    val id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int = 0,
    val lastVisit:Date? = null,
    val isOnline:Boolean = false
)
{
    constructor(id:String, firstName: String?, lastName: String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String): this(
        id = id,
        firstName = "John",
        lastName = "Doe"
    )

    init {

        println("It's Alive!!! \n${ if(lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName"}\n")
    }

    companion object Factory {
        private var lastId : Int = 1

        fun makeUser(fullName:String?) : User {

            lastId++

            val (firstName,lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
    /*
    data class  Builder(
        val id:String = "",
        var firstName:String = "",
        var lastName:String = "",
        var avatar:String = "",
        var rating:Int = 0,
        var respect:Int = 0,
        var lastVisit:Date = Date(),
        var isOnline:Boolean = false
    ) {
        fun id(id: String) = apply { this@Builder.id = id }
        fun firstName(firstName: String) = apply { this@Builder.firstName = firstName }
        fun lastName(lastName: String) = apply { this@Builder.lastName = lastName }
        fun avatar(avatar: String) = apply { this@Builder.avatar = avatar }
        fun rating(rating: Int) = apply { this@Builder.avatar = avatar }
        fun respect(respect: Int) = apply { this@Builder.respect = respect }
        fun lastVisit(lastVisit: Date) = apply { this@Builder.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this@Builder.isOnline = isOnline }
        fun build() = User(id, firstName, lastName, avatar,rating, respect, lastVisit, isOnline)
    }
    */
}