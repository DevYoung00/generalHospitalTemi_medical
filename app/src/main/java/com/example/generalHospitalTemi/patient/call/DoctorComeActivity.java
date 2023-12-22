package com.example.generalHospitalTemi.patient.call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityPatientDoctorcomeBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;
import com.example.generalHospitalTemi.patient.register.RegisterNoActivity;
import com.example.generalHospitalTemi.patient.register.RegisterYesActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DoctorComeActivity extends AppCompatActivity {

   private ActivityPatientDoctorcomeBinding binding;
   private DatabaseReference databaseReference;
   ImageView imageView;
   private int calling = 0;
   int callingType = 0;


   int mDegree = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientDoctorcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        callingType = intent.getIntExtra("callingType", 0);

        imageView = binding.doctorLoadingPic;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();
        databaseReference.child("callingType").setValue(callingType);
        databaseReference.child("calling").setValue(true);
        calling=1;


        databaseReference = FirebaseDatabase.getInstance().getReference( "cardnum");
        checkCardnum();


    }
    private void checkCardnum() {
        databaseReference.orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DataSnapshot lastNode = snapshot.getChildren().iterator().next();
                    Object cardnum_before = lastNode.getValue();
                    String cardnum = cardnum_before.toString();
                    Log.d("Cardnum Log", "Cardnum: " + cardnum);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if ("15417441133".equals(cardnum)) {
                                databaseReference = FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("calling").setValue(false);
                                startActivity(new Intent(DoctorComeActivity.this, PatientMainActivity.class));
                                finish();
                            }
                        }
                    }, 2000);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 에러 처리
            }
        });
    }
}