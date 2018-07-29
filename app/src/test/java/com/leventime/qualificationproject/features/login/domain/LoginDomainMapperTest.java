package com.leventime.qualificationproject.features.login.domain;

import com.leventime.qualificationproject.features.login.data.LoginEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoEntity;

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
        LoginEntity loginEntity = LoginDomainMapper.mapLoginToEntity(loginDomain);
        assertEquals(email, loginEntity.getEmail());
        assertEquals(password, loginEntity.getPassword());
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
        UserInfoEntity userInfoEntity = new UserInfoEntity(id, avatarUrl, firstName, lastName);
        UserInfoDomain userInfoDomain = LoginDomainMapper.mapUserInfoFromEntity(userInfoEntity);
        assertEquals(id, userInfoDomain.getId());
        assertEquals(avatarUrl, userInfoDomain.getAvatarUrl());
        assertEquals(firstName, userInfoDomain.getFirstName());
        assertEquals(lastName, userInfoDomain.getLastName());
    }
}