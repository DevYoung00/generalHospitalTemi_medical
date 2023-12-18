package com.example.generalHospitalTemi.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.generalHospitalTemi.patient.call.DoctorCallActivity;
import com.google.firebase.database.DatabaseReference;

public class PatientMainActivity extends AppCompatActivity {

    private ActivityPatientMainBinding binding;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //databaseReference = FirebaseDatabase.getInstance().getReference();

        // go to patient temperature
        binding.temperatureImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientMainActivity.this, PatientTempertureActivity.class);
                startActivity(intent);
            }
        });

        // 의료진 호출
        binding.callImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientMainActivity.this, DoctorCallActivity.class);
                startActivity(intent);
            }
        });
        /*
        databaseReference.child("rfid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object rfidValue = snapshot.getValue();
                if (rfidValue != null) {
                    binding.firebaseText.setText("RFID: " + rfidValue.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read RFID value.", error.toException());
            }
        });*/
    }
}