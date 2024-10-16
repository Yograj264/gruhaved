package com.logicalsolutions.gruhaved.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.logicalsolutions.gruhaved.R;
import com.logicalsolutions.gruhaved.db.DatabaseHelper;
import com.logicalsolutions.gruhaved.model.MyAddres;

import java.util.ArrayList;


public class AddressActivity extends AppCompatActivity implements AddressAdapter.AddressItemClick{
    AddressAdapter adapter;
    ArrayList<MyAddres> Addresslist;
    Integer AddressID=0;
    private EditText FullName, Contact, FlatNo, Wing, Society, City, Pincode;
    private RecyclerView Rvaddress;
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // Initialize EditText fields
        FullName = findViewById(R.id.editfullname);
        Contact = findViewById(R.id.editcontact);
        FlatNo = findViewById(R.id.editflatNo);
        Wing = findViewById(R.id.editwing);
        Society = findViewById(R.id.editsociety);
        City = findViewById(R.id.editcity);
        Pincode = findViewById(R.id.editpincode);
        Rvaddress=findViewById(R.id.recycleviewAddress);


        // Set up the save button
        Button save = findViewById(R.id.addressSaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = FullName.getText().toString().trim();
                String contacts = Contact.getText().toString().trim();
                String flatno = FlatNo.getText().toString().trim();
                String wing = Wing.getText().toString().trim();
                String society = Society.getText().toString().trim();
                String city = City.getText().toString().trim();
                String pincodes = Pincode.getText().toString().trim();
                int pincode = (int) Long.parseLong(pincodes);

                DatabaseHelper addressdb= new DatabaseHelper(AddressActivity.this);
                if(AddressID!=0){
                    addressdb.update(AddressID,fullname,contacts,flatno,wing,society,city,pincode);
                }
                else{
                    addressdb.InsertAddress(fullname,contacts,flatno,wing,society,city,pincode);
                }
                ResetFields();
                displaydata();
            }
        });
        Button back = findViewById(R.id.addressBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddressActivity.this,OrderActivity.class);
                startActivity(intent);
                finish(); }
        });


        displaydata();

    }

    private void ResetFields() {
        // Reset input fields
        FullName.setText("");
        Contact.setText("");
        FlatNo.setText("");
        Wing.setText("");
        Society.setText("");
        City.setText("");
        Pincode.setText("");
    }
    public void displaydata() {
        // Get an instance of DatabaseHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Call the getdata() method on the instance
        Cursor cursor = dbHelper.getaddresslistdata();
        Addresslist=new ArrayList<>();
        if (cursor.getCount() == 0) {
            Toast.makeText(AddressActivity.this, "No Address Exist", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Integer addressID=cursor.getInt(0);
                String fullName=cursor.getString(1);
                String contact=cursor.getString(2);
                String flatNo=cursor.getString(3);
                String wing=cursor.getString(4);
                String society=cursor.getString(5);
                String city=cursor.getString(6) ;
                Integer pincode=cursor.getInt(7);
                MyAddres address=new MyAddres(
                    addressID,fullName,contact,flatNo,wing,society,city,pincode);
                    Addresslist.add(address);
            }
        }
        adapter= new AddressAdapter(this,Addresslist);
        Rvaddress.setAdapter(adapter);
        Rvaddress.setLayoutManager(new GridLayoutManager(this,2));
        adapter.setAddressItemClick(AddressActivity.this);
    }
    @Override
    public void update(View v, int position) {
        MyAddres myAddress =Addresslist.get(position);
        AddressID = myAddress.getAddressID();
       FullName.setText(myAddress.getFullName());
       Contact.setText(myAddress.getContact());
       FlatNo.setText(myAddress.getFlatNo());
       Wing.setText(myAddress.getWing());
       Society.setText(myAddress.getSociety());
       City.setText(myAddress.getCity());
       Pincode.setText(myAddress.getPincode()+"");
       // Toast.makeText(AddressActivity.this,myAddress.getFullName(),Toast.LENGTH_SHORT).show();
    }


}
