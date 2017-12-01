package com.ade.skeleton.mvvm.viewmodel;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ade.skeleton.mvvm.data.repository.Repository;
import com.ade.skeleton.mvvm.model.ApiResponse;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class GenericViewModel extends ViewModel {

    private MediatorLiveData<ApiResponse> mApiResponse;
    private Repository mIRepository;
    private ProgressDialog progressDialog;

    public GenericViewModel() {
        mApiResponse = new MediatorLiveData<>();
        mIRepository = new Repository();
    }

    public void showLoading(Context context) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();

    }

    public void dissmissLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<ApiResponse> getProduct() {
        mApiResponse.addSource(
                mIRepository.getProduct(),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }

    public LiveData<ApiResponse> getProductDetail(String id) {
        mApiResponse.addSource(
                mIRepository.getProductDetail(id),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }

}
