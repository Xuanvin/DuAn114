package com.example.duana.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.R;

import java.io.IOException;
import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolderAdapter> {
    private Context context;
    private ArrayList<Uri> list;
    public PhotoAdapter(Context context){
        this.context=context;
    }
    public  void setData(ArrayList<Uri>list){
        this.list=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PhotoViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);

        return new PhotoViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolderAdapter holder, int position) {
Uri uri=list.get(position);
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
            holder.imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return null==list? 0:list.size();
    }

    public class PhotoViewHolderAdapter extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public PhotoViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_photo);
        }
    }
}
