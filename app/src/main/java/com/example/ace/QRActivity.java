package com.example.ace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QRActivity extends AppCompatActivity {
    private ImageView qrCodeView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Intent myIntent = getIntent();
        String ID = myIntent.getStringExtra("ID");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(ID + "님의 QR 코드");

        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        SimpleDateFormat format = new SimpleDateFormat(("yyyyMMddhhmmss"));
        String getTime = format.format(currentDate);

        qrCodeView = (ImageView) findViewById(R.id.imageView);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            // QR Information: 01055558888, 20210920113030
            BitMatrix bitMatrix = multiFormatWriter.encode(ID + ", " + getTime, BarcodeFormat.QR_CODE,size.x - 100,size.y - 100);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCodeView.setImageBitmap(bitmap);
        }catch (Exception e){
            Log.d("error", "QR Code Generator Error!");
        }

    }
}
