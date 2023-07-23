package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterTop_Gadgets extends RecyclerView.Adapter<RecyclerAdapterTop_Gadgets.MyViewHolder> {

    ArrayList<Modalclass> arrayListgad;
    Context context;
    Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapterTop_Gadgets(Context context, ArrayList<Modalclass> arrayListgad){

        this.arrayListgad=arrayListgad;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterTop_Gadgets.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recgad_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTop_Gadgets.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListgad.get(position).getImg()).into(holder.image_gad);
        holder.title_gad.setText(arrayListgad.get(position).getName());
       // holder.cid.setText(arrayListmen.get(position).getId());

        holder.image_gad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListgad.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_gad;
        TextView title_gad;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_gad=itemView.findViewById(R.id.img_subhome_gad);
            title_gad=itemView.findViewById(R.id.txt_subhome_gad);
           // cid = itemView.findViewById(R.id.sid);
        }
    }

    public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }
}
