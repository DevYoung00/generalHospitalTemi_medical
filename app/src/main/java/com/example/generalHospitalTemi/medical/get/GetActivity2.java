package com.example.generalHospitalTemi.medical.get;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.generalHospitalTemi.databinding.ActivityMedicalGet2Binding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;
import com.example.generalHospitalTemi.patient.register.RegisterYesActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class GetActivity2 extends AppCompatActivity {

   private ActivityMedicalGet2Binding binding;

    private DatabaseReference databaseReference;

    private String cardNum = "" ;
   ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalGet2Binding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        cardNum = intent.getStringExtra("cardNum");

        imageView = binding.doctorLoadingPic;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("99101208166".equals(cardNum)) {
                    startActivity(new Intent(GetActivity2.this, GetYesActivity2.class));
                    finish();
                } else {
                    startActivity(new Intent(GetActivity2.this, GetNoActivity2.class));
                    finish();
                }
            }
        }, 2000);


        binding.mainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetActivity2.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });

        // 멈추기
       // rotateAnimator.cancel();
    }
}