package com.example.generalHospitalTemi;

import static com.google.firebase.FirebaseApp.initializeApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.generalHospitalTemi.databinding.ActivityMainBinding;
import com.example.generalHospitalTemi.medical.MedicalMainActivity;
import com.example.generalHospitalTemi.medical.emergency.CodeBlueActivity;
import com.example.generalHospitalTemi.medical.emergency.EmergencyActivity;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseReference databaseReference;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        type = "";
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

                if(snapshot.getValue().toString() == "true" && type.equals("patient")){
                    Intent intent = new Intent(MainActivity.this, CodeBlueActivity.class);
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


    }
}