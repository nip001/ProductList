package com.batch9.productlist.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("productName")
    private String productName;
    @SerializedName("productPrice")
    private String productPrice;
    @SerializedName("productDescription")
    private String productDescription;
    @SerializedName("productVarian")
    private String productVarian;
    @SerializedName("productImage")
    private String productImage;

    public Product(long id, String productName, String productPrice, String productDescription, String productVarian, String productImage) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productVarian = productVarian;
        this.productImage = productImage;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductVarian() {
        return productVarian;
    }

    public void setProductVarian(String productVarian) {
        this.productVarian = productVarian;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.productName);
        dest.writeString(this.productPrice);
        dest.writeString(this.productDescription);
        dest.writeString(this.productVarian);
        dest.writeString(this.productImage);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.productName = source.readString();
        this.productPrice = source.readString();
        this.productDescription = source.readString();
        this.productVarian = source.readString();
        this.productImage = source.readString();
    }

    protected Product(Parcel in) {
        this.id = in.readLong();
        this.productName = in.readString();
        this.productPrice = in.readString();
        this.productDescription = in.readString();
        this.productVarian = in.readString();
        this.productImage = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}

