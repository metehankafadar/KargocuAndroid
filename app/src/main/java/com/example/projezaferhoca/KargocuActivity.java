package com.example.projezaferhoca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KargocuActivity extends AppCompatActivity {
    Button btnk1,btnkdetail,btnkqrread;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargocu);
        btnk1=findViewById(R.id.btn_k1);
        btnkdetail=findViewById(R.id.btn_kdetail);
        btnkqrread=findViewById(R.id.btn_kQRread);
        final String kargocu = getIntent().getExtras().getString("un", "");
        final Activity activity=this;

        btnkqrread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator=new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        btnk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),kargocupuangor.class);
                intent.putExtra("un",kargocu);
                startActivity(intent);

            }
        });


    }

    public void TakeDelivererId(){




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if (result.getContents()== null){
                Toast.makeText(this,"You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }
            else {
                String kargoid;
                String kargocuname=getIntent().getExtras().getString("un","def");

                kargoid=result.getContents();
                Toast.makeText(this,kargoid+"burayageldi",Toast.LENGTH_LONG).show();


                DatabaseReference dataref = firebaseDatabase.getInstance().getReference().child("Kargo").child(kargoid);
                Map<String , Object> map = new HashMap<>();
                map.put("kargocu",kargocuname);
                dataref.updateChildren(map);


                // kargoid'nin alcısının ismi....
                //alıcının lan lon u
             //   String gonderilcek=result.getContents();
              //  Intent send5=new Intent(kargocuYeri.this,MapsActivity.class);
               // send5.putExtra("takeun",gonderilcek);
               // startActivity(send5);
                final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference().child("Kargo").child(kargoid);


                ValueEventListener vel = new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        DeliverOutDB deliever;
                        deliever = dataSnapshot.getValue(DeliverOutDB.class);
                        String alici=deliever.alici;
                        DatabaseReference dbref = firebaseDatabase.getInstance().getReference().child("user").child(alici);
                       ValueEventListener valevent = new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               UserOutDB user;
                               user=dataSnapshot.getValue(UserOutDB.class);
                               String lat=user.lat;
                               String lon=user.lon;
                               Toast.makeText(getApplicationContext(),lat+" "+lon,Toast.LENGTH_LONG).show();
                               Intent gonder=new Intent(getApplicationContext(),harita.class);
                               gonder.putExtra("lat",lat);
                               gonder.putExtra("lon",lon);
                               startActivity(gonder);
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {

                           }
                       };
                       dbref.addValueEventListener(valevent);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                };
                databaseReference.addValueEventListener(vel);




            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
