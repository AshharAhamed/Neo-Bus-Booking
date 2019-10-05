package com.neo.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.Card;
import com.neo.ticketingapp.response.model.StringResponse;
import com.neo.ticketingapp.service.PassengerAccountService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private ImageView recoverPwdBtn;
    private EditText code;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initializeObject();
    }

    private void initializeObject() {
        this.recoverPwdBtn = findViewById(R.id.RecoverPwdBtn);
        this.code = findViewById(R.id.RecoverEditText);
    }

    private void popUpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(ForgotPassword.this).inflate(R.layout.password_recovery_processing, viewGroup, false);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void closeDialog() {
        alertDialog.hide();
    }

    private void requestRecoverPwd() {
        popUpDialog();
        if (code.getText() != null) {
            PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
            Call<StringResponse> call = service.requestRecoverPwd(code.getText().toString());
            call.enqueue(new Callback<StringResponse>() {
                @Override
                public void onResponse(Call<StringResponse> call, Response<StringResponse> response) {
                    closeDialog();
                    GeneralUtil.toastShort("Recovery Details sent to your email !", getBaseContext()).show();
                    GeneralUtil.toastShort("Please Log In with the credentials !", getApplicationContext()).show();
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<StringResponse> call, Throwable t) {
                    closeDialog();
                    GeneralUtil.toastShort("Recovery Details sent to your email !", getBaseContext()).show();
                    GeneralUtil.toastShort("Please Log In with the credentials !", getApplicationContext()).show();
                    Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                    startActivity(intent);
                    //GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.RecoverPwdBtn) {
            requestRecoverPwd();
        }
    }
}
