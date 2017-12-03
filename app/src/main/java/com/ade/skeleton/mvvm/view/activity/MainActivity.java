package com.ade.skeleton.mvvm.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ade.skeleton.mvvm.R;
import com.ade.skeleton.mvvm.model.Product;
import com.ade.skeleton.mvvm.view.adapter.ProductListAdapter;
import com.ade.skeleton.mvvm.viewmodel.productlist.ProductListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_product)
    RecyclerView listProduct;

    private ProductListViewModel viewModel;
    private LinearLayoutManager linearLayoutManager;
    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new ProductListAdapter(this);

        listProduct.setLayoutManager(linearLayoutManager);
        listProduct.setAdapter(adapter);

        loadProductList();

    }

    private void loadProductList() {

        //Todo: Show Progress Bar
        viewModel.showLoading(this, true);

        //Todo: Hit Api getProductList
        viewModel.getProductList();

        //Todo: Handle changes emitted by LiveData
        viewModel.getApiResponse().observe(this, apiResponse -> {

            //Todo: Dismiss Progress Bar
            viewModel.dismissLoading();

            if (apiResponse != null) {
                if (apiResponse.getError() == null) {
                    Product data = (Product) apiResponse.getData();
                    if (data.getStatus().equals("OK")) {
                        for (int i = 0; i < data.getProducts().size(); i++) {
                            adapter.getListItem().add(data.getProducts().get(i));
                            adapter.notifyItemInserted(adapter.getListItem().size() - 1);
                        }
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(this, "Connection problem!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
