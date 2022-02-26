package com.elifeindia.crm.view.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.elifeindia.crm.R;
import com.elifeindia.crm.contract.activities.LoginContract;
import com.elifeindia.crm.model.FooterModel;
import com.elifeindia.crm.model.HeaderModel;
import com.elifeindia.crm.model.Login;
import com.elifeindia.crm.presenter.activities.LoginPresenter;
import com.elifeindia.crm.sharedpref.Constants;
import com.elifeindia.crm.sharedpref.SharedPrefsData;
import com.elifeindia.crm.utils.ViewUtils;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginContract.Presenter presenter;
    EditText edt_username, edt_pass;
    TextView txt_version, tv_login;
    CheckBox checkBox;
    Boolean Remember_me;
    ImageView iv_show_pass;
    int i = 1;
    ViewUtils viewUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenter(this);
        presenter.start();
        viewUtils = new ViewUtils();

        iv_show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i = i + 1;
                if (i % 2 == 0) {
                    edt_pass.setTransformationMethod(null);
                } else {
                    edt_pass.setTransformationMethod(new PasswordTransformationMethod());
                }

            }
        });


        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_username.getText().toString();
                String pass = edt_pass.getText().toString();
                if (isFieldEmpty()) {
                    if (checkBox.isChecked()) {
                        presenter.loginApi(LoginActivity.this, username, pass);
                        SharedPrefsData.putString(getApplicationContext(), Constants.UserName, username, Constants.PREF_NAME);
                        SharedPrefsData.putString(getApplicationContext(), Constants.Password, pass, Constants.PREF_NAME);
                        SharedPrefsData.putBool(getApplicationContext(), Constants.Remember_me, true, Constants.PREF_NAME);

                    } else {
                        presenter.loginApi(LoginActivity.this, username, pass);
                        SharedPrefsData.putString(getApplicationContext(), Constants.UserName, "", Constants.PREF_NAME);
                        SharedPrefsData.putString(getApplicationContext(), Constants.Password, "", Constants.PREF_NAME);
                        SharedPrefsData.putBool(getApplicationContext(), Constants.Remember_me, false, Constants.PREF_NAME);
                    }


                }

            }
        });


        Remember_me = SharedPrefsData.getBool(this, Constants.Remember_me, Constants.PREF_NAME);
        if (Remember_me) {
            checkBox.setChecked(true);
        }
        edt_username.setText(SharedPrefsData.getString(this, Constants.UserName, Constants.PREF_NAME));
        edt_pass.setText(SharedPrefsData.getString(this, Constants.Password, Constants.PREF_NAME));

    }

    @Override
    public void init() {
        edt_username = findViewById(R.id.edt_username);
        edt_pass = findViewById(R.id.edt_pass);
        tv_login = findViewById(R.id.tv_login);
        checkBox = findViewById(R.id.remembermecheckbox);
        iv_show_pass = findViewById(R.id.iv_show_pass);
        txt_version = findViewById(R.id.txt_version);

        PackageManager pm = getApplicationContext().getPackageManager();
        String pkgName = getApplicationContext().getPackageName();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String ver = pkgInfo.versionName;
        txt_version.setText("Version : " + ver);

    }

    @Override
    public void showError(String message) {

        //Toast.makeText(this, "No internet connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(Login login) {
        if (login.getStatus().matches(Constants.Successful)){
            SharedPrefsData.putString(getApplicationContext(), Constants.CompanyID, login.getCompanyID().toString(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.CompanyName, login.getCompanyName(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.EmployeeCode, login.getEmployeeCode(), Constants.PREF_NAME);
            SharedPrefsData.putInt(getApplicationContext(), Constants.EmpId, login.getEmployeeID(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.EmployeeName, login.getEmployeeName(), Constants.PREF_NAME);
             SharedPrefsData.putString(getApplicationContext(), Constants.EmployeeMob, "", Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.RoleName, login.getRoleName(), Constants.PREF_NAME);
            SharedPrefsData.putInt(getApplicationContext(), Constants.RoleId, login.getRoleId(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.RoleType, login.getRoleType(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.UserId, login.getUserId().toString(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.UserName, login.getUserName(), Constants.PREF_NAME);
            SharedPrefsData.putString(getApplicationContext(), Constants.Status, login.getStatus(), Constants.PREF_NAME);
            String compId = SharedPrefsData.getString(this, Constants.CompanyID, Constants.PREF_NAME);
            presenter.loadHeader(this, compId);
            presenter.loadFooter(this, compId);
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();

        }
        else  {
            viewUtils.toast(this,"Please enter valid username and password");
        }

    }

    private boolean isFieldEmpty() {
        if (edt_username.length() == 0) {
            edt_username.setError("Please enter user name");
            edt_username.requestFocus();
            return false;
        } else if (edt_pass.length() == 0) {
            edt_pass.setError("Please enter password");
            edt_pass.requestFocus();
            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void openWhatsApp() {
        String smsNumber = "918099346155"; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("image/png");
        //sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Error/n", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(sendIntent);
    }

    @Override
    public void showHeader(HeaderModel headerModel) {
        try {
            SharedPrefsData.putString(getApplicationContext(), Constants.HEADER, headerModel.getHeader(), Constants.PREF_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFooter(FooterModel footerModel) {
        List<FooterModel.HeaderfooterDTO> footerModels = footerModel.getHeaderfooter();
        String footer = footerModel.getHeaderfooter().get(0).getDisplay_Content();
        SharedPrefsData.putString(getApplicationContext(), Constants.FOOTER, footer, Constants.PREF_NAME);

    }

}