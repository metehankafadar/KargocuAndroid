package com.example.projezaferhoca;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;

public class connectionClass {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //for read
    DatabaseReference database = firebaseDatabase.getReference().child("user").child("nurlan01");
    //for write
    DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference().child("user");

    // for take data you can use everywhere


    public void takeFromDB(final Context context ) {


        ValueEventListener vel = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserOutDB uaer;
                uaer = dataSnapshot.getValue(UserOutDB.class);
                Toast.makeText(context,uaer.password,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        };
        database.addValueEventListener(vel);

// belki lazım olur alt tarafiçin geçerlidir
        /*
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserInDB user;

            if (list.size()>0){
                list.clear();
            }else {
                int i =0;
                for (DataSnapshot post:dataSnapshot.getChildren()){

                   user = post.getValue(UserInDB.class);
                    list.add(user);
                    i = i+1;
                    Toast.makeText(getApplicationContext(),list.get(i).toString(),Toast.LENGTH_LONG).show();

              } } }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/

    }

    public String kargo(String Gonderen,String Alici){

        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference().child("Kargo");

        Deliever deliever = new Deliever("","","","","","1",Gonderen,Alici,"","",UUID.randomUUID().toString());
        databaseReference.child(deliever.getId()).setValue(deliever);

        return deliever.getId();
    }

    public void addToDB(String saveusername, String savepassword, String savename, String savesurmane,String tcno,String mail,String telno,  Context context) {

        UserInDB userInDB = new UserInDB(saveusername, savepassword, savename, savesurmane,"",mail,"","","1",telno,tcno,UUID.randomUUID().toString());

        databaseReference.child(userInDB.getUsername()).setValue(userInDB);

        Toast.makeText(context, savename, Toast.LENGTH_SHORT).show();


    }

    public boolean checkUser(String username) {


        DatabaseReference verioku = firebaseDatabase.getReference().child("puanla");


        ValueEventListener vel = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserInDB user;
                user = dataSnapshot.getValue(UserInDB.class);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        };
        verioku.addValueEventListener(vel);

        return  true;

    }

public void update(String username, String lanval,String lonval){

        DatabaseReference dataref = firebaseDatabase.getReference().child("user").child(username);

        Map<String , Object> map = new HashMap<>();

        map.put("lat",lanval);
        map.put("lon",lonval);

        dataref.updateChildren(map);




}


    }




