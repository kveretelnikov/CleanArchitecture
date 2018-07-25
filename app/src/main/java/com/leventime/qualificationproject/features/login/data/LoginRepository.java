package com.leventime.qualificationproject.features.login.data;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.data.BaseRepository;
import com.leventime.qualificationproject.base.network.Api;
import com.leventime.qualificationproject.base.resources.ResourceManager;
import com.leventime.qualificationproject.features.login.LoginContract;

/**
 * Provide login data
 *
 * @author kv
 */
public class LoginRepository extends BaseRepository implements LoginContract.Repository{

    private final Api mApi;

    public LoginRepository(@NonNull final ResourceManager aResourceManager, @NonNull Api aApi){
        super(aResourceManager);
        mApi = aApi;
    }
}
