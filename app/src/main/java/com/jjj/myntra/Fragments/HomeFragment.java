package com.jjj.myntra.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Top;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_ad;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_mid;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<Modalclass> arrayList,arrayListad,arrayListmid,arrayListslider;
    RequestQueue requestQueue;

    RecyclerView recycler_top,recycler_ad,recycler_mid;
    RecyclerAdapter_Top adapter_top;
    RecyclerAdapter_ad adapter_ad;
    RecyclerAdapter_mid adapter_mid;

    ImageView img1,img2,img3,gifimg4,img5,gifimg6;

    //Click Listener
   // Recyclerclicklistener recyclerclicklistener;

    //Image Slider
    ViewPager2 viewPager;
    Runnable sliderRunnable;
    private Handler sliderHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recycler_top=view.findViewById(R.id.recycler_top);
        recycler_ad=view.findViewById(R.id.recycler_ad);
        recycler_mid=view.findViewById(R.id.recycler_mid);
        viewPager = view.findViewById(R.id.img_slider);

        requestQueue= Volley.newRequestQueue(getContext());
        arrayList=new ArrayList<>();
        arrayListad=new ArrayList<>();
        arrayListmid=new ArrayList<>();


        // register the extended floating action Button
        final ExtendedFloatingActionButton extendedFloatingActionButton = view.findViewById(R.id.extFloatingActionButton);

        // register the nestedScrollView from the main layout
        NestedScrollView nestedScrollView = view.findViewById(R.id.nestedscrollview_home);

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "XPLORE", Toast.LENGTH_SHORT).show();
            }
        });

        // handle the nestedScrollView behaviour with OnScrollChangeListener
        // to extend or shrink the Extended Floating Action Button
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // the delay of the extension of the FAB is set for 12 items
                if (scrollY > oldScrollY + 12 && extendedFloatingActionButton.isExtended()) {
                    extendedFloatingActionButton.shrink();
                }

                // the delay of the extension of the FAB is set for 12 items
                if (scrollY < oldScrollY - 12 && !extendedFloatingActionButton.isExtended()) {
                    extendedFloatingActionButton.extend();
                }

                // if the nestedScrollView is at the first item of the list then the
                // extended floating action should be in extended state
                if (scrollY == 0) {
                    extendedFloatingActionButton.extend();
                }
            }
        });



        // For Recycler View First
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/categoryapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("Cname");
                        modalclass.setName(name);

                        String ID=jsonObject.getString("id");
                        modalclass.setId(ID);

                        String img="http://api.karanvarma.link/upload/"+jsonObject.getString("Cimg");
                        modalclass.setImg(img);
                        arrayList.add(modalclass);
                        // For Recycler View First
                        adapter_top=new RecyclerAdapter_Top(getContext(),arrayList);
                        recycler_top.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                        recycler_top.setAdapter(adapter_top);

                        // Click listener
                        adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {
                                SubHomeFragment subHomeFragment=new SubHomeFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("key",""+arrayList.get(pos).getId());
                                subHomeFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.profile_container_main,subHomeFragment).addToBackStack(null).commit();

                            }
                        });


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);


        // For Recycler View Ad
        StringRequest stringRequestad=new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/categoryadapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String cadimg="http://api.karanvarma.link/upload/"+jsonObject.getString("cimg");
                        modalclass.setImg(cadimg);
                        arrayListad.add(modalclass);

                        adapter_ad =new RecyclerAdapter_ad(getContext(),arrayListad);
                        recycler_ad.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                        recycler_ad.setAdapter(adapter_ad);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestad);

        // For Recycler View Mid
        StringRequest stringRequestmid=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/categorymidapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String cmidimg="http://api.karanvarma.link/upload/"+jsonObject.getString("cimg");
                        modalclass.setImg(cmidimg);
                        arrayListmid.add(modalclass);
                        // For Recycler View First
                        adapter_mid =new RecyclerAdapter_mid(getContext(),arrayListmid);
                        recycler_mid.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                        recycler_mid.setAdapter(adapter_mid);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestmid);

        // For View Pager
        /*ArrayList<CarouselItem> banner =new ArrayList<>();
        banner.add(new CarouselItem("https://images-eu.ssl-images-amazon.com/images/G/31/img19/laptops/750x450-1_1545999822._CB457223703_.jpg",""));
        banner.add(new CarouselItem("https://economictimes.indiatimes.com/thumb/msid-94677060,width-1200,height-900,resizemode-4,imgsize-37878/best-laptops-and-tablets-at-huge-discounts-in-amazon-sale-today.jpg?from=mdr",""));
        banner.add(new CarouselItem("https://cdn.mos.cms.futurecdn.net/DnrckHRgetDkkD6hJMoA2K.jpg",""));
        banner.add(new CarouselItem("https://www.grabon.in/indulge/wp-content/uploads/2022/06/Upcoming-Amazon-Sales-in-India.jpg",""));

        carousel.setData(banner);*/

        /// image slider
        arrayListslider = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getContext());

        String url = "http://api.karanvarma.link/Webservice1.asmx/categorysliderapi";
        StringRequest stringRequestslider = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Modalclass modalclass = new Modalclass();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("cimg");

                        modalclass.setImg(image);
                        viewPager.setAdapter(new ImageSliderAdaptor(viewPager, arrayListslider));
                        arrayListslider.add(modalclass);

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
        requestQueue.add(stringRequestslider);


        // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


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
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        };


        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });


        //For Multi Images
        img1=view.findViewById(R.id.img1);
        img2=view.findViewById(R.id.img2);
        img3=view.findViewById(R.id.img3);
        gifimg4=view.findViewById(R.id.imggif4);
        img5=view.findViewById(R.id.img5);
        gifimg6=view.findViewById(R.id.imggif6);


        StringRequest stringRequestmulti = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/imagesmultiapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (getActivity() == null) {
                    return;
                }

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    String imag1 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(0).getString("iimg");
                    Glide.with(getContext()).load(imag1).into(img1);

                    String imag2 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(1).getString("iimg");
                    Glide.with(getContext()).load(imag2).into(img2);

                    String imag3 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(2).getString("iimg");
                    Glide.with(getContext()).load(imag3).into(img3);

                    String imag4 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(3).getString("iimg");
                    Glide.with(getContext()).load(imag4).into(gifimg4);

                    String imag5 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(4).getString("iimg");
                    Glide.with(getContext()).load(imag5).into(img5);

                    String imag6 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(5).getString("iimg");
                    Glide.with(getContext()).load(imag6).into(gifimg6);


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
        requestQueue.add(stringRequestmulti);


        return view;
    }
}