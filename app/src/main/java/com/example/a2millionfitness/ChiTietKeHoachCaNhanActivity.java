package com.example.a2millionfitness;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChiTietKeHoachCaNhanActivity extends AppCompatActivity {
    int soLuongBaiTap = 0;
    String maKeHoachCaNhan, name;
    TextView tenKeHoachCaNhanTv;
    ImageView anhKeHoachCaNhanIv;
    ListView baiTapCaNhanLv;
    Button batDauBtn, xoaKeHoachBtn;

    SQLiteDatabase db;

    ArrayList<BaiTap> listBaiTap;
    BaiTapAdapter adapter;

    ArrayList<Integer> listMaBaiTap;

    AlertDialog.Builder dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ke_hoach_ca_nhan);

        getSupportActionBar().hide();
        maKeHoachCaNhan = getIntent().getStringExtra("maKeHoachCaNhan");
        name = getIntent().getStringExtra("name");

        baiTapCaNhanLv = (ListView)findViewById(R.id.baiTapCaNhanLv);
        tenKeHoachCaNhanTv = (TextView)findViewById(R.id.ct_tenKeHoachCaNhanTv);
        anhKeHoachCaNhanIv = (ImageView)findViewById(R.id.ct_anhKeHoachCaNhanIv);
        batDauBtn = (Button)findViewById(R.id.batDauBtn);
        xoaKeHoachBtn = (Button)findViewById(R.id.xoaKeHoachBtn);

        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);

        listBaiTap = new ArrayList<>();
        adapter = new BaiTapAdapter(this, R.layout.dong_baitap, listBaiTap);
        baiTapCaNhanLv.setAdapter(adapter);

        listMaBaiTap = new ArrayList<>();

        Cursor c = db.rawQuery("Select * From KeHoachTap Where MaKeHoach=" + maKeHoachCaNhan, null);
        while (c.moveToNext()){
            tenKeHoachCaNhanTv.setText(c.getString(1));
            String resource = c.getString(3);
            Context context = getApplicationContext();
            int id = context.getResources().getIdentifier("drawable/" + resource, null, context.getPackageName());
            anhKeHoachCaNhanIv.setImageResource(id);
        }

        Cursor cur = db.rawQuery("Select * From BaiTap Where MaKeHoach=" + maKeHoachCaNhan, null);
        while (cur.moveToNext()){
            int ma = cur.getInt(0);
            String ten = cur.getString(1);
            String moTa = cur.getString(4);

            soLuongBaiTap++;

            listMaBaiTap.add(ma);
            listBaiTap.add(new BaiTap(1, 3, ten, "", moTa, ""));
        }
        cur.close();

        xoaKeHoachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(ChiTietKeHoachCaNhanActivity.this, 0);
                dialog.setTitle("")
                        .setMessage("Bạn có muốn xóa kế hoạch này?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.execSQL("Delete From KeHoachCaNhan Where MaKeHoach=" + maKeHoachCaNhan + " and Ten='" + name + "'");
                                Intent i = new Intent(ChiTietKeHoachCaNhanActivity.this, MainActivity.class);
                                i.putExtra("name", name);
                                startActivity(i);
                                Toast.makeText(ChiTietKeHoachCaNhanActivity.this, "Đã xóa kế hoạch, bạn vẫn có thể thêm lại và tiếp tục!", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });

        baiTapCaNhanLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String maBaiTap = listMaBaiTap.get(position).toString();

                Intent i = new Intent(ChiTietKeHoachCaNhanActivity.this, ChiTietBaiTapActivity.class);
                i.putExtra("maBaiTap", maBaiTap);
                startActivity(i);
            }
        });

        batDauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maBaiTap = listMaBaiTap.get(0).toString();
                Intent i = new Intent(ChiTietKeHoachCaNhanActivity.this, BatDauActivity.class);
                i.putExtra("maKeHoach", maKeHoachCaNhan);
                i.putExtra("maBaiTap", maBaiTap);
                i.putExtra("soLuongBaiTap", soLuongBaiTap + "");
                startActivity(i);
            }
        });
    }
}