package com.example.generalHospitalTemi.medical;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityMedicalDoctorcomeBinding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.call.DoctorCallActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class DoctorCalledActivity extends AppCompatActivity {

   private ActivityMedicalDoctorcomeBinding binding;

    private DatabaseReference databaseReference;
   ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalDoctorcomeBinding.inflate(getLayoutInflater());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        setContentView(binding.getRoot());

        imageView = binding.doctorLoadingPic;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();

        databaseReference.child("calling").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // callingType 변수를 읽어옵니다.
                Integer callingType = snapshot.getValue(Integer.class);

                // callingType에 따라 doctor_call_reason 텍스트를 변경합니다.
                if (callingType != null) {
                    switch (callingType) {
                        case 1:
                            binding.doctorCallReason.setText("사유: 고열 및 외상");
                            break;
                        case 2:
                            binding.doctorCallReason.setText("사유 : 급성 의식장애, 호흡부전 등");
                            break;
                        case 3:
                            binding.doctorCallReason.setText("사유 : 응급 환자 발생");
                            break;
                        case 4:
                            binding.doctorCallReason.setText("사유 : 기타 서비스");
                            break;
                        default:
                            binding.doctorCallReason.setText("알 수 없는 상황");
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 에러 처리
            }
        });
        binding.mainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorCalledActivity.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });

        // 멈추기
       // rotateAnimator.cancel();
    }
}