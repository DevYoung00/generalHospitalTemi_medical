package com.example.generalHospitalTemi.medical.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityMedicalEmergencyBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.PatientTempertureActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmergencyActivity extends AppCompatActivity {

    private ActivityMedicalEmergencyBinding binding;
    private DatabaseReference databaseReference;
    private int codeblue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicalEmergencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        binding.emergencySendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(codeblue == 0){
                    binding.emergencySendPic.setImageResource(R.drawable.emergency_off_pic);
                    binding.emergencyText.setText("다른 Temi에게 응급 상황 종료 알림을 전송하려면 아래 버튼을 누르세요");
                    databaseReference.child("codeblue").setValue(true);

                }
                else if(codeblue == 1){
                    binding.emergencySendPic.setImageResource(R.drawable.emergency_btn);
                    binding.emergencyText.setText("다른 Temi에게 응급 알림을 전송하려면 아래 버튼을 누르세요");
                    databaseReference.child("codeblue").setValue(false);
                }
//                Intent intent = new Intent(PatientMainActivity.this, PatientTempertureActivity.class);
//                startActivity(intent);
            }
        });


    }


    private void writeData(String Data) {
        databaseReference.setValue(Data)
                .addOnSuccessListener(aVoid -> Log.d("tag", "data written to the database"))
                .addOnFailureListener(e -> Log.w("tag", "Error writing data to the database", e));
    }


}