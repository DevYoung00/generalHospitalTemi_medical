package com.example.generalHospitalTemi.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.example.generalHospitalTemi.databinding.ActivityPatientMainBinding;

public class PatientMainActivity extends AppCompatActivity {

    private ActivityPatientMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}