package com.tuan88291.mvvmpattern.data.local.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataLogin {
    @SerializedName("access_token")
    @Expose
    var accessToken: String? = null

    @SerializedName("token_type")
    @Expose
    var tokenType: String? = null

    @SerializedName("expires_in")
    @Expose
    var expiresIn: Int? = null
}