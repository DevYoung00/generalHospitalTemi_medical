package com.example.generalHospitalTemi;

import static com.google.firebase.FirebaseApp.initializeApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.robotemi.sdk.Robot;
import com.example.generalHospitalTemi.databinding.ActivityMainBinding;
import com.example.generalHospitalTemi.medical.DoctorCalledActivity;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.medical.emergency.CodeBlueActivity;
import com.example.generalHospitalTemi.medical.emergency.EmergencyActivity;
import com.example.generalHospitalTemi.medical.request.RequestActivity2;
import com.example.generalHospitalTemi.medical.request.RequestActivity3;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robotemi.sdk.TtsRequest;


public class MainActivity extends AppCompatActivity implements
        Robot.AsrListener,
        Robot.TtsListener,
        Robot.ConversationViewAttachesListener,
        Robot.WakeupWordListener{

    private ActivityMainBinding binding;
    private DatabaseReference databaseReference;
    RoboTemiListeners roboTemiListeners;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        type = "";
        roboTemiListeners = new RoboTemiListeners();

        roboTemiListeners.getRobot().addAsrListener(this);//robot 객체는 현재 private 처리되어있기 때문에 참조하기 위해서 get을 사용하였다.
        //  ^---- 여기가 Robot 객체 ----^                    << 여긴 robot.addAsrListener(this);와 동일하다.
        roboTemiListeners.getRobot().addTtsListener(this);
        roboTemiListeners.getRobot().addWakeupWordListener(this);
        roboTemiListeners.getRobot().addConversationViewAttachesListenerListener(this);
        roboTemiListeners.getRobot().toggleNavigationBillboard(false);


        // go to medical main
        binding.medicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "medical";
                Intent intent = new Intent(MainActivity.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });

        // go to patient main
        binding.patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "patient";
                Intent intent = new Intent(MainActivity.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });

        // go to test
//        binding.goToTestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, TestActivity.class);
//                startActivity(intent);
//            }
//        });

        databaseReference.child("codeblue").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue().toString() == "true" && type.equals("patient")) {
                    Intent intent = new Intent(MainActivity.this, CodeBlueActivity.class);
                    startActivity(intent);
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read RFID value.", error.toException());
            }
        });

        databaseReference.child("calling").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().toString() == "true" && type.equals("medical")){
                    Intent intent = new Intent(MainActivity.this, DoctorCalledActivity.class);
                    startActivity(intent);
                }
                else{

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read RFID value.", error.toException());
            }
        });

        databaseReference.child("remote").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DataSnapshot lastNode = snapshot.getChildren().iterator().next();
                    Object remote_before = lastNode.getValue();
                    String remote = remote_before.toString();
                    Log.d("remote Log", "Remote: " + remote);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 1번물품 현재 테미 위치로 가져옴
                            if (remote.equals("1") && type.equals("medical")) {
                                Intent intent = new Intent(MainActivity.this, RequestActivity3.class);
                                intent.putExtra("productType", 1);
                                intent.putExtra("placeType", 1);
                                startActivity(intent);
                            }
                            // 1번물품 진료실로 가져옴
                            else if (remote.equals("2") && type.equals("medical")) {
                                Intent intent = new Intent(MainActivity.this, RequestActivity3.class);
                                intent.putExtra("productType", 1);
                                intent.putExtra("placeType", 2);
                                startActivity(intent);
                            }
                            // 1번물품 대기실로 가져옴
                            else if (remote.equals("3") && type.equals("medical")) {
                                Intent intent = new Intent(MainActivity.this, RequestActivity3.class);
                                intent.putExtra("productType", 1);
                                intent.putExtra("placeType", 2);
                                startActivity(intent);
                            }
                            // 홈베이스 이동
                            else if (remote.equals("4") && type.equals("medical")) {
                                roboTemiListeners.goTo("home base");
                            }
                            else if(remote.equals("5") && type.equals("medical")){
                                skidJoy((float) -1.0,1);
                            }
                        }
                    }, 500);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "Failed to read RFID value.", error.toException());
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

    public void skidJoy(float vel , int sec) {// 이동 예시
        long t = System.currentTimeMillis();
        long end = t + 1000*sec;
        while (System.currentTimeMillis() < end) {
            roboTemiListeners.skidJoy(vel, 0F);
        }
    }
}

