package com.elifeindia.crm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.elifeindia.crm.R;
import com.elifeindia.crm.adapters.InvoiceListAdapter;
import com.elifeindia.crm.adapters.PaymentListAdapter;
import com.elifeindia.crm.model.PaymentRecieptList;

import java.util.List;

public class InvoiceHistoryActivity extends AppCompatActivity {
    RecyclerView rv_invoice_list;
    InvoiceListAdapter invoiceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_history);

        rv_invoice_list = findViewById(R.id.rv_invoice_list);

        List<PaymentRecieptList.PaymentReciept> paymentReciepts = null;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(InvoiceHistoryActivity.this, RecyclerView.VERTICAL, false);
        rv_invoice_list.setLayoutManager(mLayoutManager);
        invoiceListAdapter = new InvoiceListAdapter(InvoiceHistoryActivity.this, paymentReciepts);
        rv_invoice_list.setAdapter(invoiceListAdapter);

    }
}