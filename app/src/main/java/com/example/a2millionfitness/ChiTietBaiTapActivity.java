package com.example.a2millionfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChiTietBaiTapActivity extends AppCompatActivity {
    String maBaiTap;

    TextView tenBaiTapTv;
    ListView buocTapLv;
    SQLiteDatabase db;

    BuocTapAdapter adapter;

    ArrayList<BuocTap> listBuocTap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_tap);

        getSupportActionBar().hide();
        maBaiTap = getIntent().getStringExtra("maBaiTap");

        tenBaiTapTv = (TextView)findViewById(R.id.ct_tenBaiTapTv);
        buocTapLv = (ListView)findViewById(R.id.buocTapLv);

        db = openOrCreateDatabase("quanLyTapLuyen.db", MODE_PRIVATE, null);

        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS BuocTap(MaBuocTap Integer primary key autoincrement, MaBaiTap Integer, NoiDung Text, GhiChu Text, Anh Text)");
        }catch (Exception e){
            Log.e("Error", "Table da ton tai");
        }

        listBuocTap = new ArrayList<>();
        adapter = new BuocTapAdapter(this, R.layout.dong_buoctap, listBuocTap);
        buocTapLv.setAdapter(adapter);

        hienThiDSBuocTap();
        if(buocTapLv.getAdapter().getCount() == 0){
            if(maBaiTap.equals("1")){
                db.execSQL("Insert into BuocTap Values(null, 1, 'Đứng thẳng người, hai chân dang rộng bằng hông, hai bàn tay nắm thật chặt và đặt ngay cạnh hông. ', '', 'chaybuocnho1')");
                db.execSQL("Insert into BuocTap Values(null, 1, 'Tiến hành chạy bước nhỏ. Nhấc chân lên, thực hiện tư thế chạy tại chỗ. Tiếp đất bằng mũi chân. Một lưu ý nhỏ ở đây đó là bàn chân chỉ hơi nhấc nhẹ lên so với mặt đất, bước chạy cần duy trì ở phạm vi nhỏ. Khi dậm chân xuống thì không được khóa phần đầu gối.', '', 'chaybuocnho2')");
                db.execSQL("Insert into BuocTap Values(null, 1, 'Giữ nhịp thở đều đặn và nhịp nhàng trong khi thực hiện chạy bước nhỏ liên tục trong 40 giây. Nên dành khoảng 10 giây nghỉ giữa các hiệp chạy. ', '', 'chaybuocnho1')");
            }
            if(maBaiTap.equals("2")){
                db.execSQL("Insert into BuocTap Values(null, 2, 'Đứng thẳng người, hai chân dang rộng bằng hông, hai bàn tay nắm thật chặt và đặt ngay cạnh hông. ', '', 'squat1')");
                db.execSQL("Insert into BuocTap Values(null, 2, 'Tiến hành chạy bước nhỏ. Nhấc chân lên, thực hiện tư thế chạy tại chỗ. Tiếp đất bằng mũi chân. Một lưu ý nhỏ ở đây đó là bàn chân chỉ hơi nhấc nhẹ lên so với mặt đất, bước chạy cần duy trì ở phạm vi nhỏ. Khi dậm chân xuống thì không được khóa phần đầu gối.', '', 'squat2')");
                db.execSQL("Insert into BuocTap Values(null, 2, 'Giữ nhịp thở đều đặn và nhịp nhàng trong khi thực hiện chạy bước nhỏ liên tục trong 40 giây. Nên dành khoảng 10 giây nghỉ giữa các hiệp chạy. ', '', 'squat3')");
            }
            hienThiDSBuocTap();
        }

        Cursor cur = db.rawQuery("Select * From BaiTap Where MaBaiTap=" + maBaiTap, null);
        while(cur.moveToNext()){
            tenBaiTapTv.setText(cur.getString(1));
        }
        cur.close();
    }

    private void hienThiDSBuocTap(){
        Cursor cur = db.rawQuery("Select * From BuocTap Where MaBaiTap=" + maBaiTap, null);
        while(cur.moveToNext()){
            int ma = cur.getInt(0);
            int maBaiTap = cur.getInt(1);
            String noiDung = cur.getString(2);
            String anh = cur.getString(4);

            listBuocTap.add(new BuocTap(ma, maBaiTap, noiDung, "", anh));
        }
    }
}