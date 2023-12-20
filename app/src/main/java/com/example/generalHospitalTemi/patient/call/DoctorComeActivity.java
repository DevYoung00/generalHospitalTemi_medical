package com.example.generalHospitalTemi.patient.call;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityPatientDoctorcomeBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorComeActivity extends AppCompatActivity {

   private ActivityPatientDoctorcomeBinding binding;
   ImageView imageView;
   int mDegree = 0;

    private DatabaseReference databaseReference;

   String reason = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientDoctorcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        if (intent != null) {
            int index = intent.getIntExtra("reason",0);
            if(index==1){
                reason = "고열 및 외상";
            }
            else if(index==2){
                reason = "급성 의식장애, 호흡부전 등";
            }
            else if(index==3){
                reason = "응급 환자 발생";
            }
            else if(index==4){
                reason = "기타 서비스";
            }
        }

       if(!reason.equals("")){
           databaseReference.child("call_reason").setValue(reason);
           databaseReference.child("call").setValue(true);
       }
        imageView = binding.doctorLoadingPic;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();

        // 멈추기
       // rotateAnimator.cancel();
    }
}