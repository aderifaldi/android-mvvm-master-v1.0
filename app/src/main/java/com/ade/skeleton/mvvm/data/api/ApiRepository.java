package com.ade.skeleton.mvvm.data.api;

import android.arch.lifecycle.LiveData;

import com.ade.skeleton.mvvm.model.ApiResponse;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public interface ApiRepository {

    LiveData<ApiResponse> getProduct();

    LiveData<ApiResponse> getProductDetail(String id);

}
