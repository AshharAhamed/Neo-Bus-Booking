package com.neo.ticketingapp.ui.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.neo.ticketingapp.R;
import com.neo.ticketingapp.adapter.PassengerLogAdapter;
import com.neo.ticketingapp.common.GeneralUtil;
import com.neo.ticketingapp.response.model.PassengerLogResponse;
import com.neo.ticketingapp.service.PassengerAccountService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerLog extends AppCompatActivity implements View.OnClickListener {

    private ListView passengerLogList;
    private ImageView generateReportBtn;
    private LinearLayout passengerLogLinearLayout;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_log);
        this.initializeUIObjects();
        this.getLog();
    }

    //data binding
    private void initializeUIObjects() {
        this.passengerLogList = findViewById(R.id.passengerLogList);
        this.generateReportBtn = findViewById(R.id.PassengerLogGenerateReportBtn);
        this.passengerLogLinearLayout = findViewById(R.id.PassengerLogLinearLayout);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    //render Passenger past journeys
    private void getLog() {
        PassengerAccountService service = GeneralUtil.getGeneralUtilInstance().getRetroFit().create(PassengerAccountService.class);
        Call<List<PassengerLogResponse>> call = service.getPassengerLog(GeneralUtil.getGeneralUtilInstance().getTravelCardID());

        call.enqueue(new Callback<List<PassengerLogResponse>>() {
            @Override
            public void onResponse(Call<List<PassengerLogResponse>> call, Response<List<PassengerLogResponse>> response) {
                passengerLogList.setAdapter(new PassengerLogAdapter(getBaseContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<PassengerLogResponse>> call, Throwable t) {
                //To get network errors
                GeneralUtil.toastShort(t.getMessage(), getBaseContext()).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.PassengerLogGenerateReportBtn) {
            Log.d("size", " " + passengerLogLinearLayout.getWidth() + " " + passengerLogLinearLayout.getHeight());
            bitmap = loadBitmapFromView(passengerLogLinearLayout, passengerLogLinearLayout.getWidth(), passengerLogLinearLayout.getHeight());
            createPdf();
        }
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    private void createPdf() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        float width = displayMetrics.widthPixels;

        int convertHeight = (int) height, convertWidth = (int) width;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHeight, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        //Write the document Content
        String targetPdf = "/sdcard/pdffromlayout.pdf";
        File filePath;

        filePath = new File(targetPdf);

        try {
            document.writeTo(new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            GeneralUtil.toastShort(e.toString(), getApplicationContext()).show();
            e.printStackTrace();
        }

        //close the document
        document.close();
        GeneralUtil.toastShort("Report Created !", getApplicationContext()).show();
        openGeneratedPDF();

    }

    private void openGeneratedPDF() {
        File file = new File("/sdcard/pdffromlayout.pdf");
        GeneralUtil.toastShort("This Calls", getApplicationContext()).show();
        if (file.exists()) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            GeneralUtil.toastShort(uri.toString(), getApplicationContext()).show();
            intent.setDataAndType(uri, "application/pdf");

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                GeneralUtil.toastShort("No Application to view PDF", getApplicationContext()).show();
            }
        }
    }
}
