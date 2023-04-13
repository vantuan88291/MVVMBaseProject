package com.tuan88291.mvvmpattern.data.local.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DataProfile {

    @SerializedName("id")
    @Expose
     var id: Int? = null

    @SerializedName("name")
    @Expose
     var name: String? = null

    @SerializedName("phone")
    @Expose
     var phone: String? = null

    @SerializedName("address")
    @Expose
     var address: String? = null

    @SerializedName("email")
    @Expose
     var email: String? = null

    @SerializedName("passport")
    @Expose
     var passport: Any? = null

    @SerializedName("date_of_birth")
    @Expose
     var dateOfBirth: Any? = null

    @SerializedName("type")
    @Expose
     var type: Any? = null

    @SerializedName("created_at")
    @Expose
     var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
     var updatedAt: String? = null

    @SerializedName("email_verified_at")
    @Expose
     var emailVerifiedAt: Any? = null

    @SerializedName("avatar")
    @Expose
     var avatar: Any? = null

    @SerializedName("settings")
    @Expose
     var settings: Any? = null

    @SerializedName("status")
    @Expose
     var status: String? = null

    @SerializedName("is_advertisement")
    @Expose
     var isAdvertisement: Int? = null
}