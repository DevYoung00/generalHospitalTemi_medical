package com.example.generalHospitalTemi.patient.call;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.generalHospitalTemi.R;
import com.example.generalHospitalTemi.TestActivity;
import com.example.generalHospitalTemi.databinding.ActivityDoctorCallBinding;
import com.example.generalHospitalTemi.databinding.ActivityMedicalMainBinding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;
import com.example.generalHospitalTemi.patient.PatientTempertureActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorCallActivity extends AppCompatActivity {

    private ActivityDoctorCallBinding binding;
    private List<String> list = new ArrayList<>();
    private Spinner spinner;
    private CustomSpinnerAdapter adapter;
    private String selectedItem;
    private int selectedPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Spinner 객체
        spinner = binding.doctorCallSpinner;
        list = Arrays.asList(getResources().getStringArray(R.array.spinnerArray));
        adapter = new CustomSpinnerAdapter(this,list);
        spinner.setAdapter(adapter);

        // 스피너 클릭 리스너
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 어댑터에서 정의한 메서드를 통해 스피너에서 선택한 아이템의 이름을 받아온다
                selectedItem = (String) adapter.getItem(position);  // 수정된 부분
                selectedPosition = position;
                // Toast.makeText(DoctorCallActivity.this, "선택한 아이템 : " + selectedItem, Toast.LENGTH_SHORT).show();

                String otherItem = (String) spinner.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        binding.doctorCallNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedPosition!= 0){
                Intent intent = new Intent(DoctorCallActivity.this, DoctorComeActivity.class);
                startActivity(intent);
                }
                else {
                     Toast.makeText(DoctorCallActivity.this, "선택을 완료 해 주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}