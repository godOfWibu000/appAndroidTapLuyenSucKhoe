package com.example.a2millionfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KeHoachAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<KeHoach> listKeHoach;

    public KeHoachAdapter(Context context, int layout, List<KeHoach> listKeHoach) {
        this.context = context;
        this.layout = layout;
        this.listKeHoach = listKeHoach;
    }

    @Override
    public int getCount() {
        return listKeHoach.size();
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
        TextView tenKeHoachTv;

        ImageView anhKeHoachIv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.tenKeHoachTv = (TextView) convertView.findViewById(R.id.tenKeHoachTv);
            holder.anhKeHoachIv = (ImageView) convertView.findViewById(R.id.anhKeHoachIv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        KeHoach keHoach = listKeHoach.get(position);
        holder.tenKeHoachTv.setText(keHoach.getTenKeHoach());
        String resource = keHoach.getAnh();

        Context c = context.getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/" + resource, null, c.getPackageName());

        holder.anhKeHoachIv.setImageResource(id);

        return convertView;
    }
}
