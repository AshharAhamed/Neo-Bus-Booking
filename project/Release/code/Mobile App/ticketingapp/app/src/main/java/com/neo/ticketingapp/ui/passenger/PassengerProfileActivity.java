package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.PassengerAccountResult;
import com.neo.ticketingapp.service.PassengerAccountService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView accountTxt;
    private TextView codeTxt;
    private EditText firstNameTxt;
    private EditText lastNameTxt;
    private EditText emailTxt;
    private EditText mobileTxt;
    private TextView typeTxt;
    private EditText nicTxt;
    private TextView dobTxt;
    private ImageView editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_profile);
        this.initializeUIObjects();
        this.getPassengerAccountDetails();
    }

    //data binding
    private void initializeUIObjects() {
        this.accountTxt = findViewById(R.id.AccountTxt);
        this.codeTxt = findViewById(R.id.CodeTxt);
        this.firstNameTxt = findViewById(R.id.FirstNameTxt);
        this.lastNameTxt = findViewById(R.id.LastNameTxt);
        this.emailTxt = findViewById(R.id.EmailTxt);
        this.mobileTxt = findViewById(R.id.MobileTxt);
        this.typeTxt = findViewById(R.id.TypeTxt);
        this.nicTxt = findViewById(R.id.NICTxt);
        this.dobTxt = findViewById(R.id.DoBTxt);
        this.editBtn = findViewById(R.id.EditProfileBtn);
    }

    //render passenger account details
    private void getPassengerAccountDetails() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Call<PassengerAccountResult> call = service.getPassengerAccount(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<PassengerAccountResult>() {
            @Override
            public void onResponse(Call<PassengerAccountResult> call, Response<PassengerAccountResult> response) {
                accountTxt.setText(response.body().getAccountId());
                codeTxt.setText(response.body().getCardNo());
                firstNameTxt.setText(response.body().getFirstName());
                lastNameTxt.setText(response.body().getLastName());
                emailTxt.setText(response.body().getEmail());
                mobileTxt.setText(response.body().getContact());
                typeTxt.setText(response.body().getType());
                nicTxt.setText(response.body().getNic());
                dobTxt.setText(GeneralUtil.convertMongoDate(response.body().getDob()));
            }

            @Override
            public void onFailure(Call<PassengerAccountResult> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }

    private void UpdatePassengerAccountDetails() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        PassengerAccountResult accountResult = new PassengerAccountResult();
        accountResult.setAccountId(accountTxt.getText().toString());
        accountResult.setCardNo(codeTxt.getText().toString());
        accountResult.setFirstName(firstNameTxt.getText().toString());
        accountResult.setLastName(lastNameTxt.getText().toString());
        accountResult.setEmail(emailTxt.getText().toString());
        accountResult.setContact(mobileTxt.getText().toString());
        accountResult.setType(typeTxt.getText().toString());
        accountResult.setNic(nicTxt.getText().toString());
        //accountResult.setDob(dobTxt.getText().toString());

        Call<PassengerAccountResult> call = service.updatePassengerAccount(new PassengerAccountResult(accountResult.getAccountId(), accountResult.getFirstName(), accountResult.getLastName(), accountResult.getEmail(), accountResult.getContact(), accountResult.getType(), accountResult.getNic(), accountResult.getCardNo()));
        call.enqueue(new Callback<PassengerAccountResult>() {
            @Override
            public void onResponse(Call<PassengerAccountResult> call, Response<PassengerAccountResult> response) {
                getPassengerAccountDetails();
                GeneralUtil.toastShort("Profile Details Updated Successfully ", getApplicationContext()).show();
            }

            @Override
            public void onFailure(Call<PassengerAccountResult> call, Throwable t) {
                //GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });
    }

    private Boolean isValid() {
        if (isNameValid(firstNameTxt)) {
            if (isNameValid(lastNameTxt)) {
                if (isEmailValid(emailTxt)) {
                    if (isMobileValid(mobileTxt)) {
                        if (isNICValid(nicTxt)) {
                            return true;
                        } else {
                            GeneralUtil.toastShort("Please enter a valid NIC Number", getApplicationContext()).show();
                            return false;
                        }
                    } else {
                        GeneralUtil.toastShort("Please enter a valid mobile number", getApplicationContext()).show();
                        return false;
                    }
                } else {
                    GeneralUtil.toastShort("Please enter a valid email !", getApplicationContext()).show();
                    return false;
                }
            } else {
                GeneralUtil.toastShort("Please enter a valid last name !", getApplicationContext()).show();
                return false;
            }
        } else {
            GeneralUtil.toastShort("Please enter a valid first name !", getApplicationContext()).show();
            return false;
        }
    }

    public Boolean isNameValid(EditText text) {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(text.getText().toString());
        return ms.matches() && (!TextUtils.isEmpty(text.getText().toString()));
    }


    public Boolean isEmailValid(EditText text) {
        return (!TextUtils.isEmpty(text.getText().toString()) && Patterns.EMAIL_ADDRESS.matcher(text.getText().toString()).matches());
    }

    public Boolean isMobileValid(EditText text) {
        if (!TextUtils.isEmpty(text.getText().toString())) {
            if (text.getText().length() == 10) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean isNICValid(EditText text) {
        if (!TextUtils.isEmpty(text.getText().toString())) {
            if (text.getText().length() == 10 || text.getText().length() == 12) {
                if (text.getText().length() == 10) {
                    char[] nicArr = text.getText().toString().toCharArray();
                    if (nicArr[9] == 'V' || nicArr[9] == 'v') {
                        return true;
                    }
                } else if (text.getText().length() == 12) {
                    if (TextUtils.isDigitsOnly(text.getText())) {
                        return true;
                    }
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.EditProfileBtn) {
            if (isValid()) {
                UpdatePassengerAccountDetails();
                GeneralUtil.toastShort("Profile Details Updated Successfully ", getApplicationContext()).show();
            }
        }
    }
}
