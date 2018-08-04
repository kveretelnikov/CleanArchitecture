package com.leventime.qualificationproject.base.room.dao

import android.arch.persistence.room.*
import com.leventime.qualificationproject.base.room.entity.login.UserEntity
import com.leventime.qualificationproject.util.Constants

/**
 * Provide user data
 *
 * @author kv
 */
@Dao
interface UserDao {

    /**
     * Save user info
     *
     * @param user user info
     */
    @Transaction
    fun saveUser(user: UserEntity) {
        val userId = insertUser(user)
        if (userId == Constants.UNDEFINED_ID) {
            updateUser(user);
        }
    }

    /**
     * Insert a user in the database. If the user already exists, ignore.
     * Not use method, need for uses in [UserDao.saveUser]
     *
     * @param aQuizModule quiz module to be inserted.
     * @return id of inserted module
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserEntity): Long

    /**
     * Update user
     * Not use method, need for uses in [UserDao.saveUser]
     *
     * @param user user info
     */
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUser(user: UserEntity)
}