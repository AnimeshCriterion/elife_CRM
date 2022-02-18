package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.Login;


public interface LoginContract {
    interface View {
        void init();

        void showError(String message);

        void showHeader(HeaderModel paymentReciept);

        void showFooter(FooterModel paymentReciept);

        void showResult(Login login);
    }

    interface Presenter {
        void start();

        void loadHeader(Context context, String compId);

        void loadFooter(Context context, String compId);

        void loginApi(Context context, String username, String pass);
    }


}
