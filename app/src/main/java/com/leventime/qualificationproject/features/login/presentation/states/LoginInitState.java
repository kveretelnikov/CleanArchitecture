package com.leventime.qualificationproject.features.login.presentation.states;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login init state
 *
 * @author kv
 */
public class LoginInitState extends LoginBaseState{

    /**
     * Create {@link LoginInitState}
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public Parcelable createFromParcel(Parcel aIn){
            return new LoginInitState(aIn);
        }

        public LoginInitState[] newArray(int size){
            return new LoginInitState[size];
        }
    };

    public LoginInitState(){
    }

    /**
     * @param aParcel data
     */
    private LoginInitState(final Parcel aParcel){
    }

    @Override
    public void invalidateView(@NonNull final LoginView aView){
        super.invalidateView(aView);
        LoginOwner owner = getOwner();
        boolean hasErrors = owner.getLoginValidationErrors().hasErrors();
        owner.getView().setLoginEnabled(!hasErrors);
    }

    @NonNull
    @Override
    public String getName(){
        return getClass().getName();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel aParcel, final int aI){

    }
}
