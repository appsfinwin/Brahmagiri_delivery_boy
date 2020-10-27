package com.finwintechnologies.deltracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.finwintechnologies.deltracker.Activities.OrderDetails;
import com.finwintechnologies.deltracker.R;
import com.finwintechnologies.deltracker.Responses.OrderToDeliver;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<OrderToDeliver> mResults;
    private Context context;

    private boolean isLoadingAdded = false;

    public MyOrdersAdapter(Context context) {
        this.context = context;
        mResults = new ArrayList<>();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.layout_products, parent, false);
        viewHolder = new OrderVH(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        OrderToDeliver lists = mResults.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                final OrderVH orderVH = (OrderVH) holder;


                orderVH.textViewTitle.setText("Invoice No: " + lists.getInvoiceNo());
                orderVH.textViewAmount.setText("Amount :₹ " + lists.getTotalAmount());
                orderVH.textViewConsumername.setText("Customer Name :"+lists.getConsumerName());
                orderVH.textViewStatus.setText("Status :"+lists.getOrderStatus());
                orderVH.textViewOutname.setText("Outlet Name :"+lists.getOutlet());
                orderVH.textviewdate.setText("Date : "+lists.getInvoiceDate());




                break;

            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mResults == null ? 0 : mResults.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mResults.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(OrderToDeliver r) {
        mResults.add(r);
        notifyItemInserted(mResults.size() - 1);
    }

    public void addAll(List<OrderToDeliver> moveResults) {
        for (OrderToDeliver result : moveResults) {
            add(result);
        }
    }

    public void remove(OrderToDeliver r) {
        int position = mResults.indexOf(r);
        if (position > -1) {
            mResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new OrderToDeliver());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mResults.size() - 1;
        OrderToDeliver result = getItem(position);

        if (result != null) {
            mResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public OrderToDeliver getItem(int position) {
        return mResults.get(position);
    }


   /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */
    protected class OrderVH extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewAmount, textViewConsumername, textViewStatus,textViewOutname,textviewdate;
        ImageView imageView;
        public OrderVH(View view) {

            super(view);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewAmount = itemView.findViewById(R.id.tvamount);
            textViewConsumername = itemView.findViewById(R.id.consumername);
            textViewStatus = itemView.findViewById(R.id.status);
            textViewOutname = itemView.findViewById(R.id.textViewOutname);
            textviewdate = itemView.findViewById(R.id.date);


            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    String id=mResults.get(pos).getInvoiceId().toString();
                    String billid=mResults.get(pos).getBillId().toString();
                    String status = mResults  .get(pos).getStatus();

                    String consumerName=mResults.get(pos).getConsumerName();
                    String Consumermob=mResults.get(pos).getConsumerMobile();
                    String consumerAdderss=mResults.get(pos).getConsumerDeliveryLoc()+
                            " , \n "+mResults.get(pos).getConsumerDeliveryLandmark();


                    if (status.equalsIgnoreCase("shipped")||status.equalsIgnoreCase("assigned")||status.equalsIgnoreCase("undelivered")) {
                        context.startActivity(new Intent(context, OrderDetails.class)
                                .putExtra("id",id)
                                .putExtra("consumerName",consumerName)
                                .putExtra("Consumermob",Consumermob)
                                .putExtra("consumerAdderss",consumerAdderss)
                                .putExtra("status",status)
                                .putExtra("billid",billid));
                    }

                }
            });
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


}