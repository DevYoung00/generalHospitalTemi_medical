package com.example.generalHospitalTemi.medical.request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalEmergencyBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest1Binding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.call.DoctorCallActivity;
import com.example.generalHospitalTemi.patient.call.DoctorComeActivity;

public class RequestActivity1 extends AppCompatActivity {
    private ActivityMedicalRequest1Binding binding;
    int selectIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1번 클릭 시
        binding.requestButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 1;
                ImageView requestButton1Clicked = findViewById(R.id.request_button1_clicked);
                requestButton1Clicked.setVisibility(View.VISIBLE);
                ImageView requestButton1 = findViewById(R.id.request_button1);
                requestButton1.setVisibility(View.INVISIBLE);

            }
        });
        // 1번 클릭 해제
        binding.requestButton1Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton1 = findViewById(R.id.request_button1);
                requestButton1.setVisibility(View.VISIBLE);
                ImageView requestButton1Clicked = findViewById(R.id.request_button1_clicked);
                requestButton1Clicked.setVisibility(View.INVISIBLE);

            }
        });

        // 2번 클릭 시
        binding.requestButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 2;
                ImageView requestButton2Clicked = findViewById(R.id.request_button2_clicked);
                requestButton2Clicked.setVisibility(View.VISIBLE);
                ImageView requestButton2 = findViewById(R.id.request_button2);
                requestButton2.setVisibility(View.INVISIBLE);

            }
        });

        // 2번 클릭 해제
        binding.requestButton2Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton2 = findViewById(R.id.request_button2);
                requestButton2.setVisibility(View.VISIBLE);
                ImageView requestButton2Clicked = findViewById(R.id.request_button2_clicked);
                requestButton2Clicked.setVisibility(View.INVISIBLE);

            }
        });
        // 3번 클릭 시
        binding.requestButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 3;
                ImageView requestButton3 = findViewById(R.id.request_button3);
                requestButton3.setVisibility(View.INVISIBLE);
                ImageView requestButton3Clicked = findViewById(R.id.request_button3_clicked);
                requestButton3Clicked.setVisibility(View.VISIBLE);

            }
        });
        // 3번 클릭 해제
        binding.requestButton3Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton3Clicked = findViewById(R.id.request_button3_clicked);
                requestButton3Clicked.setVisibility(View.INVISIBLE);
                ImageView requestButton3 = findViewById(R.id.request_button3);
                requestButton3.setVisibility(View.VISIBLE);

            }
        });

        // go to next
        binding.requestNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectIndex!= 0){
                    Intent intent = new Intent(RequestActivity1.this, RequestActivity2.class);
                    intent.putExtra("productType", selectIndex);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RequestActivity1.this, "선택을 완료 해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // go to main
        binding.requestMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestActivity1.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });
    }
}