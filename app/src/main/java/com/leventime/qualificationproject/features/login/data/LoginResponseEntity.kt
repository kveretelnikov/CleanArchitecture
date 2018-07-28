package com.leventime.qualificationproject.features.login.data

import com.google.gson.annotations.SerializedName

/**
 * Describe login response data
 *
 * @author kv
 */
class LoginResponseEntity(
        @SerializedName("token")
        var token: String
)