package com.leventime.qualificationproject.features.login.presentation

/**
 * Describe login validation errors
 *
 * @param emailError email error
 * @param passwordError password error
 *
 * @author kv
 */
data class LoginValidationErrors(
        var emailError: String? = null,
        var passwordError: String? = null) {
    /**
     * Determine is there any error contained
     */
    fun hasErrors(): Boolean {
        return !(emailError.isNullOrEmpty() &&
                passwordError.isNullOrEmpty());
    }
}