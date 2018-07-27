package com.leventime.qualificationproject.features.login.domain;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.data.LoginEntity;
import com.leventime.qualificationproject.features.login.data.LoginResponseEntity;
import com.leventime.qualificationproject.features.login.data.UserInfoEntity;

/**
 * Converts data between data and domain layers
 *
 * @author kv
 */
public final class LoginDomainMapper{

    private LoginDomainMapper(){
    }

    /**
     * Converts {@link LoginDomain} to {@link LoginEntity}
     *
     * @param aLoginDomain login data model
     * @return domain model
     */
    @NonNull
    public static LoginEntity mapLoginToEntity(@NonNull LoginDomain aLoginDomain){
        return new LoginEntity(aLoginDomain.getEmail(), aLoginDomain.getPassword());
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
     * Converts {@link UserInfoEntity} to {@link UserInfoDomain}
     *
     * @param aUserInfoEntity user info data model
     * @return domain model
     */
    @NonNull
    public static UserInfoDomain mapUserInfoFromEntity(@NonNull UserInfoEntity aUserInfoEntity){
        return new UserInfoDomain(aUserInfoEntity.getId(),
                                  aUserInfoEntity.getAvatar(),
                                  aUserInfoEntity.getFirstName(),
                                  aUserInfoEntity.getLastName());
    }

}
