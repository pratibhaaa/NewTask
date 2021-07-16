package com.example.newtask.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidpagecontrol.PageControl;
import com.example.newtask.Global.RetrofitClientInstance;
import com.example.newtask.R;
import com.example.newtask.adapter.ItemAdapter;
import com.example.newtask.adapter.MoreFromAdapter;
import com.example.newtask.adapter.PagerDataAdapter;
import com.example.newtask.adapter.ProductAdapter;
import com.example.newtask.interfaces.OnBottomSheetSelect;
import com.example.newtask.module.ProductModule;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    PageControl page_control;
    Toolbar toolbar;
    RelativeLayout relColor;
    RelativeLayout relSize;
    ExpandableLayout elDesc, elReturn, elCOmpo, elSizeG;
    ImageView imageDesc, imgsizeG, img_return, imgCompo;
    RelativeLayout relDes, relSizeG, relReturn, relCompo;
    TextView tvBName, tvCompoCare, tvNewName, tvCurrency, tvMoreFrom, tvDesc, tvBlock, tvSize, tvColor;
    RecyclerView recyclerView_productss, recyclerView_morefrom;
    private List<ProductModule.Attribute> arrListColors;
    private List<ProductModule.Attribute> arrListSize;
    private ProductModule.Attribute selectedColor;
    private ProductModule.Attribute selectedSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        page_control = findViewById(R.id.page_control);
        toolbar = findViewById(R.id.toolbar);
        tvBName = findViewById(R.id.tvNBame);
        tvNewName = findViewById(R.id.tvNewName);
        tvMoreFrom = findViewById(R.id.tvMoreFrom);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvCompoCare = findViewById(R.id.tvCompoCare);
        tvColor = findViewById(R.id.tvColor);
        tvSize = findViewById(R.id.tvSize);
        elDesc = findViewById(R.id.elDesc);
        elReturn = findViewById(R.id.elReturn);
        elSizeG = findViewById(R.id.elSizeG);
        elCOmpo = findViewById(R.id.elCOmpo);
        imageDesc = findViewById(R.id.imageDes);
        imgsizeG = findViewById(R.id.imgsizeG);
        img_return = findViewById(R.id.img_return);
        imgCompo = findViewById(R.id.imgCompo);
        tvDesc = findViewById(R.id.tvDesc);
        relColor = findViewById(R.id.relColor);
        relSize = findViewById(R.id.relSize);
        relDes = findViewById(R.id.relDes);
        relSizeG = findViewById(R.id.relSizeG);
        relReturn = findViewById(R.id.relReturn);
        relCompo = findViewById(R.id.relCompo);
        tvBlock = findViewById(R.id.tvBlock);
        recyclerView_productss = findViewById(R.id.recyclerView_productss);
        recyclerView_morefrom = findViewById(R.id.recyclerView_morefrom);
        toolbar.setTitle("NEW COLLECTION");
        recyclerView_productss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_morefrom.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        relColor.setOnClickListener(this);
        relSize.setOnClickListener(this);
        relDes.setOnClickListener(this);
        relSizeG.setOnClickListener(this);
        relCompo.setOnClickListener(this);
        relReturn.setOnClickListener(this);
        getProductDetail();
    }

    private void getProductDetail() {
        Call<ProductModule.Example> call = RetrofitClientInstance.getWebservice(MainActivity.this)
                .getProductDetail(String.valueOf(1812), "en", "KW");
        call.enqueue(new Callback<ProductModule.Example>() {
            @Override
            public void onResponse(Call<ProductModule.Example> call, Response<ProductModule.Example> response) {
                if (response.body() != null) {
                    ProductModule.Example body = response.body();
                    Log.e("success", String.valueOf(response));
                    if (body.getSuccess()) {
                        tvCurrency.setText(body.getData().getCurrencyCode() + " " + body.getData().getRegularPrice());
                        tvBName.setText(body.getData().getBrandName());
                        tvNewName.setText(body.getData().getName());
                        tvBlock.setText(body.getData().getShop() + " ID:" + body.getData().getSku());
                        arrListColors = body.getData().getConfigurableOption().get(1).getAttributes();
                        arrListSize = body.getData().getConfigurableOption().get(0).getAttributes();
                        tvDesc.setText(body.getData().getDescription());
                        viewPager.setAdapter(new PagerDataAdapter(getApplicationContext(), body.getData().getImages()));
                        page_control.setViewPager(viewPager);
                        page_control.setPosition(0);
                        tvMoreFrom.setText("MORE FROM " + body.getData().getBrandName());
                        recyclerView_productss.setAdapter(new ProductAdapter(getApplicationContext(), body.getData().getRelatedProducts()));
                        recyclerView_morefrom.setAdapter(new MoreFromAdapter(getApplicationContext(), body.getData().getAssociatedProducts()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductModule.Example> call, Throwable t) {
                Log.e("fail", "Error" + t.getMessage());
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.relColor) {
            openColorPicker();
        }
        if (view.getId() == R.id.relSize) {
            openSizePicker();
        }
        if (view.getId() == R.id.relDes) {
            if (elDesc.isExpanded()) {
                elDesc.collapse();
                imageDesc.setImageResource(R.drawable.ic_add);
            } else {
                elDesc.expand();
                imageDesc.setImageResource(R.drawable.ic_minus);
            }
        }
        if (view.getId() == R.id.relSizeG) {
            if (elSizeG.isExpanded()) {
                elSizeG.collapse();
                imgsizeG.setImageResource(R.drawable.ic_add);
            } else {
                elSizeG.expand();
                imgsizeG.setImageResource(R.drawable.ic_minus);
            }
        }
        if (view.getId() == R.id.relCompo) {
            if (elCOmpo.isExpanded()) {
                elCOmpo.collapse();
                imgCompo.setImageResource(R.drawable.ic_add);
            } else {
                elCOmpo.expand();
                imgCompo.setImageResource(R.drawable.ic_minus);
            }
        }
        if (view.getId() == R.id.relReturn) {
            if (elReturn.isExpanded()) {
                elReturn.collapse();
                img_return.setImageResource(R.drawable.ic_add);
            } else {
                elReturn.expand();
                img_return.setImageResource(R.drawable.ic_minus);
            }
        }
    }

    private void openColorPicker() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_item, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        if (arrListColors.size() > 0) {
            final TextView btnDone = view.findViewById(R.id.tvDone);
            final TextView btnCancel = view.findViewById(R.id.tvCancel);
            final RecyclerView rvItems = view.findViewById(R.id.rvItems);
            rvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rvItems.setAdapter(new ItemAdapter(this, arrListColors, new OnBottomSheetSelect() {
                @Override
                public void onItemSelect(int position) {
                    selectedColor = arrListColors.get(position);
                }
            }));
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvColor.setText(selectedColor.getValue());
                    dialog.dismiss();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

    private void openSizePicker() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_item, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        if (arrListSize.size() > 0) {
            final TextView btnDone = view.findViewById(R.id.tvDone);
            final TextView btnCancel = view.findViewById(R.id.tvCancel);
            final RecyclerView rvItems = view.findViewById(R.id.rvItems);
            rvItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            rvItems.setAdapter(new ItemAdapter(this, arrListSize, new OnBottomSheetSelect() {
                @Override
                public void onItemSelect(int position) {
                    selectedSize = arrListSize.get(position);
                }
            }));
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSize.setText(selectedSize.getValue());
                    dialog.dismiss();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}