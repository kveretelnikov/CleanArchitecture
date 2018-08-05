package com.leventime.qualificationproject.features.login.presentation.states;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.leventime.qualificationproject.features.login.presentation.LoginView;

/**
 * Describe login processing state
 *
 * @author kv
 */
public class LoginProcessingState extends LoginBaseState{

    /**
     * Create {@link LoginProcessingState}
     */
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public Parcelable createFromParcel(Parcel aIn){
            return new LoginProcessingState(aIn);
        }

        public LoginProcessingState[] newArray(int size){
            return new LoginProcessingState[size];
        }
    };

    public LoginProcessingState(){
    }

    private LoginProcessingState(final Parcel aParcel){
    }

    @Override
    public void onEnter(@NonNull final LoginView aView){
        super.onEnter(aView);
        getOwner().login();
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
