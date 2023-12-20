package com.example.generalHospitalTemi.medical.get;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityMedicalGet2VerifyBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;

public class GetYesActivity2 extends AppCompatActivity {
    private ActivityMedicalGet2VerifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalGet2VerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.get2PrevTextVerifyYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetYesActivity2.this, MedicalMainActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });

        // 3초 후에 PatientMainActivity로 이동
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GetYesActivity2.this, GetActivity3.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        }, 3000); // 3000ms = 3초
    }
}
