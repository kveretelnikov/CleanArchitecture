package com.leventime.qualificationproject.base.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.room.dao.UserDao;
import com.leventime.qualificationproject.base.room.entity.login.UserEntity;

/**
 * The Room database of application.
 */
@Database(entities = {UserEntity.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static final String DB_NAME = "app.sqlite";

    /**
     * Get database instance, use one instance in all app
     *
     * @param aContext android context
     * @return database instance
     */
    @NonNull
    public static AppDatabase build(@NonNull Context aContext){
        return Room.databaseBuilder(aContext.getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME)
                .build();
    }

    public abstract UserDao userDao();
}
