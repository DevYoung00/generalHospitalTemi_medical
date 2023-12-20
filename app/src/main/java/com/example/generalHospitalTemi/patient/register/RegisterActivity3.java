package com.example.generalHospitalTemi.patient.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityPatientRegister3Binding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity3 extends AppCompatActivity {

    private ActivityPatientRegister3Binding binding;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Handler handler = new Handler();
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);
        databaseReference.child("Temperature").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            //체온 측정
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot lastNode = snapshot.getChildren().iterator().next();
                Object temperatureValue = lastNode.getValue();
                binding.tempertureFillRed.setVisibility(View.INVISIBLE);
                binding.tempertureFillGreen.setVisibility(View.INVISIBLE);
                if (temperatureValue != null) {
                    double temperture = Double.valueOf(temperatureValue.toString());
                    if (temperture != 0){
                        binding.tempertureBartext.setText("체온 측정 중...");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.tempertureBartext.setText(temperture+"도");
                                binding.tempertureBartext.startAnimation(bounceAnimation);
                                if (temperture <= 37.5) {
                                    binding.tempertureFillRed.setVisibility(View.INVISIBLE);
                                    binding.tempertureFillGreen.setVisibility(View.VISIBLE);
                                    binding.printTempertureText.setText("체온이 정상입니다.\n 잠시 후, 진료과 선택을 해주세요.");
                                } else {
                                    binding.tempertureFillGreen.setVisibility(View.INVISIBLE);
                                    binding.tempertureFillRed.setVisibility(View.VISIBLE);
                                    binding.printTempertureText.setText("체온이 비정상입니다.\n접수 후, 가까운 의료진을 찾아가세요.");
                                }
                            }
                        }, 2000);
                        // 3초 후에 PatientMainActivity로 이동
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(RegisterActivity3.this, RegisterActivity4.class);
                                startActivity(intent);
                                finish(); // 현재 액티비티 종료
                            }
                        }, 2000); // 3000ms = 3초

                    } else {
                        binding.tempertureBartext.setText("체온 측정 전");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "체온 측정 오류", error.toException());
            }
        });

        binding.register3MainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity3.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });

    }
}