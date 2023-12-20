package com.example.generalHospitalTemi.medical.request;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest2Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest3TemiBinding;

public class RequestActivity3 extends AppCompatActivity {
    private ActivityMedicalRequest3TemiBinding binding;
    int productType = 0;
    int placeType = 0;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest3TemiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Intent에서 데이터를 받아옴
        Intent intent = getIntent();
        if (intent != null) {
            productType = intent.getIntExtra("productType",0);
            placeType = intent.getIntExtra("placeType",0);
        }
        String placeText = "현재 물품 수령을 위해 " + productType +"번 서랍으로 이동합니다.";
        binding.request3PlaceType.setText(placeText);


        imageView = binding.request3LoadingPic;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();

    }
}