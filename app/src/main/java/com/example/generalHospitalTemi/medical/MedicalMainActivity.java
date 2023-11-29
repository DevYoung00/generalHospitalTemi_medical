package com.example.generalHospitalTemi.medical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMainBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;

public class MedicalMainActivity extends AppCompatActivity {

    private ActivityMedicalMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}