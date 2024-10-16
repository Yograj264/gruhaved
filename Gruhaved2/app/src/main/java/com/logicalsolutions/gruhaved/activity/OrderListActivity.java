package com.logicalsolutions.gruhaved.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.logicalsolutions.gruhaved.R;

public class OrderListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_order_list);
        Button Back= findViewById(R.id.orderlistBack);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Display order details (implement as needed)
            }
        });


    }
}
