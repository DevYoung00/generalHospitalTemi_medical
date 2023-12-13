package com.example.generalHospitalTemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.generalHospitalTemi.databinding.ActivityTestBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

public class TestActivity extends AppCompatActivity implements
        Robot.AsrListener,
        Robot.TtsListener,
        Robot.ConversationViewAttachesListener,
        Robot.WakeupWordListener{

    private ActivityTestBinding binding;

    RoboTemiListeners roboTemiListeners;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        roboTemiListeners = new RoboTemiListeners();

            roboTemiListeners.getRobot().addAsrListener(this);//robot 객체는 현재 private 처리되어있기 때문에 참조하기 위해서 get을 사용하였다.
            //  ^---- 여기가 Robot 객체 ----^                    << 여긴 robot.addAsrListener(this);와 동일하다.
            roboTemiListeners.getRobot().addTtsListener(this);
            roboTemiListeners.getRobot().addWakeupWordListener(this);
            roboTemiListeners.getRobot().addConversationViewAttachesListenerListener(this);

        binding.goToHomebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                roboTemiListeners.goTo("홈베이스");
            }
        });

        binding.goTo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roboTemiListeners.goTo("1");
            }
        });

        binding.goTo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roboTemiListeners.goTo("2");
            }
        });
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
}