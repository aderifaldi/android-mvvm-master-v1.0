package com.ade.skeleton.mvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ade.skeleton.mvvm.model.ApiResponse;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductDetailViewModel extends BaseViewModel implements BaseViewModel.IBaseViewModel {

    //Todo: Return Response
    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    //Todo: Get Product Detail
    public void getProductDetail(String id) {
        apiResponse.addSource(
                repository.getProductDetail(id),
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
