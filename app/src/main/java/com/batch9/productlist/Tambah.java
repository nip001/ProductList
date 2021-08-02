package com.batch9.productlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.batch9.productlist.entity.Product;
import com.batch9.productlist.service.ApiClient;
import com.batch9.productlist.service.ProductInterface;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambah extends AppCompatActivity {
    EditText editName,editPrice,editVarian,editDesc;
    Button btnKamera,btnSubmit;
    ImageView imageView;
    private int requestCode=1;
    RequestBody requestFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        editName = findViewById(R.id.editTextName);
        editPrice = findViewById(R.id.editTextPrice);
        editDesc = findViewById(R.id.editTextDescription);
        editVarian = findViewById(R.id.editTextVariant);
        btnKamera = findViewById(R.id.btnKamera);
        btnSubmit = findViewById(R.id.btnSubmit);
        imageView = findViewById(R.id.imageView2);

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gambar = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(gambar,1);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.setProductName(editName.getText().toString());
                product.setProductDescription(editDesc.getText().toString());
                product.setProductPrice(editPrice.getText().toString());
                product.setProductVarian(editVarian.getText().toString());
                ProductInterface productInterface = ApiClient.getRetrofit().create(ProductInterface.class);

//                RequestBody body =
//                        RequestBody.create("multipart/form-data",requestFile);
//                productInterface.addProduct(body," {\n" +
//                        "        \"productName\": \"Figure\",\n" +
//                        "        \"productPrice\": \"4000000\",\n" +
//                        "        \"productDescription\": \"Figure murah\",\n" +
//                        "        \"productVarian\": \"Patung\"\n" +
//                        "}");


//                call.enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        System.out.println(response);
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        System.out.println(t);
//
//                    }
//                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode ==requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(bitmap));

            imageView.setImageBitmap(bitmap);
        }
    }
}