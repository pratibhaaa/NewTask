package com.example.newtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newtask.R;
import com.example.newtask.module.ProductModule;

import java.util.List;

public class MoreFromAdapter extends RecyclerView.Adapter<MoreFromAdapter.MoreFrom>  {
    Context context;
    List<ProductModule> list;

    public MoreFromAdapter(Context context, List<ProductModule> list) {
        this.context = context;
        this.list = list;
    }

/*
    public MoreFromAdapter(Context applicationContext, List<ProductModule> associatedProducts) {
    }
*/

    @NonNull
    @Override
    public MoreFromAdapter.MoreFrom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new MoreFrom(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MoreFromAdapter.MoreFrom holder, int position) {
        final ProductModule relatedProduct = list.get(position);
        holder.txtView.setText(relatedProduct.getBrandName());
        holder.tvKD.setText(relatedProduct.getCurrencyCode() + " " + relatedProduct.getRegularPrice());
        Glide.with(context)
                .load(relatedProduct.getImage())
                .placeholder(R.drawable.ic_launcher_background).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MoreFrom extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtView, tvKD;
        public MoreFrom(@NonNull View itemView) {
            super(itemView);

            txtView = itemView.findViewById(R.id.txtView);
            tvKD = itemView.findViewById(R.id.tvKD);
            image = itemView.findViewById(R.id.image);

        }
    }
}
