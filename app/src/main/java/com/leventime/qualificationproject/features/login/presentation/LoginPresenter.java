package com.leventime.qualificationproject.features.login.presentation;

import com.leventime.qualificationproject.base.core.presentation.BasePresenter;
import com.leventime.qualificationproject.features.login.presentation.states.LoginOwner;

/**
 *
 * Manage login view
 *
 * @author kv
 */
public interface LoginPresenter extends BasePresenter<LoginView>, LoginView.Actions, LoginOwner{

}
