package com.example.duan.mode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Review[] itemsData;
    private  int layout;
    public MyAdapter(Review[] itemsData) {
        this.itemsData = itemsData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View  itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemryceview, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.txtViewTitle.setText(itemsData[position].getTxt());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getPng());
viewHolder.imgViewIcon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
switch (position){
    case 0:
        break;
    case 1:
        break;
}


    }
});

    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.txt);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.view);
        }
    }
}