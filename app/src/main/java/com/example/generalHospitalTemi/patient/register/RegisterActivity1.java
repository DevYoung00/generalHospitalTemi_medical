package com.example.generalHospitalTemi.patient.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.generalHospitalTemi.databinding.ActivityPatientRegister1Binding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.register.RegisterNoActivity;
import com.example.generalHospitalTemi.patient.register.RegisterYesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity1 extends AppCompatActivity {
    private ActivityPatientRegister1Binding binding;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("cardnum");
        checkCardnum();
        binding.register1PrevText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity1.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        // 액티비티가 재시작될 때 checkCardnum() 메소드를 호출합니다.
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
                    if ("99101208166".equals(cardnum)) {
                        startActivity(new Intent(RegisterActivity1.this, RegisterYesActivity.class));
                    } else {
                        startActivity(new Intent(RegisterActivity1.this, RegisterNoActivity.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 에러 처리
            }
        });
    }
}
