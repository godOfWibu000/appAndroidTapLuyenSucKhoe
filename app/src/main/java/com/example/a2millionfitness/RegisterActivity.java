package com.example.a2millionfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
//    Khai bao wiget
    EditText tenNguoiDungTb;
    Button themNguoiDungBtn;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Khoi tao wiget
        tenNguoiDungTb = (EditText) findViewById(R.id.tenBaiTapTv);
        themNguoiDungBtn = (Button)findViewById(R.id.themNguoiDungBtn);

//        Ket noi csdl
        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);

//        Nut bam them nguoi dung
        themNguoiDungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = tenNguoiDungTb.getText().toString();

                ContentValues value = new ContentValues();
                value.put("ten", ten);

                String msg = "";

                if(db.insert("NguoiDung", null, value) == -1)
                {
                    msg = "Thêm người dùng không thành công!";
                }else{
                    msg = "Thêm người dùng thành công!";
                }
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}