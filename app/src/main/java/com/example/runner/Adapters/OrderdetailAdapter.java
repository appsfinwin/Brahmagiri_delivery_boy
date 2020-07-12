
package com.example.runner.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.runner.R;
import com.example.runner.Responses.LineItem;

import java.util.List;

public class OrderdetailAdapter extends RecyclerView.Adapter<OrderdetailAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<LineItem> productList;

    //getting the context and product list with constructor

    public OrderdetailAdapter(Context mCtx) {
        this.mCtx = mCtx;
    }

    public OrderdetailAdapter(Context mCtx, List<LineItem> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products_finalbill, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        LineItem product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getProductName());
        holder.textViewPrice.setText("₹ " + product.getPrice());
        holder.textViewQnty.setText("" + product.getQuantity());
        Double subtotal = product.getPrice() * product.getQuantity();
        holder.textViewSubtotal.setText("₹ " + subtotal);
        //holder.textViewRating.setText(String.valueOf(product.getRating()));
        // holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewQnty, textViewSubtotal, textViewPrice, textViewDelete;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.tv_prdct_name);
            textViewQnty = itemView.findViewById(R.id.tv_quantity);
            textViewSubtotal = itemView.findViewById(R.id.tv_subtotal);
            textViewPrice = itemView.findViewById(R.id.tv_cost);
            textViewDelete = itemView.findViewById(R.id.tv_delete);

            // imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

