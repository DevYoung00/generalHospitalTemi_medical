package com.example.generalHospitalTemi.medical.get;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityMedicalGet2VerifyNoBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;

public class GetNoActivity2 extends AppCompatActivity {
    private ActivityMedicalGet2VerifyNoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalGet2VerifyNoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.get2PrevText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetNoActivity2.this, MedicalMainActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });

    }
}
