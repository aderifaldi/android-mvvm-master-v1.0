package com.ade.skeleton.mvvm.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ade.skeleton.mvvm.BR;
import com.ade.skeleton.mvvm.R;
import com.ade.skeleton.mvvm.helper.GenericViewHolder;
import com.ade.skeleton.mvvm.model.Product;
import com.ade.skeleton.mvvm.view.activity.ProductDetailActivity;
import com.ade.skeleton.mvvm.databinding.ProductListDataBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by RadyaLabs PC on 11/10/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<GenericViewHolder> {
    private List<Product.Products> listItem;
    private Context context;

    public ProductListAdapter(Context context) {
        this.listItem = new ArrayList<>();
        this.context = context;
    }

    public List<Product.Products> getListItem() {
        return listItem;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.product_item, parent, false);
        GenericViewHolder vh = new GenericViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        final Product.Products itemData = listItem.get(position);
        ProductListDataBinding viewModel = new ProductListDataBinding(itemData, view ->
                context.startActivity(new Intent(context, ProductDetailActivity.class)
                        .putExtra("product", itemData))
        );

        holder.bindModel(BR.productData, viewModel);
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
