package com.example.baiktra1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baiktra1.R;

public class MainActivity extends AppCompatActivity {

    Button btnDonVi, btnNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDonVi = findViewById(R.id.btnDonVi);
        btnNhanVien = findViewById(R.id.btnNhanVien);

        btnDonVi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DonViActivity.class);
                startActivity(intent);
            }
        });

        btnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NhanVienActivity.class);
                startActivity(intent);
            }
        });
    }
}
