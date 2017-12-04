package com.ade.skeleton.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ade.skeleton.mvvm.model.ApiResponse;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductListViewModel extends BaseViewModel implements BaseViewModel.IBaseViewModel {

    //Todo: Return Response
    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    //Todo: Get Product List
    public void getProductList() {
        apiResponse.addSource(
                repository.getProduct(),
                apiResponse -> this.apiResponse.setValue(apiResponse)
        );
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
