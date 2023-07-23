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

public class RecyclerAdapterProfile_Top extends RecyclerView.Adapter<RecyclerAdapterProfile_Top.MyViewHolder> {

    ArrayList<Modalclass> arrayListtop;
    Context context;


    public RecyclerAdapterProfile_Top(Context context, ArrayList<Modalclass> arrayListtop){

        this.arrayListtop=arrayListtop;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerAdapterProfile_Top.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerprofile_design_top,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProfile_Top.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListtop.get(position).getImg()).into(holder.imageprofiletop);
        holder.titleprofiletop.setText(arrayListtop.get(position).getName());
       // holder.titleprofiletop2.setText(arrayListtop.get(position).getName2());

      /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRecyclerClickListener.onclick(holder.getAdapterPosition());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayListtop.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageprofiletop;
        TextView titleprofiletop;
        CardView cardViewprofiletop;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageprofiletop=itemView.findViewById(R.id.profile_recyclerimg_top);
            titleprofiletop=itemView.findViewById(R.id.profile_recyclertext_top);
            //titleprofiletop2=itemView.findViewById(R.id.profile_recyclertext2);
            cardViewprofiletop=itemView.findViewById(R.id.carddemo);
        }
    }
   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/

}