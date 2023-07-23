package com.jjj.myntra.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor_p2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product_Sec_Fragment extends Fragment {

    ArrayList<Modalclass> arrayListslider_p2,arrayListad,arrayListmid,arrayListslider;
    RequestQueue requestQueue;

    ImageView img_cart;

    TextView txt_p1,txt_p2,txt_p3,txt_p4,txt_p5,txt_p6,txt_p7,txt_p8,txt_p9,txt_p10,txt_p11,txt_p12,txt_p13,size,measure,address;
    ViewPager2 viewPagerp;
    Runnable sliderRunnable;
    private Handler sliderHandler = new Handler();
    Button cart;

    //Radio Button
    RadioButton r1;
    RadioGroup radioGroup;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";
    private static final String KEY_ADDRESS = "address";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product__sec_, container, false);

        sharedPreferences= getContext().getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String Address=sharedPreferences.getString(KEY_ADDRESS,null);

        txt_p1=view.findViewById(R.id.text_product2);
        txt_p2=view.findViewById(R.id.text_product3);
        txt_p3=view.findViewById(R.id.cutprice_product2);
        txt_p4=view.findViewById(R.id.price_product2);
        txt_p5=view.findViewById(R.id.offer_product2);

        txt_p11=view.findViewById(R.id.sizechart_product2);
        txt_p12=view.findViewById(R.id.view_product);

        txt_p6=view.findViewById(R.id.bannertxt_product2);
        txt_p7=view.findViewById(R.id.date_s);
        txt_p8=view.findViewById(R.id.price_s);
        txt_p9=view.findViewById(R.id.cutprice_s);
        txt_p10=view.findViewById(R.id.offer_s);
        txt_p13=view.findViewById(R.id.detail_product2);

        cart=view.findViewById(R.id.cart_btn);

        //Address
        address=view.findViewById(R.id.address_product2);
        address.setText(Address);

        viewPagerp=view.findViewById(R.id.img_slider_product2);

        Bundle bundle=this.getArguments();
        String name=bundle.getString("product_name");
        String name2=bundle.getString("product_name2");
        String name3=bundle.getString("product_name3");
        String name4=bundle.getString("product_name4");
        String name5=bundle.getString("product_name5");
        String imgc=bundle.getString("imgp");

        txt_p1.setText(name);
        txt_p2.setText(name2);
        txt_p3.setText(name3);
        txt_p4.setText(name4);
        txt_p5.setText(name5);
        txt_p8.setText(name4);
        txt_p9.setText(name3);
        txt_p10.setText(name5);
        txt_p13.setText(name2);

        //Radio Button

        size=view.findViewById(R.id.size_product2);
        measure=view.findViewById(R.id.measure_product2);

        radioGroup=view.findViewById(R.id.radiogrp);
        r1=view.findViewById(R.id.radio1);

        r1.setChecked(true);
        size.setText(r1.getText().toString());
        measure.setText("Chest 39.0in");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio1:
                        size.setText("S");
                        measure.setText("Chest 39.0in");
                        break;

                    case R.id.radio2:
                        size.setText("M");
                        measure.setText("Chest 41.0in");
                        break;

                    case R.id.radio3:
                        size.setText("L");
                        measure.setText("Chest 43.0in");
                        break;

                    case R.id.radio4:
                        size.setText("XL");
                        measure.setText("Chest 45.0in");
                        break;
                    }
                }
            });

        //For Size Chart
        txt_p11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.profile_container_main,new SizeFragment()).addToBackStack(null).commit();

            }
        });

        //For Emi Chart
        txt_p12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.profile_container_main,new EmiFragment() ).addToBackStack(null).commit();

            }
        });

        String cid_p=bundle.getString("key_product");

        //For First Product
           // image slider
            arrayListslider_p2 = new ArrayList<>();
            requestQueue = Volley.newRequestQueue(getContext());

            String url = "http://api.karanvarma.link/Webservice1.asmx/product2api?id="+cid_p;
            StringRequest stringRequestslider_p2 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("pimg");

                            modalclass.setImg(image);

                            String ID=jsonObject.getString("id");
                            modalclass.setId(ID);

                            viewPagerp.setAdapter(new ImageSliderAdaptor_p2(viewPagerp, arrayListslider_p2));
                            arrayListslider_p2.add(modalclass);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(stringRequestslider_p2);

            viewPagerp.setClipToPadding(false);
            viewPagerp.setClipChildren(false);
            viewPagerp.setOffscreenPageLimit(3);
            viewPagerp.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(20));
            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });
            sliderRunnable = new Runnable() {
                @Override
                public void run() {
                    viewPagerp.setCurrentItem(viewPagerp.getCurrentItem() + 1);
                }
            };
            viewPagerp.setPageTransformer(compositePageTransformer);
            viewPagerp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandler.removeCallbacks(sliderRunnable);
                    sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
                }
            });


        //For Banner Text
        StringRequest stringRequesttextp = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/bannerapi?id="+cid_p, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    String txt = jsonArray.getJSONObject(0).getString("bname");
                    txt_p6.setText(txt);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequesttextp);

        //For Delivery Text
        StringRequest stringRequestdeliveryp = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/deliveryapi?id="+cid_p, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    String txt2 = jsonArray.getJSONObject(0).getString("dname");
                    txt_p7.setText(txt2);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestdeliveryp);

        //Add To Cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(getContext());

                String ctitle=txt_p1.getText().toString();
                String cdes=txt_p2.getText().toString();
                String ccutp=txt_p3.getText().toString();
                String cprice=txt_p4.getText().toString();
                String coffer=txt_p5.getText().toString();
                String cdelivery=txt_p7.getText().toString();

                String csize=size.getText().toString();
           //   String cmeasure=measure.getText().toString();

                SharedPreferences.Editor editor=sharedPreferences.edit();
                String id = sharedPreferences.getString(KEY_ID,null);
                editor.putString(KEY_ADDRESS,Address);
                editor.commit();

                String cart = "http://api.karanvarma.link/Webservice1.asmx/cartapi?n="+ctitle+"&i="+imgc+"&d="+cdes+"&pc="+ccutp+"&p="+cprice+"&o="+coffer+"&b="+cdelivery+"&s="+csize+"&userid="+id;
                StringRequest stringRequestcart = new StringRequest(Request.Method.GET, cart, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getContext(), "Added To Cart", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequestcart);
            }
        });

        return view;
    }

}