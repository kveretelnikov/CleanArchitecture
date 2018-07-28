package com.leventime.qualificationproject.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.leventime.qualificationproject.R;

import butterknife.ButterKnife;

/**
 * Stores methods for work with views
 *
 * @author kv
 */
public final class Views{

    /**
     * Action that changes visibility state of view
     */
    public static final ButterKnife.Setter<View, Integer> VISIBLE = (view, value, index) -> view.setVisibility(value);

    private Views(){
    }

    /**
     * Show error snackbar
     *
     * @param aContext context
     * @param aContainer root container
     * @param aMessage message
     * @param aDurationInMilliseconds duration show of snackbar in milliseconds
     */
    @NonNull
    public static Snackbar showErrorSnackbar(@NonNull Context aContext, @NonNull CoordinatorLayout aContainer, @NonNull String aMessage, int aDurationInMilliseconds){
        Snackbar snackbar = createSnackbar(aContext, aContainer, aMessage, aDurationInMilliseconds, TypeSnackbar.ERROR);
        snackbar.show();
        return snackbar;
    }

    /**
     * Show error snackbar with duration {@link Snackbar#LENGTH_INDEFINITE}
     *
     * @param aContext context
     * @param aContainer root container
     * @param aMessage message
     */
    @NonNull
    public static Snackbar showErrorSnackbar(@NonNull Context aContext, @NonNull CoordinatorLayout aContainer, @NonNull String aMessage){
        return showErrorSnackbar(aContext, aContainer, aMessage, Snackbar.LENGTH_INDEFINITE);
    }

    /**
     * Show message snackbar
     *
     * @param aContext context
     * @param aContainer root container
     * @param aMessage message
     * @param aDurationInMilliseconds duration show of snackbar in milliseconds
     */
    @NonNull
    public static Snackbar showMessageSnackbar(@NonNull Context aContext, @NonNull CoordinatorLayout aContainer, @NonNull String aMessage, int aDurationInMilliseconds){
        Snackbar snackbar = createSnackbar(aContext, aContainer, aMessage, aDurationInMilliseconds, TypeSnackbar.MESSAGE);
        snackbar.show();
        return snackbar;
    }

    /**
     * Show message snackbar with duration {@link Snackbar#LENGTH_INDEFINITE}
     *
     * @param aContext context
     * @param aContainer root container
     * @param aMessage message
     */
    @NonNull
    public static Snackbar showMessageSnackbar(@NonNull Context aContext, @NonNull CoordinatorLayout aContainer, @NonNull String aMessage){
        return showMessageSnackbar(aContext, aContainer, aMessage, Snackbar.LENGTH_INDEFINITE);
    }

    /**
     * Create snackbar
     *
     * @param aContext context
     * @param aContainer root container
     * @param aMessage message
     * @param aDurationInMilliseconds duration show of snackbar in milliseconds
     * @param aSnackbarType snackbar type
     */
    @NonNull
    private static Snackbar createSnackbar(@NonNull Context aContext,
                                           @NonNull View aContainer,
                                           @NonNull String aMessage,
                                           int aDurationInMilliseconds,
                                           TypeSnackbar aSnackbarType){
        Snackbar snackbar = Snackbar.make(aContainer, aMessage, aDurationInMilliseconds);
        View view = snackbar.getView();
        if(aSnackbarType == TypeSnackbar.ERROR){
            view.setBackgroundColor(ContextCompat.getColor(aContext, R.color.snackbar_error_background));
        } else if(aSnackbarType == TypeSnackbar.MESSAGE){
            view.setBackgroundColor(ContextCompat.getColor(aContext, R.color.snackbar_message_background));
        }
        TextView tvMessage = view.findViewById(android.support.design.R.id.snackbar_text);
        int colorRes = ContextCompat.getColor(aContext, R.color.snackbar_message_text);
        tvMessage.setTextColor(colorRes);
        return snackbar;
    }

    /**
     * Type show snackbar
     */
    private enum TypeSnackbar{
        /**
         * Snackbar show error message
         */
        ERROR,
        /**
         * Snackbar show notification message
         */
        MESSAGE
    }

}
