package com.example.generalHospitalTemi.medical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityCallReqBinding;
import com.example.generalHospitalTemi.databinding.ActivityPatientDoctorcomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CallReqActivity extends AppCompatActivity {

    private ActivityCallReqBinding binding;
    ImageView imageView;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallReqBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();

        imageView = binding.callLoadingPic;
        // 회전 애니메이션 생성 (시계 방향으로 360도 회전)
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(2000); // 애니메이션 지속 시간 (2초)
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 무한 반복
        rotateAnimator.setInterpolator(new LinearInterpolator()); // 선형 보간

        // 애니메이션 시작
        rotateAnimator.start();
        databaseReference.child("call_reason").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            //체온 측정
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot lastNode = snapshot.getChildren().iterator().next();
                Object CallReason = lastNode.getValue();
                if (CallReason != null) {
                    String reason = CallReason.toString();
                    binding.callReason.setText("사유: " + reason);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("tag", "체온 측정 오류", error.toException());
            }
        });

    }
}