package com.example.duana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duana.R;
import com.example.duana.mode.GridVIew;

import java.util.List;

public class GridviewApdapter  extends BaseAdapter {
    private Context context;
    private List<GridVIew> aGridVIews;
    private GridVIew[] itemsData;
    public GridviewApdapter( GridVIew[]itemsData,Context context, List<GridVIew> aGridVIews) {
        this.itemsData= itemsData;
        this.context = context;
        this.aGridVIews = aGridVIews;
    }

    @Override
    public int getCount() {
        return aGridVIews.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public  class ViewHolder{
        ImageView imageView;
        TextView txt;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.itemryceview,null);
            holder.imageView=view.findViewById(R.id.view);
            holder.txt=view.findViewById(R.id.txt);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        final GridVIew gridVIew=aGridVIews.get(i);
        holder.imageView.setImageResource(itemsData[i].getImg());
        holder.txt.setText(itemsData[i].getTxt());
        return null;
    }
}
