package com.example.generalHospitalTemi.medical.request;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest2Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest3TemiBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.call.DoctorCallActivity;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnBeWithMeStatusChangedListener;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.listeners.OnLocationsUpdatedListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

import java.util.List;

public class RequestActivity3 extends AppCompatActivity  implements

        OnGoToLocationStatusChangedListener,
        OnCurrentPositionChangedListener
        {
    private ActivityMedicalRequest3TemiBinding binding;
    int productType = 0;
    int placeType = 0;
    int count = 0;

    Position currentPosition;

    ImageView imageView;
    RoboTemiListeners roboTemiListeners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest3TemiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        roboTemiListeners = new RoboTemiListeners();
        roboTemiListeners.getRobot().toggleNavigationBillboard(false);
        roboTemiListeners.getRobot().addOnGoToLocationStatusChangedListener(this);
        roboTemiListeners.getRobot().addOnCurrentPositionChangedListener(this);
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

        //roboTemiListeners = new RoboTemiListeners();

//        roboTemiListeners.goTo(String.valueOf(productType));
        roboTemiListeners.goTo("home base");

    }


    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {

        if(s1.equals("complete")){
            Intent intent = new Intent(RequestActivity3.this, RequestActivity4.class);
            intent.putExtra("placeType",placeType);
            intent.putExtra("productType",productType);
            intent.putExtra("currentPosition",currentPosition);
            startActivity(intent);
        }

    }

    @Override
    public void onCurrentPositionChanged(@NonNull Position position) {
        if(count == 0){
            Log.d("POSITION",position.toString());
            currentPosition = position;
            count ++;
                }
            }
        }