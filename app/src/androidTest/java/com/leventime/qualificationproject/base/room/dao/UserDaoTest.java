package com.leventime.qualificationproject.base.room.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.leventime.qualificationproject.base.room.AppDatabase;
import com.leventime.qualificationproject.base.room.entity.login.UserEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Test {@link UserDao}
 *
 * @author kv
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest{

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private AppDatabase mDatabase;
    private UserDao mDao;

    @Before
    public void initDb(){
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        mDao = mDatabase.userDao();
    }

    @After
    public void closeDb(){
        mDatabase.close();
    }

    @Test
    public void insertUser(){
        long id = 1;
        UserEntity user = createUser();
        long actualId = mDao.insertUser(user);
        assertEquals(id, actualId);
    }

    @Test
    public void insertAndUpdateUser(){
        long id = 1;
        String avatarUrl = "url1";
        String firstName = "first";
        String lastName = "last";
        UserEntity user = new UserEntity(id, avatarUrl, firstName, lastName);
        mDao.insertUser(user);
        String newAvatarUrl = "url2";
        UserEntity userUpdated = new UserEntity(id, newAvatarUrl, firstName, lastName);
        mDao.updateUser(userUpdated);
        mDao.getUser()
                .test()
                .assertValue(userUpdated);
    }

    @Test
    public void saveAndGetUser(){
        UserEntity user = createUser();
        mDao.saveUser(user);
        mDao.getUser()
                .test()
                .assertValue(user);
    }

    /**
     * Create user
     *
     * @return user
     */
    @NonNull
    private UserEntity createUser(){
        long id = 1;
        String avatarUrl = "url1";
        String firstName = "first";
        String lastName = "last";
        return new UserEntity(id, avatarUrl, firstName, lastName);
    }

}
