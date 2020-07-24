package com.example.projezaferhoca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button btnmsign,btnmlogin;


    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);


        // kullanıcı girişi
         username.setText("mete");
         password.setText("12345");
        // buraya kadar


        btnmsign=findViewById(R.id.btn_k1);
        btnmlogin=findViewById(R.id.btn_mlogin);



        btnmsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(ıntent);
            }
        });

        btnmlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference database = firebaseDatabase.getReference().child("user").child(username.getText().toString());
                ValueEventListener vel = new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) throws  RuntimeException  {

                        UserOutDB user;
                        user = dataSnapshot.getValue(UserOutDB.class);

                        if (password.getText().toString().equals(user.password) && username.getText().toString().equals(user.username)){

                            if(user.role.equals("1")){
                                Toast.makeText(getApplicationContext(),"merhaba kargocu",Toast.LENGTH_LONG).show();

                                Intent intent1 = new Intent(getApplicationContext(),KargocuActivity.class);
                                intent1.putExtra("un",user.username);
                                startActivity(intent1);
                            }else {
                                Toast.makeText(getApplicationContext(),"merhaba kullanıcı",Toast.LENGTH_LONG).show();

                            Intent intent=new Intent(getApplicationContext(),User_Ativity.class);
                            intent.putExtra("un",user.username);
                            startActivity(intent);
                        }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                        Toast.makeText(getApplicationContext(),"hatalı",Toast.LENGTH_LONG).show();

                    }
                };
                database.addValueEventListener(vel);

            }
        });

    }
}
