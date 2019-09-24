package com.neo.ticketingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.request.model.LoginRequest;
import com.neo.ticketingapp.response.model.LoginResult;
import com.neo.ticketingapp.service.LoginService;
import com.neo.ticketingapp.ui.inspector.InspectorHome;
import com.neo.ticketingapp.ui.passenger.PassengerHome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText loginUserName = findViewById(R.id.loginUsernameTxt);
        final EditText loginPwd = findViewById(R.id.loginPasswordTxt);
        final ImageView loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginUserName.getText().toString();
                String password = loginPwd.getText().toString();
                //validate form
                if (validateLogin(username, password)) {
                    //do login
                    doLogin(username, password);
                }
            }
        });
    }

    //Validate fields
    private boolean validateLogin(String username, String password){
        //Username
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        //Password
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username,final String password){

        LoginService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(LoginService.class);

        Call<LoginResult> call = service.logUser(new LoginRequest(username,password));

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.body().getLoginFlag().equals("true") && (response.body().getType().equals("Local") || (response.body().getType().equals("Foreign")))){
                    GeneralUtil.getGeneralUtilInstance().setTravelCardID(response.body().getUsername());
                    startActivity(new Intent(SignInActivity.this, PassengerHome.class));
                }else if(response.body().getLoginFlag().equals("true") && (response.body().getType().equals("Inspector"))){
                    GeneralUtil.getGeneralUtilInstance().setTravelCardID(response.body().getUsername());
                    startActivity(new Intent(SignInActivity.this, InspectorHome.class));
                }
                else {
                    GeneralUtil.toastShort("Invalid Username or Password", getBaseContext()).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });
    }
}
