package com.example.generalHospitalTemi.medical.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.example.generalHospitalTemi.MainActivity;
import com.example.generalHospitalTemi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CodeBlueActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_blue);
        mediaPlayer = MediaPlayer.create(this, R.raw.code_bule_sound);
        mediaPlayer.start();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("codeblue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.getValue().toString() == "false"){
                    mediaPlayer.release();
                    finish();
                }
                else{

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}