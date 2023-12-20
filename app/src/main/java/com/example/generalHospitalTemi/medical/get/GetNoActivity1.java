package com.example.generalHospitalTemi.medical.get;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityMedicalGet1NoBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;

public class GetNoActivity1 extends AppCompatActivity {
    private ActivityMedicalGet1NoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalGet1NoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetNoActivity1.this, MedicalMainActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료
            }
        });

    }
}
