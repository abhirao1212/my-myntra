package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapter_Product extends RecyclerView.Adapter<RecyclerAdapter_Product.MyViewHolder> {

    ArrayList<Modalclass> arrayListproduct;
    Context context;
    Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapter_Product(Context context, ArrayList<Modalclass> arrayListproduct){

        this.arrayListproduct=arrayListproduct;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter_Product.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerproduct_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_Product.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListproduct.get(position).getImg()).into(holder.imageproduct);
        holder.ptxt1.setText(arrayListproduct.get(position).getName());
        holder.ptxt2.setText(arrayListproduct.get(position).getName2());
        holder.ptxt3.setText(arrayListproduct.get(position).getName3());
        holder.ptxt4.setText(arrayListproduct.get(position).getName4());
        holder.ptxt5.setText(arrayListproduct.get(position).getName5());
        holder.ptxt6.setText(arrayListproduct.get(position).getName6());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListproduct.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageproduct;
        TextView ptxt1,ptxt2,ptxt3,ptxt4,ptxt5,ptxt6;
        ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageproduct=itemView.findViewById(R.id.img_product);
            ptxt1=itemView.findViewById(R.id.text_product);
            ptxt2=itemView.findViewById(R.id.description_product);
            ptxt3=itemView.findViewById(R.id.cutprice_product);
            ptxt4=itemView.findViewById(R.id.price_product);
            ptxt5=itemView.findViewById(R.id.offer_product);
            ptxt6=itemView.findViewById(R.id.bannertxt_product);

            layout=itemView.findViewById(R.id.parent_product);



        }
    }

    public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }
}
