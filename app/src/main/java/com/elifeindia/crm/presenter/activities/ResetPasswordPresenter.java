package com.elifeindia.crm.presenter.activities;

import android.app.ProgressDialog;
import android.content.Context;

import com.elifeindia.crm.contract.activities.ResetPasswordContract;
import com.elifeindia.crm.model.ChangePasswordModel;
import com.elifeindia.crm.networking.NetCheck;
import com.elifeindia.crm.networking.NetworkUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {

    ResetPasswordContract.View mView;

    public ResetPasswordPresenter(ResetPasswordContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadHeader(Context context, String compId) {

    }

    @Override
    public void loadFooter(Context context, String compId) {

    }

    @Override
    public void resetPasswordApi(Context context, String userId, String userName, String oldPassword, String password) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Please wait...");
        progressBar.show();
        if (NetCheck.isInternetConnection(context)) {
            NetworkUtils.getUserApiInstance()
                    .changePassword(userId, userName, oldPassword, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ChangePasswordModel>() {
                        @Override
                        public void onCompleted() {
                            progressBar.dismiss();
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.showError(e.toString());
                            progressBar.dismiss();
                        }

                        @Override
                        public void onNext(ChangePasswordModel login) {
                            mView.showResult(login);
                            progressBar.dismiss();
                        }
                    });
        } else {
            mView.showError("Connection Error");
            progressBar.dismiss();
        }
    }
}
