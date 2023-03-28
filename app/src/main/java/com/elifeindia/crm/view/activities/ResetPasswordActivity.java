package com.elifeindia.crm.view.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.ResetPasswordContract;
import com.elifeindia.crm.databinding.ActivityResetPasswordBinding;
import com.elifeindia.crm.model.ChangePasswordModel;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.presenter.activities.ResetPasswordPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordContract.View {

    ResetPasswordContract.Presenter presenter;
    private ActivityResetPasswordBinding binding;
    ViewUtils viewUtils;
    String userId, username;
    int i = 1, j = 1, k = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        presenter = new ResetPasswordPresenter(this);
        presenter.start();
        viewUtils = new ViewUtils();
        userId = SharedPrefsData.getString(this, Constants.UserId, Constants.PREF_NAME);
        username = SharedPrefsData.getString(this, Constants.UserName, Constants.PREF_NAME);
        binding.ivShowPass.setOnClickListener(view -> {
            i = i + 1;
            if (i % 2 == 0) {
                binding.editTextTextPassword.setTransformationMethod(null);
            } else {
                binding.editTextTextPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.ivShowPass2.setOnClickListener(view -> {
            j = j + 1;
            if (j % 2 == 0) {
                binding.editNewPassword.setTransformationMethod(null);
            } else {
                binding.editNewPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });

        binding.ivShowPass3.setOnClickListener(view -> {
            k = k + 1;
            if (k % 2 == 0) {
                binding.editConfirmNewPassword.setTransformationMethod(null);
            } else {
                binding.editConfirmNewPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
        });


        binding.editTextTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.ivShowPass.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.editNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.ivShowPass2.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.editConfirmNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.ivShowPass3.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.imageView2.setOnClickListener(view -> onBackPressed());


        binding.button.setOnClickListener(view -> {

            if (isFieldEmpty()) {
                presenter.resetPasswordApi(ResetPasswordActivity.this, userId, username, binding.editTextTextPassword.getText().toString().trim(), binding.editNewPassword.getText().toString().trim());
            } else {

            }

        });
    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHeader(HeaderModel paymentReciept) {

    }

    private boolean isFieldEmpty() {
        String pas = binding.editNewPassword.getText().toString();
        String cPass = binding.editConfirmNewPassword.getText().toString();
        if (binding.editTextTextPassword.length() == 0) {
            binding.editTextTextPassword.setError("Please enter old password");
            binding.editTextTextPassword.requestFocus();
            return false;
        } else if (binding.editNewPassword.length() == 0) {
            binding.editNewPassword.setError("Please enter new  password");
            return false;
        } else if (!pas.equals(cPass)) {
            Toast.makeText(this, "Password and Confirm password not matching", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void showFooter(FooterModel paymentReciept) {

    }

    @Override
    public void showResult(ChangePasswordModel changePasswordModel) {
        Toast.makeText(this, "" + changePasswordModel.getError_Massage(), Toast.LENGTH_SHORT).show();

    }
}