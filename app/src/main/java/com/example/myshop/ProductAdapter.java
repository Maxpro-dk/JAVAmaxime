package com.example.myshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myshop.entity.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    final List<Product> products;
    final Context context;

    public ProductAdapter(List products, Context context) {
        this.products=products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
       return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ConstraintLayout myItem= (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.activity_list_item,parent,false);
        TextView nameTextView =  myItem.findViewById(R.id.name);
        TextView stockQuantityTextView =  myItem.findViewById(R.id.stock_quantity);
        TextView priceTextView =  myItem.findViewById(R.id.price);
        nameTextView.setText(products.get(position).getName());
        priceTextView.setText(String.format("%,.2f",products.get(position).getPrice())+" Fcfa");
        stockQuantityTextView.setText((products.get(position).getStockQuantity()>1? products.get(position).getStockQuantity()+" disponibles" : products.get(position).getStockQuantity()+" disponibles"));
        return myItem;

    }
}
