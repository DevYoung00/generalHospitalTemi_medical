package com.example.generalHospitalTemi.patient.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.example.generalHospitalTemi.databinding.ActivityPatientRegister2Binding;
import com.example.generalHospitalTemi.patient.PatientMainActivity;

    public class RegisterActivity2 extends AppCompatActivity {
        private ActivityPatientRegister2Binding binding;
        @SuppressLint("ClickableViewAccessibility")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityPatientRegister2Binding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.rootLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (binding.register2PrintIdText.isFocused()) {
                        binding.register2PrintIdText.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                    return false;
                }
            });



            binding.register2PrintIdText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        ((EditText)v).setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
            });

            binding.register2PrintIdText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.register2PrintIdText.setText("");
                    ((EditText)v).setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            });

            binding.register2PrintIdText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (isValidResidentNumber(s.toString())) {
                        binding.register2PrintIdText.setTextColor(Color.BLACK);
                        binding.register2PrintIdText.setTextSize(30);
                        String front = s.toString().substring(0, 8);
                        String back = s.toString().substring(8).replaceAll("[0-9]", "*");
                        binding.register2PrintIdText.removeTextChangedListener(this);
                        binding.register2PrintIdText.setText(front + back);
                        binding.register2PrintIdText.addTextChangedListener(this);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(RegisterActivity2.this, RegisterActivity3.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }
                    else if(s.toString().length() == 13){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(RegisterActivity2.this, RegisterNo2Activity.class);
                                startActivity(intent);
                                finish(); // 현재 액티비티 종료
                            }
                        }, 1000); // 1000ms = 1초
                    }
                }
            });

            binding.register2MainText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RegisterActivity2.this, PatientMainActivity.class);
                    startActivity(intent);
                }
            });


        }

        private boolean isValidResidentNumber(String number) {
            String pattern = "0001014321234";
            return number.equals(pattern);
        }
    }


