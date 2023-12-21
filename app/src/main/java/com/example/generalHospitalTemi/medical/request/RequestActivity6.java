package com.example.generalHospitalTemi.medical.request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest5Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest6Binding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RequestActivity6 extends AppCompatActivity {
    private ActivityMedicalRequest6Binding binding;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Arrive").setValue(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 일정 시간 대기 후에 실행될 코드
                Intent intent = new Intent(RequestActivity6.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        }, 8000); // 5초 딜레이 (밀리초 단위)

    }

}