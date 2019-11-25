package com.tuan88291.mvppatternkotlin.data.local.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataUser {
    @SerializedName("data")
    @Expose
    var data: List<DetailUser>? = null
}
