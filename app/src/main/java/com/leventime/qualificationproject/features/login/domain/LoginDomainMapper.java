package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.room.entity.login.UserEntity;
import com.leventime.qualificationproject.features.login.data.LoginRequestEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoResponseEntity;

/**
 * Converts data between data and domain layers
 *
 * @author kv
 */
public final class LoginDomainMapper{

    private LoginDomainMapper(){
    }

    /**
     * Converts {@link LoginDomain} to {@link LoginRequestEntity}
     *
     * @param aLoginDomain login data model
     * @return domain model
     */
    @NonNull
    public static LoginRequestEntity mapLoginToEntity(@NonNull LoginDomain aLoginDomain){
        return new LoginRequestEntity(aLoginDomain.getEmail(), aLoginDomain.getPassword());
    }

    /**
     * Converts {@link LoginResponseEntity} to {@link LoginResponseDomain}
     *
     * @param aLoginResponseEntity login response data model
     * @return domain model
     */
    @NonNull
    public static LoginResponseDomain mapLoginResponseFromEntity(@NonNull LoginResponseEntity aLoginResponseEntity){
        return new LoginResponseDomain(aLoginResponseEntity.getToken());
    }

    /**
     * Converts {@link UserInfoResponseEntity} to {@link UserInfoDomain}
     *
     * @param aUserInfoEntity user info data model
     * @return domain model
     */
    @NonNull
    public static UserInfoDomain mapUserInfoFromEntity(@NonNull UserInfoResponseEntity aUserInfoEntity){
        return new UserInfoDomain(aUserInfoEntity.getId(),
                                  aUserInfoEntity.getAvatar(),
                                  aUserInfoEntity.getFirstName(),
                                  aUserInfoEntity.getLastName());
    }

    /**
     * Converts {@link UserInfoDomain} to {@link UserEntity}
     *
     * @param aUserInfoDomain user info data model
     * @return data model
     */
    @NonNull
    public static UserEntity mapUserInfoToEntity(@NonNull UserInfoDomain aUserInfoDomain){
        return new UserEntity(aUserInfoDomain.getId(),
                              aUserInfoDomain.getAvatarUrl(),
                              aUserInfoDomain.getFirstName(),
                              aUserInfoDomain.getLastName());
    }

}
