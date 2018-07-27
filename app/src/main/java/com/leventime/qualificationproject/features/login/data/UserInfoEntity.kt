package com.leventime.qualificationproject.features.login.data

/**
 * Describe user info
 *
 * @param id user id
 * @param avatar avatar
 * @param firstName first name
 * @param lastName last name
 * @author kv
 */
data class UserInfoEntity(
        val id: Long,
        val avatar: String?,
        val firstName: String,
        val lastName: String
)