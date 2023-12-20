package com.example.generalHospitalTemi.patient.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;


import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity2;
import com.example.generalHospitalTemi.databinding.ActivityPatientRegister1VerifyBinding;

public class RegisterYesActivity extends AppCompatActivity {
    private ActivityPatientRegister1VerifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister1VerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register1PrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterYesActivity.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });

        // 3초 후에 PatientMainActivity로 이동
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterYesActivity.this, RegisterActivity2.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        }, 3000); // 3000ms = 3초
    }
}
