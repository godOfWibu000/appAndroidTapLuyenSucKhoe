package com.example.a2millionfitness;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
//    Khai bao wiget
    ListView nguoiDungLv;
    Button themNguoiDungBtn;

//    Khai bao list nguoi dung
    ArrayList<User> listNguoiDung;

    ArrayList<String> listTenNguoiDung;

//    Adapter
    UserAdapter adapter;

    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

//        Khoi tao bien wiget
        nguoiDungLv = (ListView) this.<View>findViewById(R.id.nguoiDungLv);
        themNguoiDungBtn = (Button) findViewById(R.id.themNguoiDungBtn);

//        Khoi tao list
        listNguoiDung = new ArrayList<>();
        listTenNguoiDung = new ArrayList<>();

//        Khoi tao va gan adapter vao listview
        adapter = new UserAdapter(this, R.layout.dong_nguoidung, listNguoiDung);
        nguoiDungLv.setAdapter(adapter);

//        Mo ket noi csdl
        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);

//        Tao table
        try {
            String sql = "CREATE TABLE NguoiDung(ten TEXT primary key)";
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("Error", "Table da ton tai");
        }


//        Hien thi danh sach nguoi dung
        hienThiDSNguoiDung();

//        Nut bam va su kien
        nguoiDungLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenNguoiDung = listTenNguoiDung.get(position);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("name", tenNguoiDung);
                startActivity(i);
            }
        });

        themNguoiDungBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

//    Cac ham, phuong thuc
    private void hienThiDSNguoiDung(){
        Cursor cur = db.rawQuery("Select * From NguoiDung", null);
        cur.moveToNext();
        while (cur.isAfterLast() == false){
            String ten = cur.getString(0);
            cur.moveToNext();
            listNguoiDung.add(new User(ten));
            listTenNguoiDung.add(ten);
        }
        cur.close();
        adapter.notifyDataSetChanged();
    }
}