package com.batch9.productlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.batch9.productlist.R;
import com.batch9.productlist.entity.Product;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private ArrayList<Product> dataProduct;
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> dataProduct) {
        this.dataProduct = dataProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Glide.with(context).load(dataProduct.get(position).getProductImage()).into(holder.imageView);
        holder.proDesc.setText("Product Desc : "+ dataProduct.get(position).getProductDescription());
        holder.proVarian.setText("Product Varian : "+dataProduct.get(position).getProductVarian());
        holder.proName.setText(dataProduct.get(position).getProductName());
        holder.proPrice.setText("Product Price : "+dataProduct.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return dataProduct.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView proName,proPrice,proDesc,proVarian;
        public ProductViewHolder(@NonNull View itemView){
            super(itemView);
            proDesc = itemView.findViewById(R.id.txtDescription);
            proName = itemView.findViewById(R.id.txtProduct);
            proPrice = itemView.findViewById(R.id.txtPrice);
            proVarian = itemView.findViewById(R.id.txtVariant);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
