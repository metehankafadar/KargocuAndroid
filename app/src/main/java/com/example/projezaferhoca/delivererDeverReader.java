package com.example.projezaferhoca;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class delivererDeverReader extends AppCompatActivity {

    String deger;
    final Activity activity=this;
       public  void deneme(){

           IntentIntegrator integrator=new IntentIntegrator(activity);
           integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
           integrator.setPrompt("Scan");
           integrator.setCameraId(0);
           integrator.setBeepEnabled(false);
           integrator.setBarcodeImageEnabled(false);
           integrator.initiateScan();

       }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String resultval=null;
        if(result != null){
            if (result.getContents()== null){
                Toast.makeText(this,"okundu -> delivererDeverReader ", Toast.LENGTH_SHORT).show();
            }
            else {
                String adi ;
                adi=result.getContents();
              //  deger = adi;
               Toast.makeText(this,adi+"deenemeee",Toast.LENGTH_LONG).show();





            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }





}
