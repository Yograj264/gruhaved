package com.logicalsolutions.gruhaved.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.logicalsolutions.gruhaved.R;
import com.logicalsolutions.gruhaved.db.DatabaseHelper;
import com.logicalsolutions.gruhaved.model.MyProduct;

import java.util.ArrayList;

public class ProductMasterActivity extends AppCompatActivity implements ProductAdapter.ProductItemClick {
    private static final int PICK_IMAGE = 1;
    ProductAdapter padapter;
    ArrayList<MyProduct> ProductList;
    Integer ProductID = 0;
    private EditText ProductName, PriceUnit;
    Spinner ProductUnit;
    private RecyclerView RvProduct;
    private ImageView imgProduct;
     ActivityResultLauncher<Intent> resultLauncher;

    //private String imagePath = "";  // Variable to store the selected image path

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_master);

        ProductName = findViewById(R.id.editproductname);
        PriceUnit = findViewById(R.id.editpriceunit);
        ProductUnit = findViewById(R.id.editproductunit);
        RvProduct = findViewById(R.id.recycleviewProduct);
        imgProduct = findViewById(R.id.imgProduct);
        imgProduct.setOnClickListener(view -> pickImage());
        Button save = findViewById(R.id.productSaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = ProductName.getText().toString().trim();
                String priceunit = PriceUnit.getText().toString().trim();
                int priceUnit = (int) Long.parseLong(priceunit);
                String productUnit = ProductUnit.getSelectedItem().toString().trim();

                DatabaseHelper productdb = new DatabaseHelper(ProductMasterActivity.this);
                if (ProductID != 0) {
                    productdb.update(ProductID, productName, priceUnit, productUnit);
                } else {
                    productdb.insertProduct(productName, priceUnit, productUnit);
                }
                ResetFields();
                displaydata();
            }
        });

        Button back = findViewById(R.id.productbackbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductMasterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        displaydata();
    }
    private void pickImage()
    {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }
    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try
                        {
                            Uri imageUri = result.getData().getData();
                            imgProduct.setImageURI(imageUri);
                        }catch(Exception e)
                        {
                            Toast.makeText(ProductMasterActivity.this,"No Img Selct",Toast.LENGTH_SHORT).show();

                        }}});}

    private void displaydata() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Call the getdata() method on the instance
        Cursor cursor = dbHelper.getproductdata();
        ProductList = new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(ProductMasterActivity.this, "No Product Exist", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Integer ProductID = cursor.getInt(0);
               // String ProductImg = cursor.getString(1);
                String ProductName = cursor.getString(2);
                Integer PriceUnit = cursor.getInt(3);
                String ProductUnit = cursor.getString(4);
                MyProduct product = new MyProduct(ProductID, ProductName, PriceUnit, ProductUnit);
                ProductList.add(product);
            }
        }
        padapter = new ProductAdapter(this, ProductList);
        RvProduct.setAdapter(padapter);
        RvProduct.setLayoutManager(new GridLayoutManager(this, 2));
        padapter.setProductItemClick(ProductMasterActivity.this);
    }

    private void ResetFields() {
        ProductName.setText("");
        PriceUnit.setText("");
        ProductUnit.setSelection(0);
       // imgProduct.setImageResource(R.drawable.banner); // Reset to placeholder image
    }

    @Override
    public void update(View v, int position) {
        MyProduct myproduct = ProductList.get(position);
        ProductID = myproduct.getProductID();
        ProductName.setText(myproduct.getProductName());
        PriceUnit.setText(myproduct.getPriceUnit() + "");
        ProductUnit.setSelection(position);

     /*   // Set the image from the path if available
        imagePath = myproduct.getProductImg();
        if (imagePath != null && !imagePath.isEmpty()) {
            imgProduct.setImageURI(Uri.parse(imagePath));
        }
*/
        //Pincode.setText(myAddress.getPincode()+"");
        // Toast.makeText(AddressActivity.this,myAddress.getFullName(),Toast.LENGTH_SHORT).show();
    }
}
