package com.elifeindia.crm.contract.activities;

import android.content.Context;

import com.elifeindia.crm.model.ChangePasswordModel;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;

public interface ResetPasswordContract {

    interface View {
        void init();

        void showError(String message);

        void showHeader(HeaderModel paymentReciept);

        void showFooter(FooterModel paymentReciept);

        void showResult(ChangePasswordModel changePasswordModel);
    }

    interface Presenter {
        void start();

        void loadHeader(Context context, String compId);

        void loadFooter(Context context, String compId);

        void resetPasswordApi(Context context, String userId, String userName, String oldPassword, String password);
    }
}
