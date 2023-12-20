package com.example.generalHospitalTemi.medical.get;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityMedicalGet3Binding;
import com.example.generalHospitalTemi.medical.DoctorCalledActivity;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;

public class GetActivity3 extends AppCompatActivity {
    private ActivityMedicalGet3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalGet3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetActivity3.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });

    }


}
