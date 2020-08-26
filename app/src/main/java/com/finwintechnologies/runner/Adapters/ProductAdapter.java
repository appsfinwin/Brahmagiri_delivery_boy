package com.finwintechnologies.runner.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.finwintechnologies.runner.Activities.OrderDetails;
import com.finwintechnologies.runner.R;
import com.finwintechnologies.runner.Responses.OrderToDeliver;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OrderToDeliver> OrderToDeliverList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<OrderToDeliver> OrderToDeliverList) {
        this.mCtx = mCtx;
        this.OrderToDeliverList = OrderToDeliverList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        OrderToDeliver product = OrderToDeliverList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText("Invoice No: " + product.getInvoiceId());
        holder.textViewAmount.setText("Amount :₹ " + product.getTotalAmount());
        holder.textViewConsumername.setText("Customer Name :"+product.getConsumerName());
        holder.textViewStatus.setText("Status :"+product.getStatus());
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

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAmount = itemView.findViewById(R.id.tvamount);
            textViewConsumername = itemView.findViewById(R.id.consumername);
            textViewStatus = itemView.findViewById(R.id.status);

            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    String id=OrderToDeliverList.get(pos).getInvoiceId().toString();
                    String billid=OrderToDeliverList.get(pos).getBillId().toString();
                    String status = OrderToDeliverList  .get(pos).getStatus();

                    if (status.equalsIgnoreCase("shipped")) {
                        mCtx.startActivity(new Intent(mCtx, OrderDetails.class).putExtra("id",id).putExtra("billid",billid));
                    }

                }
            });
        }
    }
}