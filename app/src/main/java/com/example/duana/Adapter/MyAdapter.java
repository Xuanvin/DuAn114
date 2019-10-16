package com.example.duana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.OnDigList;
import com.example.duana.R;
import com.example.duana.mode.Review;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Review[] itemsData;
   private Context context;
private  OnDigList onDigList;

    public void setOnDigList(OnDigList onDigList) {
        this.onDigList = onDigList;
    }

    public MyAdapter(Context context, Review[] itemsData) {
        this.itemsData = itemsData;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.itemryceview, null);



        return new ViewHolder(view,onDigList);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.txtViewTitle.setText(itemsData[position].getTxt());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getPng());

    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;
        LinearLayout recyview;


        public ViewHolder(View itemLayoutView, final OnDigList onDigList) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.txt);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.view);
            recyview = itemLayoutView.findViewById(R.id.recyeview1);
            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onDigList.onFondoClick(position);

                    }
                }
            });
            imgViewIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onDigList.onAccionClick(position);
                    }
                }
            });
        }
    }
}