package com.example.duana.Adapter.Vin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duana.R;
import com.example.duana.model.Loaisanpham;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by muase on 3/17/2018.
 */

public class LoaiSpAdapter extends BaseAdapter {

    private Context context;
    private List<Loaisanpham> list;

    public LoaiSpAdapter(Context context, List<Loaisanpham> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_loaisp,null);
            holder.ivHinhAnhSp = view.findViewById(R.id.iv_loaisp);
            holder.tvTenLoaiSp = view.findViewById(R.id.tv_ten_loaisp);
            view.setTag(holder);
        }else holder = (ViewHolder) view.getTag();

        Loaisanpham loaiSP = list.get(i);

        holder.tvTenLoaiSp.setText(loaiSP.getTenLoaisp());
        Picasso.get().load(loaiSP.getHinhanhLoaisp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivHinhAnhSp);
        return view;
    }

    class ViewHolder{
        TextView tvTenLoaiSp;
        ImageView ivHinhAnhSp;
    }
}
