package com.example.a2millionfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BaiTapAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BaiTap> listBaitap;

    public BaiTapAdapter(Context context, int layout, List<BaiTap> listBaitap) {
        this.context = context;
        this.layout = layout;
        this.listBaitap = listBaitap;
    }

    @Override
    public int getCount() {
        return listBaitap.size();
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
        TextView tenBaiTapTv, moTaBaiTapTv;

        ImageView anhBaiTapIv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.tenBaiTapTv = (TextView) convertView.findViewById(R.id.tenBaiTapTv);
            holder.moTaBaiTapTv = (TextView) convertView.findViewById(R.id.moTaBaiTapTv);
            holder.anhBaiTapIv = (ImageView) convertView.findViewById(R.id.anhBaiTapIv);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        BaiTap baiTap = listBaitap.get(position);
        holder.tenBaiTapTv.setText(baiTap.getTenBaiTap());
        holder.moTaBaiTapTv.setText(baiTap.getGhiChu());

        return convertView;
    }
}
