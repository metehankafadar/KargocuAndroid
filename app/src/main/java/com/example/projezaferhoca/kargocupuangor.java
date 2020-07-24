package com.example.projezaferhoca;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class kargocupuangor extends AppCompatActivity {
    TextView tvhizpuan,tvsaglampuan,tvguvenpuan;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kargocupuangor);
        tvhizpuan=findViewById(R.id.tv_hizpuan);
        tvguvenpuan=findViewById(R.id.tv_guvenpuan);
        tvsaglampuan=findViewById(R.id.tv_saglampuan);
        final String kargocu=getIntent().getExtras().getString("un","def");
        DatabaseReference dataref = firebaseDatabase.getReference().child("puanla").child(kargocu);

        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kargocuPuanOut kargoout;
                kargoout=dataSnapshot.getValue(kargocuPuanOut.class);

                double hiz=Double.valueOf(kargoout.hiz);
                double guven=Double.valueOf(kargoout.guven);
                double saglamlik=Double.valueOf(kargoout.saglamlik);

                tvhizpuan.setText(String.valueOf(hiz));
                tvguvenpuan.setText(String.valueOf(guven));
                tvsaglampuan.setText(String.valueOf(saglamlik));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}