package com.example.a2millionfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BuocTapAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BuocTap> listBuocTap;

    public BuocTapAdapter(Context context, int layout, List<BuocTap> listBuocTap) {
        this.context = context;
        this.layout = layout;
        this.listBuocTap = listBuocTap;
    }

    @Override
    public int getCount() {
        return listBuocTap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    protected class ViewHolder{
        TextView thuTuBuocTapTv, noiDungTv;

        ImageView anhBuocTapIv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.thuTuBuocTapTv = (TextView)convertView.findViewById(R.id.thuTuBuocTapTv);
            holder.noiDungTv = (TextView)convertView.findViewById(R.id.noiDungBuocTapTv);
            holder.anhBuocTapIv = (ImageView)convertView.findViewById(R.id.anhBuocTapIv);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        BuocTap buocTap = listBuocTap.get(position);
        holder.thuTuBuocTapTv.setText("Bước " + buocTap.getMaBuocTap());
        holder.noiDungTv.setText(buocTap.getNoiDung());

        String resource = buocTap.getAnh();
        Context c = context.getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/" + resource, null, c.getPackageName());

        holder.anhBuocTapIv.setImageResource(id);

        return convertView;
    }
}
