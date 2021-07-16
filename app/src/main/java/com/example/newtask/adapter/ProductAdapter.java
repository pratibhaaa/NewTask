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


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Product> {
    Context context;
    List<ProductModule.RelatedProduct> list;

    public ProductAdapter(Context context, List<ProductModule.RelatedProduct> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Product onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new Product(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Product holder, int position) {

        final ProductModule.RelatedProduct relatedProduct = list.get(position);
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

    public class Product extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtView, tvKD;

        public Product(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.txtView);
            tvKD = itemView.findViewById(R.id.tvKD);
            image = itemView.findViewById(R.id.image);

        }
    }
}
