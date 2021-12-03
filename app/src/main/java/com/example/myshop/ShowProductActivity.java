package com.example.myshop;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myshop.dao.DataBaseRoom;
import com.example.myshop.dao.ProductRoomDao;
import com.example.myshop.databinding.ActivityShowProductBinding;
import com.example.myshop.entity.Product;
import com.google.android.material.textfield.TextInputEditText;
public class ShowProductActivity extends AppCompatActivity {
    ActivityShowProductBinding binding;
    Product product;
    TextView nameTextView;
    TextView descriptionTextView;
    TextView priceTextView;
    TextView stockQuantityTextView;
    TextView stockAlertTextView;
    ProductRoomDao productRoomDao;
    boolean isModify =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        productRoomDao= DataBaseRoom.getInstance(ShowProductActivity.this).productRoomDao();
        binding = ActivityShowProductBinding.inflate(getLayoutInflater());
        nameTextView = findViewById(R.id.name);
        descriptionTextView =findViewById(R.id.description);
        priceTextView = findViewById(R.id.price);
        stockQuantityTextView =findViewById(R.id.stock_quantity);
        stockAlertTextView= findViewById(R.id.stock_alert);
        product = (Product) getIntent().getExtras().get("product");
        fillTextView(product);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
            menuInflater.inflate(R.menu.menu_show_product,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(isModify) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("product", product);
            returnIntent.putExtra("modify",true);
            setResult(Activity.RESULT_OK, returnIntent);
        }

        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_item:

                productToDelete();
                return true;

            case R.id.modify_item:
                modifyProduct();


                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void productToDelete(){
        new Thread(new Runnable() {
            @Override
            public void run() {
              Room.getDao(getApplicationContext()).delete(product);
            }
        }).start();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("product",product);
        returnIntent.putExtra("modify",false);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }


    ActivityResultLauncher<Intent> addProductLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==Activity.RESULT_OK){
                        Intent intent =result.getData();
                        Product product1 = (Product) intent.getExtras().get("product");

                        product=product1;
                        fillTextView(product1);
                        isModify=true;




                    }
                }
            }
    );

    public  void  modifyProduct(){
        Intent intent  = new Intent(ShowProductActivity.this,EditProductActivity.class);
        intent.putExtra("product",product);
        addProductLaunch.launch(intent);
    }


    public void fillTextView(Product product1){

        nameTextView.setText(product1.getName());
        descriptionTextView.setText(product1.getDescription());
        priceTextView.setText(String.format("%,.2f",product1.getPrice())+" Fcfa");
        stockQuantityTextView.setText((product1.getStockQuantity()>1? product1.getStockQuantity()+" disponibles" : product1.getStockQuantity()+" disponibles"));
        stockAlertTextView.setText("La quantité Alerte est "+product1.getStockAlert());
    }
}
