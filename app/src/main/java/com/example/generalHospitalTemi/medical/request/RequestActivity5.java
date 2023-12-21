package com.example.generalHospitalTemi.medical.request;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest4Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest5Binding;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.navigation.listener.OnCurrentPositionChangedListener;
import com.robotemi.sdk.navigation.model.Position;

public class RequestActivity5 extends AppCompatActivity  implements
        OnGoToLocationStatusChangedListener,
        OnCurrentPositionChangedListener {
    private ActivityMedicalRequest5Binding binding;
    private DatabaseReference databaseReference;
    int productType = 0;
    int placeType = 0;
    Position currentPosition;
    RoboTemiListeners roboTemiListeners;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        roboTemiListeners = new RoboTemiListeners();

        roboTemiListeners.getRobot().toggleNavigationBillboard(false);
        roboTemiListeners.getRobot().addOnGoToLocationStatusChangedListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        // Intent에서 데이터를 받아옴
        Intent intent = getIntent();
        if (intent != null) {
            productType = intent.getIntExtra("productType",0);
            placeType = intent.getIntExtra("placeType",0);
            currentPosition = intent.getParcelableExtra("currentPosition");
        }
        binding.request5Text.setText("현재 요청하신 " +productType+"번 물품이 해당 위치로 이동중입니다.");
       imageView = binding.loadingPicReq5;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();

        if(placeType==1){
            roboTemiListeners.getRobot().goToPosition(currentPosition);
        }
        else if(placeType==2){
            roboTemiListeners.getRobot().goTo("room1");
        }
        else if(placeType==3){
            roboTemiListeners.getRobot().goTo("room2");
        }
    }



    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {
        if (s1.equals("complete")) {

            Intent intent = new Intent(RequestActivity5.this, RequestActivity6.class);
            intent.putExtra("productType", productType);
            startActivity(intent);
        }
    }

    @Override
    public void onCurrentPositionChanged(@NonNull Position position) {
        if(position.equals(currentPosition)){
            Intent intent = new Intent(RequestActivity5.this, RequestActivity6.class);
            intent.putExtra("productType", productType);
            startActivity(intent);
        }
    }
}