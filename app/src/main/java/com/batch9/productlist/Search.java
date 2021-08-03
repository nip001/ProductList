package com.batch9.productlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.batch9.productlist.adapter.ProductAdapter;
import com.batch9.productlist.entity.ListProduct;
import com.batch9.productlist.entity.Product;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    RecyclerView rvSearch;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        rvSearch = findViewById(R.id.rv_search);

        ListProduct product = getIntent().getParcelableExtra("productIntent");
        productAdapter = new ProductAdapter(Search.this,product.getProduct());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Search.this,LinearLayoutManager.VERTICAL,false);

        rvSearch.setLayoutManager(layoutManager);
        rvSearch.setAdapter(productAdapter);
    }
}