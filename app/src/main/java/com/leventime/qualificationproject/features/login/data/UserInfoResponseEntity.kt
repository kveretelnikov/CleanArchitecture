package com.leventime.qualificationproject.features.login.data

import com.google.gson.annotations.SerializedName

/**
 * Describe user info
 *
 * @param id user id
 * @param avatar avatar
 * @param firstName first name
 * @param lastName last name
 * @author kv
 */
data class UserInfoResponseEntity(
        @SerializedName("id")
        val id: Long,
        @SerializedName("avatar")
        val avatar: String?,
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("lastName")
        val lastName: String
)