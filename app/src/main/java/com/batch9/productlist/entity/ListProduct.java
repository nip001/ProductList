package com.batch9.productlist.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ListProduct implements Parcelable {
    private ArrayList<Product> product;

    public ListProduct() {
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    public ListProduct(ArrayList<Product> product) {
        this.product = product;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.product);
    }

    public void readFromParcel(Parcel source) {
        this.product = source.createTypedArrayList(Product.CREATOR);
    }

    protected ListProduct(Parcel in) {
        this.product = in.createTypedArrayList(Product.CREATOR);
    }

    public static final Parcelable.Creator<ListProduct> CREATOR = new Parcelable.Creator<ListProduct>() {
        @Override
        public ListProduct createFromParcel(Parcel source) {
            return new ListProduct(source);
        }

        @Override
        public ListProduct[] newArray(int size) {
            return new ListProduct[size];
        }
    };
}
