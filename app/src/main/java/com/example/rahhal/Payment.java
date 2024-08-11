/*package com.example.rahhal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Payment extends AppCompatActivity {
    Button button ;
    Info_Personal personal = new Info_Personal();
    String[]  informationArray = new String[]{ "Nom et Prenom","CIN" ,"Date de rendez-vous", "Prix" };
    String[]  infoArray = new String[]{ personal.getName(), personal.getCi(),Randez_vous.getCurrentDateString(), "250 dh" };
    Bitmap bmp ,scaledBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        button = findViewById(R.id.button);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        scaledBitmap = Bitmap.createScaledBitmap(bmp ,200, 200, false);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatpdf();
                Toast.makeText( Payment.this,"pdf telecharger avec succee",Toast.LENGTH_LONG ).show();
                Intent patientIntent = new Intent(Payment.this,LoginPatient.class);

                startActivity(patientIntent);

            }
        });
    }
    public void creatpdf(){
        PdfDocument document = new PdfDocument();
        Paint myPaint  = new Paint();
        PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250, 250, 1).create();
        PdfDocument.Page page = document.startPage(myPageInfo1);
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(scaledBitmap,40,40,myPaint);
        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTextSize(12.0f);
        canvas.drawText(" ALLO DOCTOR " , myPageInfo1.getPageWidth()/2,40, myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(6.0f);
        myPaint.setColor(Color.rgb(122,119,119));
        canvas.drawText("les frais est inclus " ,10,myPageInfo1.getPageHeight()-20 , myPaint);
        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(8.0f);
        myPaint.setColor(Color.BLACK);
        int startXPosition=10;
        int startxPosition=80;
        int endXPosition = myPageInfo1.getPageWidth()-10;
        int startYPosition=100;
        for(int i=0 ;i<4; i++){
            canvas.drawText(informationArray[i],startXPosition,startYPosition,myPaint );
            canvas.drawText(infoArray[i],startxPosition+7,startYPosition,myPaint );
            canvas.drawLine(startXPosition, startYPosition+3, endXPosition, startYPosition+3,myPaint);
            startYPosition+=20;
        }
        canvas.drawLine(86,92,86,162,myPaint);
        document.finishPage(page);
        File file = new File(Environment.getExternalStorageDirectory(),"/rendez_vous.pdf");
        try {
            document.writeTo((new FileOutputStream(file)));
        }catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
    }
}*/