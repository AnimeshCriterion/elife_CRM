package com.elifeindia.crm.contract.activities;


import android.content.Context;

import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.model.RolewiseAccess;

public interface HomeContract {
    interface View {
        void init();

        void showError(String message);

        void showResult(RolewiseAccess login);

        void handleOnBackPressed();
    }

    interface Presenter {
        void start();

        void loadApi(Context context, String roleId);
    }


}
