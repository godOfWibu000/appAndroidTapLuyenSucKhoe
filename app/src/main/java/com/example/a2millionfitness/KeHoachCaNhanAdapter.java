package com.example.a2millionfitness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class KeHoachCaNhanAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<KeHoachCaNhan> listKeHoachCaNhan;

    public KeHoachCaNhanAdapter(Context context, int layout, List<KeHoachCaNhan> listKeHoachCaNhan) {
        this.context = context;
        this.layout = layout;
        this.listKeHoachCaNhan = listKeHoachCaNhan;
    }

    @Override
    public int getCount() {
        return listKeHoachCaNhan.size();
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tenKeHoachTv = (TextView) convertView.findViewById(R.id.tenKeHoachTv);
            holder.anhKeHoachIv = (ImageView) convertView.findViewById(R.id.anhKeHoachIv);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        KeHoachCaNhan keHoachCaNhan = listKeHoachCaNhan.get(position);
        holder.tenKeHoachTv.setText(keHoachCaNhan.getTenKeHoach());

        String resource = keHoachCaNhan.getAnh();
        Context c = context.getApplicationContext();
        int id = c.getResources().getIdentifier("drawable/" + resource, null, c.getPackageName());
        holder.anhKeHoachIv.setImageResource(id);

        return convertView;
    }
}
