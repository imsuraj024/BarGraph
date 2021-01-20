package com.suraj.orahiassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String otp = getRandomNumberString();
        Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();

        send = findViewById(R.id.btn_main_get_opt);

        send.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VerifyActivity.class);
            intent.putExtra("otp",otp);
            startActivity(intent);
        });
    }

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