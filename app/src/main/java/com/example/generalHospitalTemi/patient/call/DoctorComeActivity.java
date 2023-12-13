package com.example.generalHospitalTemi.patient.call;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityDoctorComeBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;

public class DoctorComeActivity extends AppCompatActivity {

   private ActivityDoctorComeBinding binding;
   ImageView imageView;
   int mDegree = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorComeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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