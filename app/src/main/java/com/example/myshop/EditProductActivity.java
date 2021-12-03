package com.example.myshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myshop.entity.Product;

public class EditProductActivity extends AddProductActivity {

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
        try {
            product1 = (Product) getIntent().getExtras().get("product");
            if (product1!=null){
                nameEditText.setText(product1.getName());
                descriptionEditTextText.setText(product1.getDescription());
                priceEditText.setText((int) product1.getPrice()+"");
                stockQuantityEditText.setText(product1.getStockQuantity()+"");
                stockAlertEditText.setText(product1.getStockAlert()+"");
                myBtnAdd.setText("Modifier");
            }


        }catch (Exception e){

        }

        //end Modify
        myBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddProductActivity.Error error= new AddProductActivity.Error();
                error.isEmpty(nameEditText,"Veuillez renseigner la désignation");
                error.isEmpty(descriptionEditTextText,"Veuillez renseigner la description");
                error.isEmpty(priceEditText,"Veuillez renseigner le prix");
                error.isEmpty(stockQuantityEditText,"Veuillez renseigner la quantité disponible");
                error.isEmpty(stockAlertEditText,"Veuillez renseigner le stock alerte");
                if (error.isInputValide()){

                    product1.setName(nameEditText.getText().toString());
                    product1.setDescription(descriptionEditTextText.getText().toString());
                    product1.setPrice(Float.parseFloat(priceEditText.getText().toString()));
                    product1.setStockQuantity(Integer.parseInt(stockQuantityEditText.getText().toString()));
                    product1.setStockAlert(Integer.parseInt(stockAlertEditText.getText().toString()));


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Room.getDao(getApplicationContext()).modify(product1);

                        }
                    }).start();


                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("product",product1);
                    setResult(Activity.RESULT_OK, returnIntent);


                    finish();
                }




            }
        });

    }
}