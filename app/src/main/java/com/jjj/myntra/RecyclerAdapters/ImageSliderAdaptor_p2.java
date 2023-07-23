package com.jjj.myntra.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.List;

public class ImageSliderAdaptor_p2 extends RecyclerView.Adapter<ImageSliderAdaptor_p2.MyViewHolder> {

    private ViewPager2 viewPagerp;
    List<Modalclass> arraylistslider_p2;

    public ImageSliderAdaptor_p2(ViewPager2 viewPagerp, List<Modalclass>arraylistslider_p2){

        this.viewPagerp = viewPagerp;
        this.arraylistslider_p2=arraylistslider_p2;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.imgslider_design_p,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(viewPagerp).load(arraylistslider_p2.get(position).getImg()).into(holder.slider_image);

        if (position == arraylistslider_p2.size()- 2){
            viewPagerp.post(runnable);
        }

    }

    @Override
    public int getItemCount() {return arraylistslider_p2.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView slider_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            slider_image=itemView.findViewById(R.id.slider_p);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            arraylistslider_p2.addAll(arraylistslider_p2);
            notifyDataSetChanged();
        }
    };
}
