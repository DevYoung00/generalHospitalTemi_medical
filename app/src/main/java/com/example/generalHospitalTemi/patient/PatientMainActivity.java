package com.example.generalHospitalTemi.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.generalHospitalTemi.databinding.ActivityPatientMainBinding;
import com.example.generalHospitalTemi.patient.call.DoctorCallActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;


public class PatientMainActivity extends AppCompatActivity {

    private ActivityPatientMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // go to patient register
        binding.receptionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientMainActivity.this, RegisterActivity1.class);
                startActivity(intent);
            }
        });

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

    }
}