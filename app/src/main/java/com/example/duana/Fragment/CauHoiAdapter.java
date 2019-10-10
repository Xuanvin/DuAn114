package com.example.duana.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duana.R;

import java.util.List;

public class CauHoiAdapter extends BaseAdapter {
    private Context context;
    private List<DanhGia> danhGias;

    public CauHoiAdapter(Context context, List<DanhGia> danhGias) {
        this.context = context;
        this.danhGias = danhGias;
    }

    @Override
    public int getCount() {
        return danhGias.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  class  ViewHolder{
        TextView edt1,edt2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.datcauhoiiteam,null);
            holder.edt1=view.findViewById(R.id.tenngdung);
//            holder.edt2=view.findViewById(R.id.tengio);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        final DanhGia danh=danhGias.get(i);
        holder.edt1.setText(""+danh.getTennguoidung());
//        holder.edt2.setText(""+danh.getNgayh());
        return view;
    }
}
