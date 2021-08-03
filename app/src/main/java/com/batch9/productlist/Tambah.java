package com.batch9.productlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.batch9.productlist.entity.Product;
import com.batch9.productlist.service.ApiClient;
import com.batch9.productlist.service.ProductInterface;
import com.google.gson.Gson;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
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
    String mediaPath;
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
                Intent gambar = new Intent(getApplicationContext(), ImageSelectActivity.class);
                gambar.putExtra(ImageSelectActivity.FLAG_COMPRESS,true);
                gambar.putExtra(ImageSelectActivity.FLAG_CAMERA,true);
                gambar.putExtra(ImageSelectActivity.FLAG_GALLERY,true);
                startActivityForResult(gambar,1);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUploadProgress();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode ==requestCode && resultCode == RESULT_OK){
//            Uri selectedImage = data.getData();
//            String[] filePathColumn={MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,null,null,null);
//
//            assert cursor != null;
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(bitmap));

            imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }

    protected void onUploadProgress(){
        Product product = new Product();
        product.setProductName(editName.getText().toString());
        product.setProductDescription(editDesc.getText().toString());
        product.setProductPrice(editPrice.getText().toString());
        product.setProductVarian(editVarian.getText().toString());

        Gson gson = new Gson();
        String json = gson.toJson(product);

        ProductInterface productInterface = ApiClient.getRetrofit().create(ProductInterface.class);

        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        RequestBody data = RequestBody.create(MediaType.parse("text/plain"),json);
        Call<String>  call = productInterface.addProduct(fileToUpload, data);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast toast = Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t);

            }
        });
    }
}