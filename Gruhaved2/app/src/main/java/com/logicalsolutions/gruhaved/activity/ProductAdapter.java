package com.logicalsolutions.gruhaved.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.logicalsolutions.gruhaved.R;
import com.logicalsolutions.gruhaved.model.MyProduct;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<MyProduct> arrProduct;
    private ProductItemClick productItemClick;

    public ProductAdapter(Context context, ArrayList<MyProduct> arrProduct) {
        this.context = context;
        this.arrProduct = arrProduct;
    }

    // Define the interface without explicit 'public'
    public interface ProductItemClick {
        void update(View v, int position);
    }

    public void setProductItemClick(ProductItemClick productItemClick) {
        this.productItemClick = productItemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyProduct myProduct = arrProduct.get(position);

        // Set product name and price unit in TextViews
        holder.productname_id.setText(myProduct.getProductName());
        holder.priceunit_id.setText(String.valueOf(myProduct.getPriceUnit()));
        // holder.product_img.setText(myProduct.getProductImg());

       // Spinner Setup
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                context,
                R.array.Unit, // Assuming this is your array of product units
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter for spinner
        holder.productunit_id.setAdapter(adapter);

        // Set the spinner selection based on the product unit from MyProduct
        if (myProduct.getProductUnit() != null) {
            int spinnerPosition = adapter.getPosition(myProduct.getProductUnit());
            holder.productunit_id.setSelection(spinnerPosition);
        }

        // Handle item click if productItemClick is not null
        holder.productlist.setOnClickListener(view -> {
            if (productItemClick != null) {
                productItemClick.update(view, position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView productname_id, priceunit_id; // These are TextViews
        Spinner productunit_id; // This is a Spinner
        ConstraintLayout productlist;

        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productname_id = itemView.findViewById(R.id.listProductName); // Correctly cast to TextView
            priceunit_id = itemView.findViewById(R.id.listpriceunit); // Correctly cast to TextView
            productunit_id = itemView.findViewById(R.id.listproductUnit); // Correctly cast to Spinner
            //product_img = itemView.findViewById(R.id.productimg); // Correctly cast to TextView
            productlist = itemView.findViewById(R.id.item_productlist); // Correctly cast to ConstraintLayout
        }
    }

}
