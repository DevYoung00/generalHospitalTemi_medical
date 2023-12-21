package com.example.generalHospitalTemi.medical.request;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest3TemiBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalRequest4Binding;
import com.example.generalHospitalTemi.temi.RoboTemiListeners;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener;
import com.robotemi.sdk.navigation.model.Position;

public class RequestActivity4 extends AppCompatActivity  implements
        OnGoToLocationStatusChangedListener {
    private ActivityMedicalRequest4Binding binding;
    private DatabaseReference databaseReference;
    int productType = 0;
    int placeType = 0;
    Position currentPosition;
    RoboTemiListeners roboTemiListeners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalRequest4Binding.inflate(getLayoutInflater());
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

        // 도착 firebase에 업로드
        databaseReference.child("Arrive").setValue(true);
        databaseReference.child("Closed").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            //체온 측정
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot lastNode = snapshot.getChildren().iterator().next();
                Object closedValue = lastNode.getValue();
                if (closedValue != null) {
                    String status = closedValue.toString();
                    if (status.equals("End")) {
                        Intent intent = new Intent(RequestActivity4.this, RequestActivity5.class);
                        intent.putExtra("placeType",placeType);
                        intent.putExtra("productType",productType);
                        intent.putExtra("currentPosition",currentPosition);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "잠금 오류", error.toException());
            }
        });


    }

    @Override
    public void onGoToLocationStatusChanged(@NonNull String s, @NonNull String s1, int i, @NonNull String s2) {

    }
}