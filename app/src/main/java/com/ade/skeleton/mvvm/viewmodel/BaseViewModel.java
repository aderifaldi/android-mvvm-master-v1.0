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

class BaseViewModel extends ViewModel {

    MediatorLiveData<ApiResponse> apiResponse;
    Repository repository;
    ProgressDialog progressDialog;

    BaseViewModel() {
        apiResponse = new MediatorLiveData<>();
        repository = new Repository();
    }

    //Todo: Show Progress Loading
    void showProgressLoading(Context context, boolean isCancelable) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(isCancelable);
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();

    }

    //Todo: Dismiss Progress Loading
    void dismissProgressLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public interface IBaseViewModel {

        void showLoading(Context context, boolean isCancelable);
        void dismissLoading();

    }

}
