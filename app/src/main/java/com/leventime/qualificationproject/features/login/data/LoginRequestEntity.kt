package com.leventime.qualificationproject.features.login.data

import com.google.gson.annotations.SerializedName

/**
 * Describe login data
 *
 * @param email email
 * @param password password
 * @author kv
 */
data class LoginRequestEntity(
        @SerializedName("username")
        var email: String? = null,

        @SerializedName("password")
        var password: String? = null)