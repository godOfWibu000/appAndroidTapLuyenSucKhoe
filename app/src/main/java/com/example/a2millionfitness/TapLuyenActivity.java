package com.example.a2millionfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TapLuyenActivity extends AppCompatActivity {
    ImageView tapLuyenIv;
    TextView tl_tenBaiTapTv;
    Button hoanThanhBaiTapBtn;

    String message, anh1, anh2, anh3, maBaiTap;

    int i = 2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_luyen);

        getSupportActionBar().hide();
        anh1 = getIntent().getStringExtra("anh1");
        anh2 = getIntent().getStringExtra("anh2");
        anh3 = getIntent().getStringExtra("anh3");
        maBaiTap = getIntent().getStringExtra("maBaiTap");
        Toast.makeText(this, maBaiTap, Toast.LENGTH_SHORT).show();

        tapLuyenIv = (ImageView) findViewById(R.id.tapLuyenIv);
        tl_tenBaiTapTv = (TextView) findViewById(R.id.tl_tenBaiTapTv);
        hoanThanhBaiTapBtn = (Button)findViewById(R.id.hoanThanhBaiTapBtn);

        hoanThanhBaiTapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int maBaiTapNumber = (Integer.parseInt(maBaiTap) + 1);
                maBaiTap = maBaiTapNumber + "";

                Intent i = new Intent(TapLuyenActivity.this, BatDauActivity.class);
                i.putExtra("maBaiTap", maBaiTap);
                startActivity(i);
            }
        });

        Context c = getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/" + anh1, null, c.getPackageName());
        tapLuyenIv.setImageResource(id);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                message = anh2;
                Context c = getApplicationContext();
                int id = c.getResources().getIdentifier("drawable/" + message, null, c.getPackageName());
                tapLuyenIv.setImageResource(id);
            }
        }, 1000);

        Handler handler2 = new Handler();
        while (i < 10000) {
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    message = anh3;
                    Context c = getApplicationContext();
                    int id = c.getResources().getIdentifier("drawable/" + message, null, c.getPackageName());
                    tapLuyenIv.setImageResource(id);
                }
            }, i * 1000);

            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    message = anh1;
                    Context c = getApplicationContext();
                    int id = c.getResources().getIdentifier("drawable/" + message, null, c.getPackageName());
                    tapLuyenIv.setImageResource(id);
                }
            }, i * 1000 + 1000);

            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    message = anh2;
                    Context c = getApplicationContext();
                    int id = c.getResources().getIdentifier("drawable/" + message, null, c.getPackageName());
                    tapLuyenIv.setImageResource(id);
                }
            }, i * 1000 + 2000);
            i += 3;
        }
    }
}