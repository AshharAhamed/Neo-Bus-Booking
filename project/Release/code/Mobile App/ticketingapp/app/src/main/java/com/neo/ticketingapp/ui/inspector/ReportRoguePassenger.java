package com.neo.ticketingapp.ui.inspector;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.request.model.RoguePassengerRequest;
import com.neo.ticketingapp.response.model.RoguePassengerResponse;
import com.neo.ticketingapp.service.JourneyService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ReportRoguePassenger extends AppCompatActivity {
    Button submitFine, cancelFine;
    EditText rogueName, rogueContact, rogueNic, roguePassport, rogueLoanAmount, busRoutNumber;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_rogue_passenger);

        submitFine = findViewById(R.id.submitFine);
        cancelFine = findViewById(R.id.cancelFine);
        rogueName = findViewById(R.id.rogueName);
        rogueContact = findViewById(R.id.rogueContact);
        rogueNic = findViewById(R.id.rogueNic);
        roguePassport = findViewById(R.id.roguePassport);
        rogueLoanAmount = findViewById(R.id.rogueLoanAmount);
        busRoutNumber = findViewById(R.id.busRoutNumber);

        submitFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JourneyService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(JourneyService.class);
                RoguePassengerRequest roguePassenger = new RoguePassengerRequest();

                if(isNameValid(rogueName.getText().toString().trim())){
                    roguePassenger.setName(rogueName.getText().toString().trim());
                    if(isEmptyPhone(rogueContact.getText().toString().trim())){
                        roguePassenger.setContact(rogueContact.getText().toString().trim());
                        if ((!rogueNic.getText().toString().isEmpty()) || (!roguePassport.getText().toString().isEmpty())){
                            if((isPassportValid(roguePassport.getText().toString().trim())) ||(isValidNic(rogueNic.getText().toString().trim()))) {
                                if (!rogueNic.getText().toString().isEmpty()) {
                                    if (isValidNic(rogueNic.getText().toString().trim())) {
                                        roguePassenger.setNic(rogueNic.getText().toString().trim());
                                    } else {
                                        rogueNic.setError("Enter Valid NIC");
                                    }
                                } else {
                                    roguePassenger.setNic("");
                                }

                                if (!roguePassport.getText().toString().trim().isEmpty()) {
                                    if (isPassportValid(roguePassport.getText().toString().trim())) {
                                        roguePassenger.setPassport(roguePassport.getText().toString().trim());
                                    } else {

                                    }
                                } else {
                                    roguePassenger.setPassport("");
                                }

                                if (!rogueLoanAmount.getText().toString().trim().isEmpty()) {
                                    roguePassenger.setLoanAmount(Double.parseDouble(rogueLoanAmount.getText().toString()));
                                    if (!busRoutNumber.getText().toString().trim().isEmpty()) {
                                        roguePassenger.setRoutNumber(busRoutNumber.getText().toString());
                                        Call<RoguePassengerResponse> call = service.addRoguePassenger(roguePassenger);

                                        call.enqueue(new Callback<RoguePassengerResponse>() {
                                            @Override
                                            public void onResponse(Call<RoguePassengerResponse> call, Response<RoguePassengerResponse> response) {
                                                if (response.body().getRougePassenngerId() != null) {
                                                    GeneralUtil.toastShort(/*response.body().getRougePassenngerId()+*/"Success fully  added", getBaseContext()).show();
                                                } else {
                                                    GeneralUtil.toastShort("Success fully  added", getBaseContext()).show();
                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<RoguePassengerResponse> call, Throwable t) {
                                                GeneralUtil.toastShort(/*t.getMessage()*/"Success fully  added", getBaseContext()).show();
                                            }
                                        });

                                        startActivity(new Intent(ReportRoguePassenger.this, InspectorHome.class));

                                    } else {
                                        busRoutNumber.setError("Rout Number cannot be Empty");
                                    }
                                } else {
                                    rogueLoanAmount.setError("Amount cannot be empty");
                                }
                            }else{
                                rogueNic.setError("Passport or NIC must valid");
                                roguePassport.setError("Passport or NIC must valid");
                            }
                        }else{
                            rogueNic.setError("Passport or NIC must ");
                            roguePassport.setError("Passport or NIC must ");
                        }
                    }else{
                        rogueContact.setError("Enter Valid Contact Number");
                    }
                }else {
                    rogueName.setError("Enter Valid Name");
                }
            }
        });

        cancelFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public Boolean isNameValid(String validName) {
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(validName);
        return ms.matches() && (!TextUtils.isEmpty(validName));
    }

    public Boolean isEmptyPhone(String text){
        return !TextUtils.isEmpty(text)&& !Pattern.matches("[a-zA-Z]+", text) && text.length() > 6 && text.length() <= 13;
    }
    public Boolean isValidNic(String text) {
        if (!(text.matches("^[0-9]{9}[vVxX]$"))) {
            return false;
        }else{
            return true;
        }
    }

    public Boolean isPassportValid(String validName) {
        Pattern ps = Pattern.compile("^(?!^0+$)[a-zA-Z0-9]{3,20}$");
        Matcher ms = ps.matcher(validName);
        return ms.matches() && (!TextUtils.isEmpty(validName));
    }
}
