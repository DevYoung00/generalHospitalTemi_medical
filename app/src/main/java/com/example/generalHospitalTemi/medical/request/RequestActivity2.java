package com.example.generalHospitalTemi.medical.request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest1Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest2Binding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;

public class RequestActivity2 extends AppCompatActivity {
    private ActivityMedicalRequest2Binding binding;

    int selectIndex = 0;
    int productType= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Intent에서 데이터를 받아옴
        Intent intent = getIntent();
        if (intent != null) {
            productType = intent.getIntExtra("productType",0);
        }

        // 1번 클릭 시
        binding.request2Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 1;
                ImageView requestButton1Clicked = findViewById(R.id.request2_button1_clicked);
                requestButton1Clicked.setVisibility(View.VISIBLE);
                ImageView requestButton1 = findViewById(R.id.request2_button1);
                requestButton1.setVisibility(View.INVISIBLE);

            }
        });
        // 1번 클릭 해제
        binding.request2Button1Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton1 = findViewById(R.id.request2_button1);
                requestButton1.setVisibility(View.VISIBLE);
                ImageView requestButton1Clicked = findViewById(R.id.request2_button1_clicked);
                requestButton1Clicked.setVisibility(View.INVISIBLE);

            }
        });

        // 2번 클릭 시
        binding.request2Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 2;
                ImageView requestButton2Clicked = findViewById(R.id.request2_button2_clicked);
                requestButton2Clicked.setVisibility(View.VISIBLE);
                ImageView requestButton2 = findViewById(R.id.request2_button2);
                requestButton2.setVisibility(View.INVISIBLE);

            }
        });

        // 2번 클릭 해제
        binding.request2Button2Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton2 = findViewById(R.id.request2_button2);
                requestButton2.setVisibility(View.VISIBLE);
                ImageView requestButton2Clicked = findViewById(R.id.request2_button2_clicked);
                requestButton2Clicked.setVisibility(View.INVISIBLE);

            }
        });
        // 3번 클릭 시
        binding.request2Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 3;
                ImageView requestButton3 = findViewById(R.id.request2_button3);
                requestButton3.setVisibility(View.INVISIBLE);
                ImageView requestButton3Clicked = findViewById(R.id.request2_button3_clicked);
                requestButton3Clicked.setVisibility(View.VISIBLE);

            }
        });
        // 3번 클릭 해제
        binding.request2Button3Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton3Clicked = findViewById(R.id.request2_button3_clicked);
                requestButton3Clicked.setVisibility(View.INVISIBLE);
                ImageView requestButton3 = findViewById(R.id.request2_button3);
                requestButton3.setVisibility(View.VISIBLE);

            }
        });

        // go to next
        binding.request2NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectIndex!= 0){
                    Intent intent = new Intent(RequestActivity2.this, RequestActivity3.class);
                    intent.putExtra("productType", productType);
                    intent.putExtra("placeType", selectIndex);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RequestActivity2.this, "선택을 완료 해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // go to main
        binding.request2Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestActivity2.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });

    }
}