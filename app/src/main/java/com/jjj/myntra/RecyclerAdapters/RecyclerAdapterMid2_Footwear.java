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

public class RecyclerAdapterMid2_Footwear extends RecyclerView.Adapter<RecyclerAdapterMid2_Footwear.MyViewHolder> {

    ArrayList<Modalclass> arrayListfoot_mid2;
    Context context;

    public RecyclerAdapterMid2_Footwear(Context context, ArrayList<Modalclass> arrayListfoot_mid2){

        this.arrayListfoot_mid2=arrayListfoot_mid2;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterMid2_Footwear.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_foot_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMid2_Footwear.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListfoot_mid2.get(position).getImg()).into(holder.image_foot);
        holder.textView_foot.setText(arrayListfoot_mid2.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return arrayListfoot_mid2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_foot;
        TextView textView_foot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_foot=itemView.findViewById(R.id.img_foot_mid2);
            textView_foot=itemView.findViewById(R.id.txt_foot_mid2);
        }
    }

   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/
}
