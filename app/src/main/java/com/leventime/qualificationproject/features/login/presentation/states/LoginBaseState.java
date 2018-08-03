package com.leventime.qualificationproject.features.login.presentation.states;

import android.support.annotation.NonNull;

import com.leventime.qualificationproject.base.core.presentation.states.BaseStateImpl;
import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login base type
 *
 * @author kv
 */
public abstract class LoginBaseState extends BaseStateImpl<LoginView, LoginOwner>{

    /**
     * Get state by type
     *
     * @param aType type
     * @return state
     */
    @NonNull
    public static LoginBaseState getState(@NonNull LoginStateType aType){
        switch(aType){
            case INIT:
                return new LoginInitState();
            case PROCESSING:
                return new LoginProcessingState();
            case COMPLETE:
                return new LoginCompleteState();
            default:
                return new LoginInitState();
        }
    }

    /**
     * Get type
     *
     * @return type
     */
    @NonNull
    abstract public LoginStateType getType();

    /**
     * Login state type
     */
    public enum LoginStateType{
        /**
         * Init state
         */
        INIT,
        /**
         * Processing login state
         */
        PROCESSING,
        /**
         * Complete login state
         */
        COMPLETE
    }
}
