package com.ade.skeleton.mvvm.helper;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Irfan AFif on 10/6/2017.
 */

public class GenericViewHolder extends RecyclerView.ViewHolder{
    ViewDataBinding binding;

    public GenericViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindModel(int modelType, Object obj){
        binding.setVariable(modelType, obj);
        binding.executePendingBindings();
    }
}
