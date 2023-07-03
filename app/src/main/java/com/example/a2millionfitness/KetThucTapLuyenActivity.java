package com.example.a2millionfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KetThucTapLuyenActivity extends AppCompatActivity {

    Button ketThucBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_thuc_tap_luyen);

        getSupportActionBar().hide();
        ketThucBtn = (Button)findViewById(R.id.ketThucBtn);
        ketThucBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(KetThucTapLuyenActivity.this , MainActivity.class);
                startActivity(i);
            }
        });
    }
}