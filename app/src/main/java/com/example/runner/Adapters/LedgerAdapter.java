package com.example.runner.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.runner.Activities.OrderDetails;
import com.example.runner.R;
import com.example.runner.Responses.Ledger;
import com.example.runner.Responses.OrderToDeliver;

import java.util.List;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Ledger> OrderToDeliverList;

    //getting the context and product list with constructor
    public LedgerAdapter(Context mCtx, List<Ledger> OrderToDeliverList) {
        this.mCtx = mCtx;
        this.OrderToDeliverList = OrderToDeliverList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_ledger, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Ledger product = OrderToDeliverList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText("Invoice No: " + product.getOutlet());
        holder.textViewAmount.setText("Amount : ₹ " + product.getClBalance());

        //   holder.textViewRating.setText(String.valueOf(product.getRating()));
        //    holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        //   holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }


    @Override
    public int getItemCount() {
        return OrderToDeliverList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewAmount, textViewConsumername, textViewStatus;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.run_outname);
            textViewAmount = itemView.findViewById(R.id.run_closingbalance);


        }
    }
}