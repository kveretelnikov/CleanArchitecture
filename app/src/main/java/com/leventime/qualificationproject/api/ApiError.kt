package com.leventime.qualificationproject.api

import com.google.gson.annotations.SerializedName

/**
 * Describe api error
 *
 * @author kv
 */
data class ApiError(
        @SerializedName("message")
        val message: String
)