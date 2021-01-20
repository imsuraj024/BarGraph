package com.suraj.orahiassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button send;
    private EditText mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.btn_main_get_opt);
        mobile = findViewById(R.id.edit_main_mobile);

        send.setOnClickListener(v -> {

            String mobile_number = mobile.getText().toString();

            if (mobile_number.isEmpty()){
                Toast.makeText(this, "Field cannot be blank", Toast.LENGTH_SHORT).show();
            } else {
                String otp = getRandomNumberString();
                Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, VerifyActivity.class);
                intent.putExtra("otp",otp);
                startActivity(intent);
            }

        });
    }

    @SuppressLint("DefaultLocale")
    public static String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}