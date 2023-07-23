package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapter_Cart extends RecyclerView.Adapter<RecyclerAdapter_Cart.MyViewHolder> {

    ArrayList<Modalclass> arrayListcart;
    Context context;
    Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapter_Cart(Context context, ArrayList<Modalclass> arrayListcart){

        this.arrayListcart=arrayListcart;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter_Cart.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cart_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_Cart.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListcart.get(position).getImg()).into(holder.img);
        holder.title.setText(arrayListcart.get(position).getName());
        holder.des.setText(arrayListcart.get(position).getName2());
        holder.price.setText(arrayListcart.get(position).getName3());
        holder.pricecut.setText(arrayListcart.get(position).getName4());
        holder.offer.setText(arrayListcart.get(position).getName5());
        holder.delivery.setText(arrayListcart.get(position).getName6());
        holder.size.setText(arrayListcart.get(position).getName7());
        holder.cutcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recyclerclicklistener.onclick(holder.getAdapterPosition());

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListcart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img,cutcart;
        TextView title,des,price,pricecut,offer,delivery,size;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_rec_cart);
            title=itemView.findViewById(R.id.title_cart);
            des=itemView.findViewById(R.id.description_cart);
            price=itemView.findViewById(R.id.price_cart);
            pricecut=itemView.findViewById(R.id.cutprice_cart);
            offer=itemView.findViewById(R.id.offer_cart);
            delivery=itemView.findViewById(R.id.delivery_cart);
            size=itemView.findViewById(R.id.cart_size);

            cutcart=itemView.findViewById(R.id.cross_cart);

        }
    }

    public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }
}
