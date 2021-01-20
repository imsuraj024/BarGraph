package com.suraj.orahiassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VerifyActivity extends AppCompatActivity {

    private Button verify;
    private EditText one, two, three, four, five, six;
    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        otp = getIntent().getStringExtra("otp");
        Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();

        verify = findViewById(R.id.btn_verify_verify_opt);
        one = findViewById(R.id.edit_verify_code_one);
        two = findViewById(R.id.edit_verify_code_two);
        three = findViewById(R.id.edit_verify_code_three);
        four = findViewById(R.id.edit_verify_code_four);
        five = findViewById(R.id.edit_verify_code_five);
        six = findViewById(R.id.edit_verify_code_six);

        setOtp();

        verify.setOnClickListener(v -> {
            startActivity(new Intent(VerifyActivity.this, GraphActivity.class));
        });
    }

    @SuppressLint("SetTextI18n")
    private void setOtp() {
        one.setText(""+otp.charAt(0));
        two.setText(""+otp.charAt(1));
        three.setText(""+otp.charAt(2));
        four.setText(""+otp.charAt(3));
        five.setText(""+otp.charAt(4));
        six.setText(""+otp.charAt(5));

    }
}