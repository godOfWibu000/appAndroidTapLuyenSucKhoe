package com.example.a2millionfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChiTietKeHoachActivity extends AppCompatActivity {
    String maKeHoach, name;
    String maBaiTap;
    TextView tenKeHoachTv;
    ImageView anhKeHoachIv;

    Button themKeHoachBtn;

    ListView baiTapLv;

    SQLiteDatabase db;

    ArrayList<Integer> listMaBaiTap;
    ArrayList<String> listTenBaiTap;
    ArrayAdapter<String> arrayAdapter;

    ArrayList<BaiTap> listBaiTap;
    BaiTapAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ke_hoach);

        getSupportActionBar().hide();
        maKeHoach = getIntent().getStringExtra("maKeHoach");
        name = getIntent().getStringExtra("name");

        tenKeHoachTv = (TextView)findViewById(R.id.ct_tenKeHoachTv);
        anhKeHoachIv = (ImageView)findViewById(R.id.ct_anhKeHoachIv);
        themKeHoachBtn = (Button)findViewById(R.id.themKeHoachBtn);
        baiTapLv = (ListView)findViewById(R.id.baiTapLv);
        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);

        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS BaiTap(MaBaiTap Integer primary key autoincrement, TenBaiTap Text, MaKeHoach Integer, NoiDung Text, GhiChu Text, Anh Text)");
        }catch (Exception e){
            Log.e("Error", "Table da ton tai");
        }

        listMaBaiTap = new ArrayList<>();

        listTenBaiTap = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTenBaiTap);

        listBaiTap = new ArrayList<>();
        adapter = new BaiTapAdapter(this, R.layout.dong_baitap, listBaiTap);

        baiTapLv.setAdapter(adapter);

        hienThiDSBaiTap();
        if(baiTapLv.getAdapter().getCount() == 0){
            if(maKeHoach.equals("3")){
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Chạy bước nhỏ tại chỗ', 3, '', 'Bài tập tăng sức bền đơn giản mà lại cho hiệu quả tốt', '')");
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Squat bật nhảy', 3, '', 'Squat bật nhảy giúp rèn luyện sức bền cho cơ thể rất tốt', '')");
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Chạy nâng cao đùi', 3, '', 'Chạy nâng cao đùi là một bài tập tăng sức bền đơn giản', '')");
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Mountain climbers', 3, '', 'Mountain climbers không đòi hỏi quá nhiều kỹ thuật luyện tập nâng cao nhưng vẫn mang đến hiệu quả', '')");
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Chống đẩy', 3, '', 'Chống đẩy giúp tăng cường sức mạnh cơ bắp, cải thiện sức khỏe', '')");
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Nhảy dây', 3, '', 'Bài tập tăng sức bền, giúp đốt cháy calo hiệu quả', '')");
                db.execSQL("INSERT INTO BaiTap VALUES(null, 'Nhảy lò cò một chân', 3, '', 'Nhảy lò cò một chân là bài tập tăng sức bền cho tác dụng tốt được nhiều người yêu thích', '')");
                hienThiDSBaiTap();
            }
        }

        Cursor cur = db.rawQuery("Select * From KeHoachTap Where MaKeHoach=" + maKeHoach, null);
        while (cur.moveToNext()){
            String tenKeHoach = cur.getString(1);
            String resource = cur.getString(3);
            tenKeHoachTv.setText(tenKeHoach);

            Context c = getApplicationContext();
            int id = c.getResources().getIdentifier("drawable/" + resource, null, c.getPackageName());
            anhKeHoachIv.setImageResource(id);
        }
        cur.close();

        themKeHoachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor count = db.rawQuery("Select Count(*) From KeHoachCaNhan Where MaKeHoach=" + maKeHoach + " and Ten='" + name + "'", null);
                count.moveToFirst();
                int number = count.getInt(0);
                count.close();

                if(number>0){
                    Toast.makeText(ChiTietKeHoachActivity.this, "Bạn đã thêm kế hoạch này, vui lòng vào mục đang tập để tiếp tục!", Toast.LENGTH_LONG).show();
                }else{
                    db.execSQL("Insert into KeHoachCaNhan Values('" + name + "', " + maKeHoach + ", 0)");
                    Toast.makeText(ChiTietKeHoachActivity.this, "Thêm kế hoạch thành công!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ChiTietKeHoachActivity.this, MainActivity.class);
                    i.putExtra("name", name);
                    startActivity(i);
                }
            }
        });

        baiTapLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maBaiTap = listMaBaiTap.get(position).toString();

                Intent i = new Intent(ChiTietKeHoachActivity.this, ChiTietBaiTapActivity.class);
                i.putExtra("maBaiTap", maBaiTap);
                startActivity(i);
            }
        });
    }

    private void hienThiDSBaiTap(){
        Cursor cur = db.rawQuery("Select * From BaiTap Where MaKeHoach=" + maKeHoach, null);
        while (cur.moveToNext()){
            int ma = cur.getInt(0);
            String ten = cur.getString(1);
            String moTa = cur.getString(4);

            listMaBaiTap.add(ma);
            listBaiTap.add(new BaiTap(1, 3, ten, "", moTa, ""));
        }
        cur.close();
    }
}