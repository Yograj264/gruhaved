package com.logicalsolutions.gruhaved.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.logicalsolutions.gruhaved.R;
import com.logicalsolutions.gruhaved.db.DatabaseHelper;

import java.io.PrintStream;

public class OrderActivity extends AppCompatActivity {

    private EditText editTextName, editTextFlatNo, editTextWing, editTextSociety, editTextOrder, editTextContact;
       public DatabaseHelper DatabaseHelper =new DatabaseHelper(this);
    private PrintStream writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize buttons
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button submitButton = findViewById(R.id.submitButton);
        Button Back= findViewById(R.id.orderBackButton);
        Button Address= findViewById(R.id.addAddressBtn);


        // Initialize DatabaseHelper
        DatabaseHelper = new DatabaseHelper(this);


        // Set onClick listener for submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOrder();  // Save order to database
                resetFields();  // Reset input fields
            }
        });

        // Set onClick listener for order visit button
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

      Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this,AddressActivity.class);
                startActivity(intent);
                finish();  // Display order details (implement as needed)
            }
        });
    }

    private void saveOrder() {
        // Get values from input fields

        Integer AddressID=0;
        String OrderDate="";
        String OrderStatus="";
        Integer OrderQty=0;
        String OrderTotal="";

        // Insert order into database
        boolean isInserted = DatabaseHelper.insertOrder( OrderDate,AddressID, OrderStatus,OrderQty,OrderTotal);
        // Show a toast message based on insertion result
        if (isInserted) {
            Toast.makeText(this, "Order saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Order not saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetFields() {
        // Reset input fields
        editTextName.setText("");
        editTextFlatNo.setText("");
        editTextWing.setText("");
        editTextSociety.setText("");
        editTextOrder.setText("");
        editTextContact.setText("");
    }

    private void displayOrder() {
       // System.out.println("Hello");
        writer.println("Hello");
        // Add logic to display order details as needed
    }
}
