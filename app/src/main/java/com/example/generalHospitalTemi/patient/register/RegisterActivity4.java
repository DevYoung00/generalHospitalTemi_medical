package com.example.generalHospitalTemi.patient.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityPatientRegister4Binding;
import com.example.generalHospitalTemi.medical.request.RequestActivity1;
import com.example.generalHospitalTemi.medical.request.RequestActivity2;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity4 extends AppCompatActivity {

    private ActivityPatientRegister4Binding binding;
    private DatabaseReference databaseReference;
    int selectIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.register4Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 1;
                ImageView requestButton1Clicked = findViewById(R.id.register4_button1_clicked);
                requestButton1Clicked.setVisibility(View.VISIBLE);
                ImageView requestButton1 = findViewById(R.id.register4_button1);
                requestButton1.setVisibility(View.INVISIBLE);

            }
        });
        // 1번 클릭 해제
        binding.register4Button1Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton1 = findViewById(R.id.register4_button1);
                requestButton1.setVisibility(View.VISIBLE);
                ImageView requestButton1Clicked = findViewById(R.id.register4_button1_clicked);
                requestButton1Clicked.setVisibility(View.INVISIBLE);

            }
        });

        // 2번 클릭 시
        binding.register4Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 2;
                ImageView requestButton2Clicked = findViewById(R.id.register4_button2_clicked);
                requestButton2Clicked.setVisibility(View.VISIBLE);
                ImageView requestButton2 = findViewById(R.id.register4_button2);
                requestButton2.setVisibility(View.INVISIBLE);

            }
        });

        // 2번 클릭 해제
        binding.register4Button2Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton2 = findViewById(R.id.register4_button2);
                requestButton2.setVisibility(View.VISIBLE);
                ImageView requestButton2Clicked = findViewById(R.id.register4_button2_clicked);
                requestButton2Clicked.setVisibility(View.INVISIBLE);

            }
        });
        // 3번 클릭 시
        binding.register4Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 3;
                ImageView requestButton3 = findViewById(R.id.register4_button3);
                requestButton3.setVisibility(View.INVISIBLE);
                ImageView requestButton3Clicked = findViewById(R.id.register4_button3_clicked);
                requestButton3Clicked.setVisibility(View.VISIBLE);

            }
        });
        // 3번 클릭 해제
        binding.register4Button3Clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectIndex = 0;
                ImageView requestButton3Clicked = findViewById(R.id.register4_button3_clicked);
                requestButton3Clicked.setVisibility(View.INVISIBLE);
                ImageView requestButton3 = findViewById(R.id.register4_button3);
                requestButton3.setVisibility(View.VISIBLE);

            }
        });

        // go to next
        binding.register4NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectIndex!= 0){
                    Intent intent = new Intent(RegisterActivity4.this, RegisterActivity5.class);
                    intent.putExtra("productType", selectIndex);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity4.this, "선택을 완료 해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.register4MainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity4.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });

    }
}