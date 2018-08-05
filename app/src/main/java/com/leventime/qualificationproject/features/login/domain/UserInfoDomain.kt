package com.leventime.qualificationproject.features.login.domain

/**
 * Describe user info
 *
 * @param id user id
 * @param avatar avatar
 * @param firstName first name
 * @param lastName last name
 * @author kv
 */
data class UserInfoDomain(
        val id: Long,
        val avatarUrl: String?,
        val firstName: String,
        val lastName: String
)