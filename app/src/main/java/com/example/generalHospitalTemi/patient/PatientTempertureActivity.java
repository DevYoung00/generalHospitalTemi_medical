package com.example.generalHospitalTemi.patient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.MainActivity;
import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityPatientTempertureBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PatientTempertureActivity extends AppCompatActivity {

    private ActivityPatientTempertureBinding binding;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientTempertureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Handler handler = new Handler();
        Animation bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);
        databaseReference.child("rfid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object rfidValue = snapshot.getValue();
                binding.tempertureFillRed.setVisibility(View.INVISIBLE);
                binding.tempertureFillGreen.setVisibility(View.INVISIBLE);
                if (rfidValue != null) {
                    double temperture = Double.valueOf(rfidValue.toString());
                    if (temperture != 0){
                        binding.tempertureBartext.setText("체온 측정 중...");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                binding.tempertureBartext.setText(temperture+"도");
                                binding.tempertureBartext.startAnimation(bounceAnimation);
                                if (temperture <= 37.5) {
                                    binding.tempertureFillRed.setVisibility(View.INVISIBLE);
                                    binding.tempertureFillGreen.setVisibility(View.VISIBLE);
                                    binding.printTempertureText.setText("체온이 정상입니다.");
                                } else {
                                    binding.tempertureFillGreen.setVisibility(View.INVISIBLE);
                                    binding.tempertureFillRed.setVisibility(View.VISIBLE);
                                    binding.printTempertureText.setText("체온이 비정상입니다.\n의료진을 호출합니다.");
                                }
                            }
                        }, 1500);
                    } else {
                        binding.tempertureBartext.setText("체온 측정 전");
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "체온 측정 오류", error.toException());
            }
        });

        binding.temperturePrevText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientTempertureActivity.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });

    }
}