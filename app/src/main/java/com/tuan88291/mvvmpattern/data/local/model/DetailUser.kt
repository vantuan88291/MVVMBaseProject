package com.tuan88291.mvvmpattern.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailUser {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("email")
    @Expose
    lateinit var email: String
    @SerializedName("first_name")
    @Expose
    lateinit var firstName: String
    @SerializedName("last_name")
    @Expose
    lateinit var lastName: String
    @SerializedName("avatar")
    @Expose
    lateinit var avatar: String
}