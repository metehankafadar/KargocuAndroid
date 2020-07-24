package com.example.projezaferhoca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class KargocupuanAcvity extends AppCompatActivity {
    SeekBar sbhiz,sbguven,sbdeneme12,sbsaglam;
    static int hiz=1,saglam=1,guven=1,deneme=1;
    TextView tvdeneme;
     static int guven1,hiz1,saglam1;
     Button kpuanver;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    DatabaseReference databasefor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargocupuan_acvity);
        sbhiz=findViewById(R.id.sb_hiz);
        sbguven=findViewById(R.id.sb_guven);
        sbdeneme12=findViewById(R.id.sb_deneme);
        sbsaglam=findViewById(R.id.sb_saglam);
        kpuanver=findViewById(R.id.btn_kPuanver);

        sbhiz.setMax(10);
        sbguven.setMax(10);
        sbsaglam.setMax(10);

        final String kargocuadi = getIntent().getExtras().getString("kargocuad", "");
        databaseReference= firebaseDatabase.getInstance().getReference().child("puanla");

        final String kargocuid = getIntent().getExtras().getString("kargoid");
        databasefor=firebaseDatabase.getInstance().getReference().child("Kargo").child(kargocuid).child("status");


        //Toast.makeText(getApplicationContext(),"Hhhhhhhh "+kargocuid+" ohhhhhhhu",Toast.LENGTH_LONG).show();

        sbhiz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hiz=progress;
                Toast.makeText(getApplicationContext(),"Hız "+hiz+" oldu",Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            hiz1=hiz;
            }
        });
        sbguven.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                guven=progress;
                Toast.makeText(getApplicationContext(),"Güvenilirlik "+guven+" oldu",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                guven1=guven;
            }
        });
        sbsaglam.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                saglam=progress;
                Toast.makeText(getApplicationContext(),"Saglamlık "+saglam+" oldu",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                saglam1=saglam;
            }
        });


        kpuanver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference dataref = firebaseDatabase.getReference().child("puanla").child(kargocuadi);

                dataref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        kargocuPuanOut kargoout;
                        kargoout=dataSnapshot.getValue(kargocuPuanOut.class);
                        double hizz,guvenn,saglamlikk;
                        double hiz=Double.valueOf(kargoout.hiz);
                        double guven=Double.valueOf(kargoout.guven);
                        double saglamlik=Double.valueOf(kargoout.saglamlik);
                        double sonpuan;
                        int count=Integer.parseInt(kargoout.cnt);
                        int intcount = count+1;


                        hizz=hiz*count;
                        guvenn=guven*count;
                        saglamlikk=saglamlik*count;

                        hizz=(hizz+hiz1)/(count+1);
                        guvenn=(guvenn+guven1)/(count+1);
                        saglamlikk=(saglamlikk+saglam1)/(count+1);

                        sonpuan=(hizz+guvenn+saglamlikk)/3;

                        kargocuPuanIn kargocupuanin = new kargocuPuanIn(String.valueOf(saglamlikk),String.valueOf(guvenn),String.valueOf(hizz),String.valueOf(sonpuan),String.valueOf(intcount));
                        databaseReference.child(kargocuadi).setValue(kargocupuanin);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(),"kargo puanlamanız başarılı",Toast.LENGTH_SHORT).show();
                //

                Intent gonderr=new Intent(getApplicationContext(), User_Ativity.class);
                startActivity(gonderr);
                databasefor.setValue("0");


            }
        });

    }





}
