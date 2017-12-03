package com.ade.skeleton.mvvm.viewmodel.productdetail;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.text.Html;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ade.skeleton.mvvm.helper.AppUtility;
import com.ade.skeleton.mvvm.model.ProductDetail;

/**
 * Created by Irfan AFif on 10/6/2017.
 */

public class ProductDetailDataBinding extends BaseObservable {

    private String productName;
    private String productPrice;
    private String productDesc;
    private String productImage;

    public ProductDetailDataBinding(ProductDetail.Product product) {
        productName = product.getName();
        productPrice = AppUtility.formatMoney("Rp. ", product.getPrice(), '.', "");
        productDesc = String.valueOf(Html.fromHtml(product.getDesc()));
        productImage = product.getImages().get(0);

    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductImage() {
        return productImage;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
