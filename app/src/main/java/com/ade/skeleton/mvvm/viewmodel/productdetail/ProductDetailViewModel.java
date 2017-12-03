package com.ade.skeleton.mvvm.viewmodel.productdetail;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ade.skeleton.mvvm.model.ApiResponse;
import com.ade.skeleton.mvvm.viewmodel.GenericViewModel;
import com.ade.skeleton.mvvm.viewmodel.IGenericViewModel;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductDetailViewModel extends GenericViewModel implements IGenericViewModel{

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
