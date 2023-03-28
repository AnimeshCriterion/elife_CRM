package com.elifeindia.crm.presenter.activities;

import android.content.Context;

import com.elifeindia.crm.contract.activities.AddReportContract;
import com.elifeindia.crm.contract.activities.BillShareActivityContract;

public class BillShareActivityPresenter implements BillShareActivityContract.Presenter {
    BillShareActivityContract.View mView;

    public BillShareActivityPresenter(BillShareActivityContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadInvoiceReport(Context context, String compId, String customerId) {




    }
}
