package com.ade.skeleton.mvvm.viewmodel;

import android.content.Context;

/**
 * Created by aderifaldi on 03-Dec-17.
 */

public interface IGenericViewModel {

    void showLoading(Context context, boolean isCancelable);
    void dismissLoading();

}
