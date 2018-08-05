package com.leventime.qualificationproject.features.login.domain;

import com.leventime.qualificationproject.base.room.entity.login.UserEntity;
import com.leventime.qualificationproject.features.login.data.LoginRequestEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoResponseEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

/**
 * Test {@link LoginDomainMapper}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginDomainMapperTest{

    @Test
    public void mapLoginToEntity(){
        LoginDomain loginDomain = new LoginDomain();
        String email = "user@user.ru";
        String password = "password";
        loginDomain.setEmail(email);
        loginDomain.setPassword(password);
        LoginRequestEntity loginRequestEntity = LoginDomainMapper.mapLoginToEntity(loginDomain);
        assertEquals(email, loginRequestEntity.getEmail());
        assertEquals(password, loginRequestEntity.getPassword());
    }

    @Test
    public void mapLoginResponseFromEntity(){
        String token = "token";
        LoginResponseEntity loginResponseEntity = new LoginResponseEntity(token);
        LoginResponseDomain loginResponseDomain = LoginDomainMapper.mapLoginResponseFromEntity(loginResponseEntity);
        assertEquals(token, loginResponseDomain.getToken());
    }

    @Test
    public void mapUserInfoFromEntity(){
        long id = 1;
        String avatarUrl = "url";
        String firstName = "first";
        String lastName = "last";
        UserInfoResponseEntity userInfoEntity = new UserInfoResponseEntity(id, avatarUrl, firstName, lastName);
        UserInfoDomain userInfoDomain = LoginDomainMapper.mapUserInfoFromEntity(userInfoEntity);
        assertEquals(id, userInfoDomain.getId());
        assertEquals(avatarUrl, userInfoDomain.getAvatarUrl());
        assertEquals(firstName, userInfoDomain.getFirstName());
        assertEquals(lastName, userInfoDomain.getLastName());
    }

    @Test
    public void mapUserInfoToEntity(){
        long id = 1;
        String avatarUrl = "url";
        String firstName = "first";
        String lastName = "last";
        UserInfoDomain userInfoDomain = new UserInfoDomain(id, avatarUrl, firstName, lastName);
        UserEntity userEntity = LoginDomainMapper.mapUserInfoToEntity(userInfoDomain);
        assertEquals(id, userEntity.getId());
        assertEquals(avatarUrl, userEntity.getAvatarUrl());
        assertEquals(firstName, userEntity.getFirstName());
        assertEquals(lastName, userEntity.getLastName());
    }
}