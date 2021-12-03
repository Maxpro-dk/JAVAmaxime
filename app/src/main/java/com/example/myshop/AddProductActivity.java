package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myshop.dao.DataBaseRoom;
import com.example.myshop.dao.ProductRoomDao;
import com.example.myshop.entity.Product;
import com.example.myshop.webservice.ProductWebService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    TextInputEditText nameEditText;
    TextInputEditText descriptionEditTextText;
    TextInputEditText priceEditText;
    TextInputEditText stockQuantityEditText;
    TextInputEditText stockAlertEditText;


    Button myBtnAdd;
    Product product1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        nameEditText = findViewById(R.id.name);
        descriptionEditTextText =findViewById(R.id.description);
        priceEditText = findViewById(R.id.price);
        stockQuantityEditText =findViewById(R.id.stock_quantity);
        stockAlertEditText = findViewById(R.id.stock_alert);
        myBtnAdd =findViewById(R.id.my_btn_add);
        //modify


        //end Modify
        myBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product= new Product();
                Error error= new Error();
                error.isEmpty(nameEditText,"Veuillez renseigner la désignation");
                error.isEmpty(descriptionEditTextText,"Veuillez renseigner la description");
                error.isEmpty(priceEditText,"Veuillez renseigner le prix");
                error.isEmpty(stockQuantityEditText,"Veuillez renseigner la quantité disponible");
                error.isEmpty(stockAlertEditText,"Veuillez renseigner le stock alerte");
                if (error.isInputValide()){
                    product.setName(nameEditText.getText().toString());
                    product.setDescription(descriptionEditTextText.getText().toString());
                    product.setPrice(Float.parseFloat(priceEditText.getText().toString()));
                    product.setStockQuantity(Integer.parseInt(stockQuantityEditText.getText().toString()));
                    product.setStockAlert(Integer.parseInt(stockAlertEditText.getText().toString()));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//
                            ProductWebService pws =new ProductWebService();
                            pws.createProduct(new Product());
                            Room.getDao(getApplicationContext()).insert(product);

                       }
                    }).start();


                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("product",product);
                    setResult(Activity.RESULT_OK, returnIntent);

                    finish();
                }




            }
        });
    }



    public  class Error{
        List<Boolean> list;

        public Error() {
            this.list = new ArrayList<>();
        }
        public void isEmpty(TextInputEditText editText,String error){
            if (editText.getText().toString().isEmpty()){
                editText.setError(error);
                list.add(false);
            }

        }
        public boolean isInputValide(){
            return list.isEmpty();
        }
    }
}