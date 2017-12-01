package com.ade.skeleton.mvvm.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ade.skeleton.mvvm.data.api.ApiRepository;
import com.ade.skeleton.mvvm.data.api.ApiService;
import com.ade.skeleton.mvvm.model.ApiResponse;
import com.ade.skeleton.mvvm.model.Product;
import com.ade.skeleton.mvvm.model.ProductDetail;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class Repository implements ApiRepository {

    private static final String BASE_URL = "https://api.bukalapak.com/v2/";
    private static final int TIMEOUT = 60;
    private ApiService mApiService;
    private MutableLiveData<ApiResponse> liveData;

    public Repository() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();

        mApiService = builder.create(ApiService.class);
    }

    //Todo: Get Product List
    @Override
    public LiveData<ApiResponse> getProduct() {
        liveData = new MutableLiveData<>();
        Call<Product> call = mApiService.loadProduct();
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                try {
                    ApiResponse<Product> product = new ApiResponse<>();
                    product.setData(response.body());
                    liveData.setValue(product);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                ApiResponse<Throwable> throwable = new ApiResponse<>();
                throwable.setError(t);
                liveData.setValue(throwable);
            }
        });

        return liveData;
    }

    //Todo: Get Product Detail
    @Override
    public LiveData<ApiResponse> getProductDetail(String id) {
        liveData = new MutableLiveData<>();
        Call<ProductDetail> call = mApiService.loadProductDetail(id);
        call.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                try {
                    ApiResponse<ProductDetail> product = new ApiResponse<>();
                    product.setData(response.body());
                    liveData.setValue(product);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                ApiResponse<Throwable> throwable = new ApiResponse<>();
                throwable.setError(t);
                liveData.setValue(throwable);
            }
        });

        return liveData;
    }

}
