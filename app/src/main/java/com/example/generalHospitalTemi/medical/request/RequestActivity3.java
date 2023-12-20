package com.example.generalHospitalTemi.medical.request;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest2Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest3TemiBinding;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnBeWithMeStatusChangedListener;
import com.robotemi.sdk.listeners.OnLocationsUpdatedListener;

import java.util.List;

public class RequestActivity3 extends AppCompatActivity implements
        Robot.AsrListener,
        Robot.TtsListener,
        Robot.ConversationViewAttachesListener,
        Robot.WakeupWordListener,
        OnLocationsUpdatedListener {
    private ActivityMedicalRequest3TemiBinding binding;
    int productType = 0;
    String placeType = "";
    ImageView imageView;
    RoboTemiListeners roboTemiListeners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest3TemiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        roboTemiListeners = new RoboTemiListeners();

        roboTemiListeners.getRobot().addAsrListener(this);//robot 객체는 현재 private 처리되어있기 때문에 참조하기 위해서 get을 사용하였다.
        //  ^---- 여기가 Robot 객체 ----^                    << 여긴 robot.addAsrListener(this);와 동일하다.
        roboTemiListeners.getRobot().addTtsListener(this);
        roboTemiListeners.getRobot().addWakeupWordListener(this);
        roboTemiListeners.getRobot().addConversationViewAttachesListenerListener(this);
        roboTemiListeners.getRobot().toggleNavigationBillboard(false);
        // Intent에서 데이터를 받아옴
        Intent intent = getIntent();
        if (intent != null) {
            productType = intent.getIntExtra("productType",0);
            placeType = String.valueOf(intent.getIntExtra("placeType",0));
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

        roboTemiListeners = new RoboTemiListeners();

        roboTemiListeners.getRobot().addAsrListener(this);//robot 객체는 현재 private 처리되어있기 때문에 참조하기 위해서 get을 사용하였다.
        //  ^---- 여기가 Robot 객체 ----^                    << 여긴 robot.addAsrListener(this);와 동일하다.
        roboTemiListeners.getRobot().addTtsListener(this);
        roboTemiListeners.getRobot().addWakeupWordListener(this);
        roboTemiListeners.getRobot().addConversationViewAttachesListenerListener(this);

        roboTemiListeners.goTo(placeType);
       // roboTemiListeners.onGoToLocationStatusChanged(placeType,);
    }

    @Override
    public void onAsrResult(@NonNull String s) {

    }

    @Override
    public void onConversationAttaches(boolean b) {

    }

    @Override
    public void onTtsStatusChanged(@NonNull TtsRequest ttsRequest) {

    }

    @Override
    public void onWakeupWord(@NonNull String s, int i) {

    }

    @Override
    public void onLocationsUpdated(@NonNull List<String> list) {

    }
}