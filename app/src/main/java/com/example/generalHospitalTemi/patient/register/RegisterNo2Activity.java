package com.example.generalHospitalTemi.patient.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityPatientRegister2VerifyNoBinding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;

public class RegisterNo2Activity extends AppCompatActivity {
    private ActivityPatientRegister2VerifyNoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister2VerifyNoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register2PrevText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterNo2Activity.this, RegisterActivity2.class);
                startActivity(intent);
            }
        });
    }
}
