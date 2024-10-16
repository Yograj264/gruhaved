package com.logicalsolutions.gruhaved.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.logicalsolutions.gruhaved.R;
import com.logicalsolutions.gruhaved.model.MyAddres;

import java.util.ArrayList;

public  class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder>{
    private Context context;

    ArrayList<MyAddres>arrAddress;
    private AddressItemClick addressItemClick;
    public AddressAdapter(Context context, ArrayList<MyAddres> arrAddress) {
        this.context = context;
        this.arrAddress = arrAddress;

    }
    public interface AddressItemClick{
        public  void update(View v,int position);

    }
    public void setAddressItemClick(AddressItemClick addressItemClick){
        this.addressItemClick=addressItemClick;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_address_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

       MyAddres myaddress= arrAddress.get(position);
        holder.fullname_id.setText(myaddress.getFullName());
        holder.contact_id.setText(String.valueOf(myaddress.getContact()));
        holder.flatno_id.setText(myaddress.getFlatNo());
        holder.wing_id.setText(myaddress.getWing());
        holder.society_id.setText(myaddress.getSociety());
        holder.city_id.setText(myaddress.getCity());
        holder.pincode_id.setText(String.valueOf(myaddress.getPincode()));
        holder.item_addresslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressItemClick.update(view,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrAddress.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fullname_id,contact_id,flatno_id,wing_id,society_id,city_id,pincode_id;
        ConstraintLayout item_addresslist;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname_id=itemView.findViewById(R.id.listfullname);
            contact_id=itemView.findViewById(R.id.listcontact);
            flatno_id=itemView.findViewById(R.id.listflatNo);
            wing_id=itemView.findViewById(R.id.listwing);
            society_id=itemView.findViewById(R.id.listsociety);
            city_id=itemView.findViewById(R.id.listcity);
            pincode_id=itemView.findViewById(R.id.listpincodes);
            item_addresslist=itemView.findViewById(R.id.item_addresslist);
        }
    }
}
