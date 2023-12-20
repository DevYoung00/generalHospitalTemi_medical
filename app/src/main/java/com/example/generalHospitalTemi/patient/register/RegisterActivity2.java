package com.example.generalHospitalTemi.patient.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.generalHospitalTemi.databinding.ActivityPatientRegister2Binding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity2 extends AppCompatActivity {
    private ActivityPatientRegister2Binding binding;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register2MainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity2.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });


    }


}
