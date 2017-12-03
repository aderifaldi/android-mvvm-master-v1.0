package com.ade.skeleton.mvvm.viewmodel.productlist;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ade.skeleton.mvvm.data.repository.Repository;
import com.ade.skeleton.mvvm.model.ApiResponse;
import com.ade.skeleton.mvvm.viewmodel.GenericViewModel;
import com.ade.skeleton.mvvm.viewmodel.IGenericViewModel;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductListViewModel extends GenericViewModel implements IGenericViewModel {

    //Todo: Return Response
    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    //Todo: Get Product List
    public LiveData<ApiResponse> getProductList() {
        apiResponse.addSource(
                repository.getProduct(),
                apiResponse -> this.apiResponse.setValue(apiResponse)
        );
        return apiResponse;
    }

    @Override
    public void showLoading(Context context, boolean isCancelable) {
        showProgressLoading(context, isCancelable);
    }

    @Override
    public void dismissLoading() {
        dismissProgressLoading();
    }
}
