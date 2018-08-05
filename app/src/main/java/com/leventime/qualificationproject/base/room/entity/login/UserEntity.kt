package com.leventime.qualificationproject.base.room.entity.login

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Describe user info entity
 *
 * @param id user id
 * @param avatar avatar
 * @param firstName first name
 * @param lastName last name
 * @author kv
 */
@Entity(tableName = "user")
data class UserEntity(
        @ColumnInfo(name = "id")
        @PrimaryKey
        val id: Long,

        @ColumnInfo(name = "avatar_url")
        val avatarUrl: String?,

        @ColumnInfo(name = "first_name")
        val firstName: String,

        @ColumnInfo(name = "last_name")
        val lastName: String
)