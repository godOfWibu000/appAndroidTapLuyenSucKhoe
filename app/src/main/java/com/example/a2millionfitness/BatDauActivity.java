package com.example.a2millionfitness;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BatDauActivity extends AppCompatActivity {
    int thuTuHD = 1;
    int thuTuBaiTap = 1;
    String thuTuBaiTapStr, soLuongBaiTap;
    String maBaiTap, anh1, anh2, anh3;

    ConstraintLayout huongDanLayout;
    TextView tenBatDauBaiTapTv, noiDungHuongDanTv;
    ImageView anhHuongDanIv, circleHD1, circleHD2, circleHD3;
    Button huongDanTruocBtn, huongDanSauBtn, boQuaHuongDanBtn, batDauTapBtn, boQuaBaiTapBtn;
    ListView batDauBuocTapLv;

    SQLiteDatabase db;

    BuocTapAdapter adapter;

    ArrayList<BuocTap> listBuocTap;

    ArrayList<String> listAnhBuocTap;

    AlertDialog.Builder dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_dau);

        getSupportActionBar().hide();

        huongDanLayout = (ConstraintLayout)findViewById(R.id.huongDanLayout);
        tenBatDauBaiTapTv = (TextView)findViewById(R.id.tenBatDauBaiTapTv);
        noiDungHuongDanTv = (TextView) findViewById(R.id.noiDungHuongDanTv);
        anhHuongDanIv = (ImageView) findViewById(R.id.anhHuongDanIv);
        batDauBuocTapLv = (ListView)findViewById(R.id.batDauBuocTapLv);
        circleHD1 = (ImageView)findViewById(R.id.circleHD1);
        circleHD2 = (ImageView)findViewById(R.id.circleHD2);
        circleHD3 = (ImageView)findViewById(R.id.circleHD3);
        huongDanTruocBtn = (Button)findViewById(R.id.huongDanTruocBtn);
        huongDanSauBtn = (Button)findViewById(R.id.huongDanSauBtn);
        boQuaHuongDanBtn = (Button)findViewById(R.id.boQuaHuongDanBtn);
        batDauTapBtn = (Button)findViewById(R.id.batDauTapBtn);
        boQuaBaiTapBtn = (Button)findViewById(R.id.boQuaBaiTapBtn);

        maBaiTap = getIntent().getStringExtra("maBaiTap");
        soLuongBaiTap = getIntent().getStringExtra("soLuongBaiTap");
        thuTuBaiTapStr = getIntent().getStringExtra("thuTuBaiTap");
        if(thuTuBaiTapStr != null) {
            thuTuBaiTap = Integer.parseInt(thuTuBaiTapStr);
        }else{
            thuTuBaiTapStr = "1";
        }
        Toast.makeText(this, "So luong bai tap: " + soLuongBaiTap, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Ma bai tap: " + maBaiTap, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Thu tu:" + thuTuBaiTapStr + "", Toast.LENGTH_SHORT).show();
        if(thuTuBaiTap != 1){
            huongDanLayout.setVisibility(View.INVISIBLE);
        }

        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);

        listBuocTap = new ArrayList<>();
        adapter = new BuocTapAdapter(this, R.layout.dong_buoctap, listBuocTap);
        batDauBuocTapLv.setAdapter(adapter);

        listAnhBuocTap = new ArrayList<>();

        huongDanTruocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuTuHD--;
                huongDanSauBtn.setVisibility(View.VISIBLE);

                if(thuTuHD == 1){
                    anhHuongDanIv.setImageResource(R.drawable.khoidong);
                    noiDungHuongDanTv.setText("Khởi động trước khi tập luyện giúp tăng hiệu quả tập luyện và giảm chấn thương không mong muốn");
                    huongDanTruocBtn.setVisibility(View.INVISIBLE);
                    circleHD1.setImageResource(R.drawable.circle1);
                    circleHD2.setImageResource(R.drawable.circle2);
                    circleHD3.setImageResource(R.drawable.circle2);
                }
                if(thuTuHD == 2){
                    anhHuongDanIv.setImageResource(R.drawable.nghingoi);
                    noiDungHuongDanTv.setText("Nghỉ ngơi giữa các bài tập giúp bạn lấy lại năng lượng để có thể tiếp tục");
                    circleHD1.setImageResource(R.drawable.circle2);
                    circleHD2.setImageResource(R.drawable.circle1);
                    circleHD3.setImageResource(R.drawable.circle2);
                }
                boQuaHuongDanBtn.setText("Bỏ qua");
            }
        });

        huongDanSauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuTuHD++;
                huongDanTruocBtn.setVisibility(View.VISIBLE);

                if(thuTuHD == 2){
                    anhHuongDanIv.setImageResource(R.drawable.nghingoi);
                    noiDungHuongDanTv.setText("Nghỉ ngơi giữa các bài tập giúp bạn lấy lại năng lượng để có thể tiếp tục");
                    circleHD1.setImageResource(R.drawable.circle2);
                    circleHD2.setImageResource(R.drawable.circle1);
                    circleHD3.setImageResource(R.drawable.circle2);
                }
                if(thuTuHD == 3){
                    anhHuongDanIv.setImageResource(R.drawable.khokhan);
                    noiDungHuongDanTv.setText("Nếu cảm thấy quá khó khăn để hoàn thành bài tập, hãy bỏ qua. Tập luyện chăm chỉ sẽ giúp bạn hoàn thành những mục tiêu mới");
                    huongDanSauBtn.setVisibility(View.INVISIBLE);
                    boQuaHuongDanBtn.setText("Bắt đầu");

                    circleHD1.setImageResource(R.drawable.circle2);
                    circleHD2.setImageResource(R.drawable.circle2);
                    circleHD3.setImageResource(R.drawable.circle1);
                }
            }
        });

        boQuaHuongDanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huongDanLayout.setVisibility(View.INVISIBLE);
            }
        });

        Cursor cur = db.rawQuery("Select * From BaiTap Where MaBaiTap=" + maBaiTap, null);
        while(cur.moveToNext()){
            tenBatDauBaiTapTv.setText(cur.getString(1));
        }
        cur.close();

        Cursor c = db.rawQuery("Select * From BuocTap Where MaBaiTap=" + maBaiTap, null);
        while(c.moveToNext()){
            int ma = c.getInt(0);
            int maBaiTap = c.getInt(1);
            String noiDung = c.getString(2);
            String anh = c.getString(4);

            listBuocTap.add(new BuocTap(ma, maBaiTap, noiDung, "", anh));
            listAnhBuocTap.add(anh);
        }
        c.close();

        batDauTapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anh1 = listAnhBuocTap.get(0);
                anh2 = listAnhBuocTap.get(1);
                anh3 = listAnhBuocTap.get(2);
                Intent i = new Intent(BatDauActivity.this, TapLuyenActivity.class);
                i.putExtra("anh1", anh1);
                i.putExtra("anh2", anh2);
                i.putExtra("anh3", anh3);
                i.putExtra("maBaiTap", maBaiTap);
                startActivity(i);
            }
        });

        boQuaBaiTapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(BatDauActivity.this, 0);
                dialog.setTitle("")
                        .setMessage("Bạn có muốn bỏ qua bài tập này?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(!thuTuBaiTapStr.equals(soLuongBaiTap)){
                                    int maBaiTapNumber = (Integer.parseInt(maBaiTap) + 1);
                                    maBaiTap = maBaiTapNumber + "";

                                    thuTuBaiTap++;

                                    Intent i = new Intent(BatDauActivity.this, BatDauActivity.class);
                                    i.putExtra("maBaiTap", maBaiTap);
                                    i.putExtra("thuTuBaiTap", thuTuBaiTap + "");
                                    i.putExtra("soLuongBaiTap", soLuongBaiTap);
                                    startActivity(i);
                                }else{
                                    Intent i = new Intent(BatDauActivity.this, KetThucTapLuyenActivity.class);
                                    startActivity(i);
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
            }
        });
    }
}