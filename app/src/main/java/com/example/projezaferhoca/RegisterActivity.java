package com.example.projezaferhoca;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,name,surname,tcno,telno,mail;
    Button signup;
    String saveusername,savepassword,savesurmane,savename,savetcno,savetelno,savemail;

   connectionClass connection= new connectionClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.et_rusername);
        password=findViewById(R.id.et_rpassword);
        name= findViewById(R.id.et_rname);
        surname=findViewById(R.id.et_rsurname);

        tcno=findViewById(R.id.et_rtc);
        telno=findViewById(R.id.et_rtelno);
        mail=findViewById(R.id.et_rmail);
        signup=findViewById(R.id.btn_rsign);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                savename=name.getText().toString();
                savesurmane=surname.getText().toString();
                saveusername=username.getText().toString();
                savepassword=password.getText().toString();
                savetcno=tcno.getText().toString();

                savetelno=telno.getText().toString();
                savemail=mail.getText().toString();

                //Toast.makeText(getApplicationContext(),"mekjgns "+savename,Toast.LENGTH_LONG).show();
               connection.addToDB(saveusername,savepassword,savename,savesurmane,savetcno,savemail,savetelno,getApplicationContext());
               connection.takeFromDB(getApplicationContext());



            }
        });
    }

}
