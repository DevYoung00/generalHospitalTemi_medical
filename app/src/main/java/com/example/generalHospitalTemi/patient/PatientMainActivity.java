package com.example.generalHospitalTemi.patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.generalHospitalTemi.databinding.ActivityPatientMainBinding;
import com.example.generalHospitalTemi.patient.call.DoctorCallActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.robotemi.sdk.Robot;


public class PatientMainActivity extends AppCompatActivity {

    private ActivityPatientMainBinding binding;
    RoboTemiListeners roboTemiListeners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        roboTemiListeners = new RoboTemiListeners();
//        skidJoy((float) -1.0, 1);
//        skidJoy((float) 1.0,1);
//        skidJoy((float) 0.5,1);
//        skidJoy((float) -0.5,1);
        // go to patient register
        binding.receptionImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientMainActivity.this, RegisterActivity1.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
    public void skidJoy(float vel , int sec) {// 이동 예시
        long t = System.currentTimeMillis();
        long end = t + 1000*sec;
        while (System.currentTimeMillis() < end) {
            roboTemiListeners.skidJoy(vel, 0F);
        }
    }
}