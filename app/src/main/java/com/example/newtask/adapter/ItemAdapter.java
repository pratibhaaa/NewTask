package com.example.newtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtask.R;
import com.example.newtask.interfaces.OnBottomSheetSelect;
import com.example.newtask.module.ProductModule;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Items> {
    Context context;
    List<ProductModule.Attribute> arrListColor;
    OnBottomSheetSelect onBottomSheetSelect;

    public ItemAdapter(Context context, List<ProductModule.Attribute> arrListColor, OnBottomSheetSelect onBottomSheetSelect) {
        this.arrListColor = arrListColor;
        this.context = context;
        this.onBottomSheetSelect = onBottomSheetSelect;

    }

    @NonNull
    @Override
    public ItemAdapter.Items onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_row_item, null);
        return new Items(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.Items holder, final int position) {
        holder.tvItem.setText(arrListColor.get(position).getValue());
        holder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBottomSheetSelect.onItemSelect(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrListColor.size();
    }

    public class Items extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvItem;

        public Items(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
