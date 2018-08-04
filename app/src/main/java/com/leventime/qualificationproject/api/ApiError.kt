package com.leventime.qualificationproject.api

import com.google.gson.annotations.SerializedName

/**
 * Describe api error
 *
 * @param status status code
 * @param message message
 * @author kv
 */
data class ApiError(
        @SerializedName("status")
        val status: Int,

        @SerializedName("message")
        val message: String
)