package com.example.duana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duana.R;
import com.example.duana.model.BinhLuan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BinhLuanAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BinhLuan> luanList;


    public BinhLuanAdapter(Context context, int layout, List<BinhLuan> luanList) {
        this.context = context;
        this.layout = layout;
        this.luanList = luanList;

    }

    @Override
    public int getCount() {
        return luanList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder {
        TextView textView;
        TextView date;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.textView = view.findViewById(R.id.tengnuoidung);
            viewHolder.date = view.findViewById(R.id.gio);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final BinhLuan binhLuan = luanList.get(i);
        viewHolder.textView.setText(binhLuan.getComment());
        viewHolder.date.setText(binhLuan.getDate());
        DateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
//        dateFormat.setLenient(false);
        Date today=new Date();
        String s=dateFormat.format(today);
        viewHolder.date.setText(s);
        return view;
    }
}
