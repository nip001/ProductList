package com.batch9.productlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.batch9.productlist.adapter.ProductAdapter;
import com.batch9.productlist.entity.Product;
import com.batch9.productlist.service.ApiClient;
import com.batch9.productlist.service.ProductInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnTambah;
    ProductAdapter productAdapter;
    RecyclerView rvProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = findViewById(R.id.btnTambah);
        rvProduct = findViewById(R.id.rv_product);

        ProductInterface productInterface = ApiClient.getRetrofit().create(ProductInterface.class);
        Call<ArrayList<Product>> call = productInterface.getAll();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                productAdapter = new ProductAdapter(MainActivity.this,response.body());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);

                rvProduct.setLayoutManager(layoutManager);
                rvProduct.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                System.out.println(t);
            }
        });
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Tambah.class);
                startActivity(intent);
            }
        });
    }
}