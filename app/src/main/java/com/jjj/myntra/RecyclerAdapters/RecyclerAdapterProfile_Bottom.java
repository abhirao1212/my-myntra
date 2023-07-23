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
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterProfile_Bottom extends RecyclerView.Adapter<RecyclerAdapterProfile_Bottom.MyViewHolder> {

    ArrayList<Modalclass> arrayListbottom;
    Context context;

    public RecyclerAdapterProfile_Bottom(Context context, ArrayList<Modalclass> arrayListbottom){

        this.arrayListbottom=arrayListbottom;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerAdapterProfile_Bottom.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerprofile_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProfile_Bottom.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListbottom.get(position).getImg()).into(holder.imageprofilebottom);
        holder.titleprofilebottom.setText(arrayListbottom.get(position).getName());
        holder.titleprofilebottom2.setText(arrayListbottom.get(position).getName2());

      /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRecyclerClickListener.onclick(holder.getAdapterPosition());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayListbottom.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageprofilebottom;
        TextView titleprofilebottom,titleprofilebottom2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageprofilebottom=itemView.findViewById(R.id.profile_recyclerimg);
            titleprofilebottom=itemView.findViewById(R.id.profile_recyclertext);
            titleprofilebottom2=itemView.findViewById(R.id.profile_recyclertext2);

        }
    }
   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/

}