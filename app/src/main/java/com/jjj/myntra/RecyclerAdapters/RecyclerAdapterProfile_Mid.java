package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterProfile_Mid extends RecyclerView.Adapter<RecyclerAdapterProfile_Mid.MyViewHolder> {

    ArrayList<Modalclass> arrayListmid;
    Context context;


    public RecyclerAdapterProfile_Mid(Context context, ArrayList<Modalclass> arrayListmid){

        this.arrayListmid=arrayListmid;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerAdapterProfile_Mid.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerprofile_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProfile_Mid.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListmid.get(position).getImg()).into(holder.imageprofilemid);
        holder.titleprofilemid.setText(arrayListmid.get(position).getName());
        holder.titleprofilemid2.setText(arrayListmid.get(position).getName2());

      /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRecyclerClickListener.onclick(holder.getAdapterPosition());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayListmid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageprofilemid;
        TextView titleprofilemid,titleprofilemid2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageprofilemid=itemView.findViewById(R.id.profile_recyclerimg);
            titleprofilemid=itemView.findViewById(R.id.profile_recyclertext);
            titleprofilemid2=itemView.findViewById(R.id.profile_recyclertext2);

        }
    }
   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/

}