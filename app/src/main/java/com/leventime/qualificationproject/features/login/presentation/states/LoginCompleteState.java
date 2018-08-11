package com.leventime.qualificationproject.features.login.presentation.states;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login complete state
 *
 * @author kv
 */
public class LoginCompleteState extends LoginBaseState{

    /**
     * Create {@link LoginCompleteState}
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public Parcelable createFromParcel(Parcel aIn){
            return new LoginCompleteState(aIn);
        }

        public LoginCompleteState[] newArray(int size){
            return new LoginCompleteState[size];
        }
    };

    public LoginCompleteState(){
    }

    /**
     * @param aParcel data
     */
    private LoginCompleteState(final Parcel aParcel){

    }

    @NonNull
    @Override
    public String getName(){
        return getClass().getName();
    }

    @Override
    public void onEnter(@NonNull final LoginView aView){
        super.onEnter(aView);
        aView.navigateToMainView();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel aParcel, final int aI){

    }
}
