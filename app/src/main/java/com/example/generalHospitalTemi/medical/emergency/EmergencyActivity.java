package com.example.generalHospitalTemi.medical.emergency;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalEmergencyBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.PatientTempertureActivity;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;

public class EmergencyActivity extends AppCompatActivity implements
        Robot.AsrListener,
        Robot.TtsListener,
        Robot.ConversationViewAttachesListener,
        Robot.WakeupWordListener{
    private ActivityMedicalEmergencyBinding binding;
    RoboTemiListeners roboTemiListeners;
    private DatabaseReference databaseReference;

    private MediaPlayer mediaPlayer;
    private int codeblue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalEmergencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mediaPlayer = MediaPlayer.create(this, R.raw.code_bule_sound); // raw 폴더에 소리 파일을 넣어주세요
        databaseReference = FirebaseDatabase.getInstance().getReference();
        roboTemiListeners = new RoboTemiListeners();

        roboTemiListeners.getRobot().addAsrListener(this);//robot 객체는 현재 private 처리되어있기 때문에 참조하기 위해서 get을 사용하였다.
        //  ^---- 여기가 Robot 객체 ----^                    << 여긴 robot.addAsrListener(this);와 동일하다.
        roboTemiListeners.getRobot().addTtsListener(this);
        roboTemiListeners.getRobot().addWakeupWordListener(this);
        roboTemiListeners.getRobot().addConversationViewAttachesListenerListener(this);
        roboTemiListeners.getRobot().toggleNavigationBillboard(false);
        

        codeblue=0;
        binding.emergencySendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(EmergencyActivity.this, "클릭 , " + codeblue, Toast.LENGTH_SHORT).show();
                if(codeblue == 0){
                    binding.emergencySendPic.setImageResource(R.drawable.emergency_off_pic);
                    binding.emergencyText.setText("다른 Temi에게 응급 상황 종료 알림을 전송하려면 아래 버튼을 누르세요");
                    databaseReference.child("codeblue").setValue(true);
                    codeblue=1;
                   mediaPlayer.start();
                    roboTemiListeners.goTo("codeblue");


                }
                else if(codeblue == 1){
                    binding.emergencySendPic.setImageResource(R.drawable.emergency_btn);
                    binding.emergencyText.setText("다른 Temi에게 응급 알림을 전송하려면 아래 버튼을 누르세요");
                    databaseReference.child("codeblue").setValue(false);
                    codeblue=0;
                   mediaPlayer.release();
                    roboTemiListeners.goTo("home base");
                }
//                Intent intent = new Intent(PatientMainActivity.this, PatientTempertureActivity.class);
//                startActivity(intent);
            }
        });

        // main 버튼 클릭
        binding.register1GoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(codeblue == 1){
                    Toast.makeText(EmergencyActivity.this, "응급 상황이 종료 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EmergencyActivity.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });


    }


    private void writeData(String Data) {
        databaseReference.setValue(Data)
                .addOnSuccessListener(aVoid -> Log.d("tag", "data written to the database"))
                .addOnFailureListener(e -> Log.w("tag", "Error writing data to the database", e));
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