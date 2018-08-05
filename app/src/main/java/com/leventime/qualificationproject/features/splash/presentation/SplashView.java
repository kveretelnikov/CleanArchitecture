package com.leventime.qualificationproject.features.splash.presentation;

import com.leventime.qualificationproject.base.core.presentation.BaseView;

/**
 * Show splash
 *
 * @author kv
 */
public interface SplashView extends BaseView{

    /**
     * Empty splash view
     */
    SplashView EMPTY = new SplashView(){

        @Override
        public void navigateToMainView(){

        }

        @Override
        public void navigateToLoginView(){

        }
    };

    /**
     * Navigate to main view
     */
    void navigateToMainView();

    /**
     * Navigate to login view
     */
    void navigateToLoginView();
}

