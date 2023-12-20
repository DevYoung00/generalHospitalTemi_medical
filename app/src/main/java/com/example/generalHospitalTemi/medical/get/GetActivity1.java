package com.example.generalHospitalTemi.medical.get;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.databinding.ActivityMedicalGet1Binding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.PatientTempertureActivity;
import com.example.generalHospitalTemi.patient.register.RegisterActivity1;
import com.example.generalHospitalTemi.patient.register.RegisterNoActivity;
import com.example.generalHospitalTemi.patient.register.RegisterYesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetActivity1 extends AppCompatActivity {

    private ActivityMedicalGet1Binding binding;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    public String cardnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalGet1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference("cardnum");
        checkCardnum();


        binding.get1PrevText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetActivity1.this, MedicalMainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void checkCardnum() {
        databaseReference.orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DataSnapshot lastNode = snapshot.getChildren().iterator().next();
                    Object cardnum_before = lastNode.getValue();
                    cardnum = cardnum_before.toString();
                    Log.d("Cardnum Log", "Cardnum: " + cardnum);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(GetActivity1.this, GetActivity2.class);
                            intent.putExtra("cardNum", cardnum);
                            startActivity(intent);
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
