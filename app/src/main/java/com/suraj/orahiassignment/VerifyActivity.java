package com.suraj.orahiassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class VerifyActivity extends AppCompatActivity {

    private Button verify;
    private EditText one, two, three, four, five, six;
    private String otp;
    private TextView resend;

    @SuppressLint("DefaultLocale")
    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    @SuppressLint("SetTextI18n")
    private void setOtp() {
        one.setText("" + otp.charAt(0));
        two.setText("" + otp.charAt(1));
        three.setText("" + otp.charAt(2));
        four.setText("" + otp.charAt(3));
        five.setText("" + otp.charAt(4));
        six.setText("" + otp.charAt(5));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        otp = getIntent().getStringExtra("otp");

        verify = findViewById(R.id.btn_verify_verify_opt);
        one = findViewById(R.id.edit_verify_code_one);
        two = findViewById(R.id.edit_verify_code_two);
        three = findViewById(R.id.edit_verify_code_three);
        four = findViewById(R.id.edit_verify_code_four);
        five = findViewById(R.id.edit_verify_code_five);
        six = findViewById(R.id.edit_verify_code_six);
        resend = findViewById(R.id.text_verify_resend);

        setOtp();

        resend.setOnClickListener(v -> {
            otp = getRandomNumberString();
            setOtp();

        });

        verify.setOnClickListener(v -> {

            String code_one = one.getText().toString();
            String code_two = two.getText().toString();
            String code_three = three.getText().toString();
            String code_four = four.getText().toString();
            String code_five = five.getText().toString();
            String code_six = six.getText().toString();

            if (code_one.isEmpty() || code_two.isEmpty() || code_three.isEmpty() || code_four.isEmpty() || code_five.isEmpty() || code_six.isEmpty()){
                Toast.makeText(this, "Invalid Code", Toast.LENGTH_SHORT).show();
            } else {
                String code = code_one + code_two + code_three + code_four + code_five + code_six;
                if (code.length() == 6 && code.equals(otp)){
                    startActivity(new Intent(VerifyActivity.this, GraphActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(this, "Code does not matches", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}