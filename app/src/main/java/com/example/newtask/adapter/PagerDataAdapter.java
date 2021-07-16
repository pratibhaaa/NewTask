package com.example.newtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.newtask.R;
import com.example.newtask.module.ProductModule;

import java.util.List;

public class PagerDataAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    List<String> stringList;

    public PagerDataAdapter(Context context,  List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.pager, container, false);
        ImageView img=view.findViewById(R.id.img_pager);
        ProductModule.Data productModule=new ProductModule.Data();
        productModule.getImage();
        Glide.with(context)
                .load(stringList.get(position))
                .placeholder(R.drawable.ic_launcher_background).into(img);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
