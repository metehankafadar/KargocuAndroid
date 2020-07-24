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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class User_Ativity extends AppCompatActivity {

    String kargocuadi = null;
    Button gonder,onay;
    final Activity activity=this;
    String kargocuid=null;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    String kargoalici=null;
    static String name=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_ativity);
        gonder=findViewById(R.id.btn_usergonder);
        onay=findViewById(R.id.btn_useronay);
     //   name = getIntent().getExtras().getString("un");
       // Toast.makeText(this,ff, Toast.LENGTH_SHORT).show();
        //name=ff;
/*
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("un");
*/

        onay.setOnClickListener(new View.OnClickListener() {
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
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                intent.putExtra("un",name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        name = getIntent().getExtras().getString("un");
        if(result != null){
            if (result.getContents()== null){
                 Toast.makeText(this,"You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }
            else {
                final String kargoid;
                kargoid=result.getContents();
               // Toast.makeText(getApplicationContext(),kargoid,Toast.LENGTH_LONG).show();
                final DatabaseReference database = firebaseDatabase.getInstance().getReference().child("Kargo").child(kargoid);
                //Toast.makeText(getApplicationContext(),kargoid,Toast.LENGTH_LONG).show();
                //final ValueEventListener vel = new ValueEventListener()
                database.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String statu;
                        DeliverOutDB kargobilgi;
                        kargobilgi =  dataSnapshot.getValue(DeliverOutDB.class);
                        kargocuadi =kargobilgi.kargocu;
                        kargoalici=kargobilgi.alici;
                        statu=kargobilgi.status;

                        if (statu.equals("0")){
                            Toast.makeText(getApplicationContext(),"kargo zaten alınmış",Toast.LENGTH_LONG).show();
                            //onBackPressed();
                           // stopLockTask();
                        }else if (!name.equals(null)){

                            if ( name.equals(kargoalici) && statu.equals("1")){
                                Intent puanlama = new Intent(getApplicationContext(), KargocupuanAcvity.class);
                                puanlama.putExtra("kargocuad", kargocuadi);
                                puanlama.putExtra("kargoid",kargoid);
                                startActivity(puanlama);
                            }else if(name.equals(kargoalici) && statu.equals("0")){
                                Toast.makeText(getApplicationContext(),"gidemezsin karagözlüm",Toast.LENGTH_LONG).show();
                                //stopLockTask();
                                //onBackPressed();

                            }else{
                                Toast.makeText(getApplicationContext(),"Sizin kargouz değil",Toast.LENGTH_LONG).show();
                                //stopLockTask();
                                //onBackPressed();
                            }
                        }



                        // Toast.makeText(getApplicationContext(),deneme+"denemeden geleler",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

}

