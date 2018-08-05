package com.leventime.qualificationproject.base.core.presentation.views;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.leventime.qualificationproject.R;
import com.leventime.qualificationproject.base.core.presentation.BaseDialog;

import butterknife.BindView;

/**
 * Dialog with infinite progress
 *
 * @author kv
 */
public class ProgressDialog extends BaseDialog{

    @BindView(R.id.tvProgress)
    TextView mTvProgress;
    private String mPendingMessage;

    /**
     * Show progress dialog
     *
     * @param aFragmentManager fragment manager
     * @return dialog instance
     */
    @NonNull
    public static ProgressDialog showDialog(@NonNull FragmentManager aFragmentManager){
        String tag = ProgressDialog.class.getName();
        ProgressDialog dialog = (ProgressDialog) aFragmentManager.findFragmentByTag(tag);
        if(dialog == null){
            dialog = new ProgressDialog();
            dialog.setCancelable(false);
            FragmentTransaction ft = aFragmentManager.beginTransaction();
            ft.add(dialog, tag);
            ft.commitAllowingStateLoss();
        }
        return dialog;
    }

    /**
     * Hide progress dialog
     *
     * @param aFragmentManager fragment manager
     */
    public static void hideDialog(@NonNull FragmentManager aFragmentManager){
        String tag = ProgressDialog.class.getName();
        ProgressDialog dialog = (ProgressDialog) aFragmentManager.findFragmentByTag(tag);
        if(dialog != null){
            dialog.dismiss();
        }
    }

    @Override
    protected int getLayoutResource(){
        return R.layout.item_progress;
    }
}
