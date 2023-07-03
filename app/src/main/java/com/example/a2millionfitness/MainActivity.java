package com.example.a2millionfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    Khai bao bien cuc bo
    String name;

//    Khai bao wiget
    TextView tenNguoiDungTv;
    Button homeBtn, calendarBtn, processBtn, userBtn, createWorkBtn;
    ConstraintLayout homeLayout, calendarLayout, achievementsLayout, userLayout, nullWorkLayout;
    ListView dangTapLv,keHoachLv;

//    Khai bao doi tuong csdl
    SQLiteDatabase db;


//    List va adapter
    KeHoachAdapter adapter;

    KeHoachCaNhanAdapter adapter1;

    ArrayList<KeHoach> listKeHoach;

    ArrayList<KeHoachCaNhan> listKeHoachCaNhan;

    ArrayList<Integer> listMaKeHoach;

    ArrayList<Integer> listMaKeHoachCaNhan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

//        Truyen du lieu tu activity login
        name = getIntent().getStringExtra("name");


//        Khoi tao cac bien wiget
        homeBtn = (Button)findViewById(R.id.homeBtn);
        calendarBtn = (Button) findViewById(R.id.calendarBtn);
        processBtn = (Button)findViewById(R.id.processBtn);
        userBtn = (Button) findViewById(R.id.userBtn);
        createWorkBtn = (Button)findViewById(R.id.createWorkBtn);

        homeLayout = (ConstraintLayout) findViewById(R.id.homeLayout);
        calendarLayout = (ConstraintLayout) findViewById(R.id.calendarLayout);
        achievementsLayout = (ConstraintLayout)findViewById(R.id.achievementsLayout);
        userLayout = (ConstraintLayout)findViewById(R.id.userLayout);
        nullWorkLayout = (ConstraintLayout)findViewById(R.id.nullWork_Layout);

        dangTapLv = (ListView)findViewById(R.id.dangTapLv);
        keHoachLv = (ListView)findViewById(R.id.keHoachLv);

        tenNguoiDungTv = (TextView)findViewById(R.id.tenNguoiDungTv);
        tenNguoiDungTv.setText(name);

        if(name == null)
        {
            nullWorkLayout.setVisibility(View.VISIBLE);
            homeLayout.setVisibility(View.INVISIBLE);
        }else{
            nullWorkLayout.setVisibility(View.INVISIBLE);
            homeLayout.setVisibility(View.VISIBLE);
        }

//        Ket noi csdl
        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);


//        Tao table
        try {
            String sql = "CREATE TABLE If not exists KeHoachTap(MaKeHoach Integer primary key autoincrement, TenKeHoach Text, GhiChu Text, Anh Text)";
            db.execSQL(sql);
        }catch (Exception e){
            Log.e("Error", "Table da ton tai");
        }

        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS KeHoachCaNhan(Ten Text, MaKeHoach Integer, SoNgayHoanThanh Integer, FOREIGN KEY(MaKeHoach) REFERENCES KeHoachTap(MaKeHoach))");
        }catch (Exception e){
            Log.e("Error", "Table da ton tai");
        }


//        Khoi tao va gan adapter vao listview
        listKeHoach = new ArrayList<>();
        adapter = new KeHoachAdapter(this, R.layout.dong_kehoach,listKeHoach);
        keHoachLv.setAdapter(adapter);

        listKeHoachCaNhan = new ArrayList<>();
        adapter1 = new KeHoachCaNhanAdapter(this, R.layout.dong_kehoach, listKeHoachCaNhan);
        dangTapLv.setAdapter(adapter1);

//        Danh sach ma ke hoach
        listMaKeHoach = new ArrayList<>();
        listMaKeHoachCaNhan = new ArrayList<>();

//        Hien thi danh sach ke hoach ca nhan
        hienThiDSKeHoachCaNhan();

//        Hien thi danh sach ke hoach
        hienThiDSKeHoach();

//        Dat chieu cao cho list ke hoach
        if(dangTapLv.getAdapter().getCount() == 0){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(480, 0);
            dangTapLv.setLayoutParams(lp);
        }else if(dangTapLv.getAdapter().getCount() == 1){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(480, 120);
            dangTapLv.setLayoutParams(lp);
        }else{
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(480, 240);
            dangTapLv.setLayoutParams(lp);
        }

//        Them cac ban ghi vao db
        themBanGhi();

//        Chon item tren listview
        keHoachLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String maKeHoach = listMaKeHoach.get(position).toString();
                Intent i = new Intent(MainActivity.this, ChiTietKeHoachActivity.class);
                i.putExtra("maKeHoach", maKeHoach);
                i.putExtra("name", name);
                startActivity(i);
            }
        });

        dangTapLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String maKeHoachCaNhan = listMaKeHoachCaNhan.get(position).toString();
                Intent i = new Intent(MainActivity.this, ChiTietKeHoachCaNhanActivity.class);
                i.putExtra("maKeHoachCaNhan", maKeHoachCaNhan);
                i.putExtra("name", name);
                startActivity(i);
            }
        });

//        Cac nut bam menu
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarLayout.setVisibility(View.INVISIBLE);
                achievementsLayout.setVisibility(View.INVISIBLE);
                userLayout.setVisibility(View.INVISIBLE);
                if(name == null)
                {
                    nullWorkLayout.setVisibility(View.VISIBLE);
                    homeLayout.setVisibility(View.INVISIBLE);
                }else{
                    nullWorkLayout.setVisibility(View.INVISIBLE);
                    homeLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nullWorkLayout.setVisibility(View.INVISIBLE);
                homeLayout.setVisibility(View.INVISIBLE);
                achievementsLayout.setVisibility(View.INVISIBLE);
                userLayout.setVisibility(View.INVISIBLE);
                calendarLayout.setVisibility(View.VISIBLE);
            }
        });

        processBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nullWorkLayout.setVisibility(View.INVISIBLE);
                homeLayout.setVisibility(View.INVISIBLE);
                calendarLayout.setVisibility(View.INVISIBLE);
                userLayout.setVisibility(View.INVISIBLE);
                achievementsLayout.setVisibility(View.VISIBLE);
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name == null)
                {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    homeLayout.setVisibility(View.INVISIBLE);
                    calendarLayout.setVisibility(View.INVISIBLE);
                    achievementsLayout.setVisibility(View.INVISIBLE);
                    userLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        createWorkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

//    Cac ham, phuong thuc
    private void themBanGhi(){
        if(keHoachLv.getAdapter().getCount() == 0){
            db.execSQL("Insert into KeHoachTap Values(null, 'Tập luyện giảm cân', '', 'tapgiamcan')");
            db.execSQL("Insert into KeHoachTap Values(null, 'Tập luyện thể hình', '', 'tapthehinh')");
            db.execSQL("Insert into KeHoachTap Values(null, 'Tập luyện tăng thể lực', '', 'taptangtheluc')");
            db.execSQL("Insert into KeHoachTap Values(null, 'Yoga', '', 'yoga')");
            db.execSQL("Insert into KeHoachTap Values(null, 'Tập luyện sức mạnh cường độ cao', '', 'tapsucmanh')");
            db.execSQL("Insert into KeHoachTap Values(null, 'Tập luyện cải thiện sức khỏe', '', 'tapcaithiensuckhoe')");
        }
    }
    private void hienThiDSKeHoachCaNhan(){
        if(name != null){
            Cursor cur1 = db.rawQuery("Select * From KeHoachCaNhan Where Ten='" + name + "'", null);
            while (cur1.moveToNext()){
                String ten = cur1.getString(0);
                int maKeHoach = cur1.getInt(1);
                int soNgayHoanthanh = cur1.getInt(2);

                listMaKeHoachCaNhan.add(maKeHoach);

                Cursor cur2 = db.rawQuery("Select * From KeHoachTap Where MaKeHoach=" + maKeHoach, null);
                while(cur2.moveToNext()){
                    String tenKeHoach = cur2.getString(1);
                    String ghiChu = cur2.getString(2);
                    String anh = cur2.getString(3);

                    listKeHoachCaNhan.add(new KeHoachCaNhan(maKeHoach, soNgayHoanthanh, ten, tenKeHoach, ghiChu, anh));
                }
                cur2.close();

            }
            cur1.close();
        }
    }
    private void hienThiDSKeHoach(){
        Cursor cur3 = db.query("KeHoachTap", null, null, null, null, null, null);
        while (cur3.moveToNext()){
            int maKeHoach = cur3.getInt(0);
            String tenKeHoach = cur3.getString(1);
            String ghiChu = cur3.getString(2);
            String anh = cur3.getString(3);
            listMaKeHoach.add(maKeHoach);
            listKeHoach.add(new KeHoach(1,tenKeHoach,ghiChu,anh));
        }
        cur3.close();
    }
}