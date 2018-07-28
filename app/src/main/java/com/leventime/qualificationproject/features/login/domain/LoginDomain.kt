package com.leventime.qualificationproject.features.login.domain

/**
 * Describe login data
 *
 * @param email email
 * @param password
 * @author kv
 */
data class LoginDomain(var email: String? = null,
                       var password: String? = null) {

    /**
     * Simple wrapper over kotlin's copy method -> java side always require all args
     */
    fun makeCopy(aLoginData: LoginDomain): LoginDomain {
        return aLoginData.copy()
    }
}