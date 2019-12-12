package com.example.duana.Adapter.Vin.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duana.Fragment.ChiTietProduct.ChiTietSPActivity;
import com.example.duana.R;
import com.example.duana.model.SanPham;
import com.example.duana.model.Sanpham1;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by muase on 3/17/2018.
 */

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.Holder> {
    private Activity context;
    private List<Sanpham1> sanPhamList;
   public static Sanpham1 sanpham1;

    public SanPhamAdapter(Activity context, List<Sanpham1> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteamsanpham1,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Sanpham1 sanPham = sanPhamList.get(position);
        Picasso.get().load(sanPham.getHinhanhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image)
                .into(holder.ivDienthoai);
        holder.tvTenDt.setText(sanPham.getTensp());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiaDt.setText("Giá: "+decimalFormat.format(Integer.parseInt(sanPham.getGiasp()))+" Đ");
        sanpham1=sanPhamList.get(position);
        holder.tvMotaDt.setMaxLines(2);
        holder.tvMotaDt.setEllipsize(TextUtils.TruncateAt.END); //kiểu dấu 3 chấm nếu mô tả dài quá.
        holder.tvMotaDt.setText(sanPham.getMotasp());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivDienthoai;
        TextView tvTenDt, tvGiaDt, tvMotaDt;

        public Holder(View itemView) {
            super(itemView);
            ivDienthoai = itemView.findViewById(R.id.iv_dienthoai);
            tvTenDt = itemView.findViewById(R.id.tv_tendt);
            tvGiaDt = itemView.findViewById(R.id.tv_giadt);
            tvMotaDt = itemView.findViewById(R.id.tv_motadt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, ChiTietSPActivity.class);
            intent.putExtra("abc", sanPhamList.get(getLayoutPosition()));
            context.startActivityForResult(intent,123);
        }
    }
}
