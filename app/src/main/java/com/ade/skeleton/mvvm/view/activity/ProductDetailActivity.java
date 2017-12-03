package com.ade.skeleton.mvvm.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ade.skeleton.mvvm.R;
import com.ade.skeleton.mvvm.databinding.ProductDetailActivityBinding;
import com.ade.skeleton.mvvm.model.Product;
import com.ade.skeleton.mvvm.model.ProductDetail;
import com.ade.skeleton.mvvm.viewmodel.productdetail.ProductDetailViewModel;
import com.ade.skeleton.mvvm.viewmodel.productdetail.ProductDetailDataBinding;

/**
 * Created by RadyaLabs PC on 29/11/2017.
 */

public class ProductDetailActivity extends AppCompatActivity {

    private ProductDetailActivityBinding productDetailActivityBinding;
    private ProductDetailViewModel viewModel;
    private Product.Products product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);
        productDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.product_detail_activity);

        product = (Product.Products) getIntent().getExtras().getSerializable("product");
        viewModel = ViewModelProviders.of(this).get(ProductDetailViewModel.class);

        loadProductDetail();

    }

    private void loadProductDetail() {
        //Todo: Show Progress Bar
        viewModel.showLoading(this, false);

        //Todo: Hit Api Product Detail
        viewModel.getProductDetail(product.getId());

        //Todo: Handle change emitted by LiveData
        viewModel.getApiResponse().observe(this, apiResponse -> {

            //Todo: Dismiss Progress Bar
            viewModel.dismissLoading();

            if (apiResponse != null) {
                if (apiResponse.getError() == null) {
                    ProductDetail data = (ProductDetail) apiResponse.getData();
                    if (data.getStatus().equals("OK")) {
                        ProductDetailDataBinding productDetailDataBinding = new ProductDetailDataBinding(data.getProduct());
                        productDetailActivityBinding.setProductDetailData(productDetailDataBinding);
                    }
                } else {
                    Toast.makeText(this, "Connection problem!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
