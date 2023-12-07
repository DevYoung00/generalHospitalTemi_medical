package com.example.generalHospitalTemi.patient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityPatientMainBinding;
import com.example.generalHospitalTemi.databinding.ActivityPatientTempertureBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientTempertureActivity extends AppCompatActivity {

    private ActivityPatientTempertureBinding binding;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientTempertureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
/*
        // go to patient temperature
        binding.temperatureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientTempertureActivity.this, com.example.generalHospitalTemi.databinding.ActivityPatientTempertureBinding.class);
                startActivity(intent);
            }
        });

        databaseReference.child("temperture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object rfidValue = snapshot.getValue();
                if (rfidValue != null) {
                    binding.firebaseText.setText("RFID: " + rfidValue.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read temperture value.", error.toException());
            }
        });*/
    }
}