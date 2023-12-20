package com.example.generalHospitalTemi.patient.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.databinding.ActivityPatientRegister5Binding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity5 extends AppCompatActivity {

    private ActivityPatientRegister5Binding binding;
    private DatabaseReference databaseReference;
    int productType = 0;
    private static int queueNumber1 = 0;
    private static int queueNumber2 = 0;
    private static int queueNumber3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientRegister5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        productType = intent.getIntExtra("productType", 0);

        switch (productType) {
            case 1:
                queueNumber1++;
                binding.register5Bartext.setText(String.format("%03d", queueNumber1));
                binding.register3PrintText2.setText("담당의: 이석훈");
                break;
            case 2:
                queueNumber2++;
                binding.register5Bartext.setText(String.format("%03d", queueNumber2));
                binding.register3PrintText2.setText("담당의: 장기하");
                break;
            case 3:
                queueNumber3++;
                binding.register5Bartext.setText(String.format("%03d", queueNumber3));
                binding.register3PrintText2.setText("담당의: 이한웅");
                break;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
        String currentTime = simpleDateFormat.format(new Date());
        binding.register3PrintText1.setText("접수일: " + currentTime);

        binding.register5MainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity5.this, PatientMainActivity.class);
                startActivity(intent);
            }
        });
    }
}