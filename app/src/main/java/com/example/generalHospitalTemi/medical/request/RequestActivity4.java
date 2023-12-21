package com.example.generalHospitalTemi.medical.request;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest3TemiBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest4Binding;

public class RequestActivity4 extends AppCompatActivity {
    private ActivityMedicalRequest4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}