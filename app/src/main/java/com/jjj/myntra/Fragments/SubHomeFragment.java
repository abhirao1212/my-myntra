package com.jjj.myntra.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.jjj.myntra.Activities.MainActivity;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor_gadget;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor_subhome;
import com.jjj.myntra.RecyclerAdapters.ImageSliderAdaptor_subhome2;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterEnd_Subhome;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterMid2_Footwear;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterMid_Subhome;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterMid_Subhome2;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterMid_Subhome3;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterMid_kid;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterTop_Footwear;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterTop_Gadgets;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterTop_Kids;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterTop_Subhome;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterTop_Women;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Top;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class SubHomeFragment extends Fragment {

    //--For Cart--//
    ImageView mycart;

    //--For Men--//
    RecyclerView rec_top,rec_mid,rec_mid2,rec_mid4,rec_end;
    ViewPager2 viewpager1,viewpager2;
    CircleIndicator3 indicator3;
    ImageView gifimg1,img2,gifimg3,gifimg4;
    TextView text1,text2;

    ImageSliderAdaptor_subhome sliderAdaptor_subhome;
    Runnable sliderRunnable,sliderRunnable2;

    private Handler sliderHandler = new Handler();
    private Handler sliderHandler2 = new Handler();

    RecyclerAdapterTop_Subhome adapterTopsubhome;
    RecyclerAdapterMid_Subhome adapterMidSubhome;
    RecyclerAdapterMid_Subhome2 adapterMidSubhome2;
    RecyclerAdapterMid_Subhome3 adapterMidSubhome3;
    RecyclerAdapterEnd_Subhome adapterEndSubhome;

    ArrayList<Modalclass> arrayListtop,arrayListmid,arrayListmid2,arrayListmid3,arrayListend,arrayListslider,arrayListslider2;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";


    //--For Women--//
    RecyclerView rec_topW,rec_midW,rec_midW2,rec_midW4,rec_endW;
    ViewPager2 viewpagerW1,viewpagerW2;
    ImageView gifimgW1,imgW2,gifimgW3,gifimgW4;
    TextView textW1,textW2;

    Runnable sliderRunnableW,sliderRunnableW2;

    private Handler sliderHandlerW = new Handler();
    private Handler sliderHandlerW2 = new Handler();

    RecyclerAdapterTop_Women adapterTopWomen;

    ArrayList<Modalclass> arrayListtopW,arrayListmidW,arrayListmidW2,arrayListmidW3,arrayListendW,arrayListsliderW,arrayListsliderW2;

    RequestQueue requestQueue;


    //--For Kids--//
    RecyclerView rec_topk,rec_midk,rec_mid2k;
    ViewPager2 viewpagerk;
    ImageView gifimgk1,gifimgk2,gifimgk3;

    LinearLayout layout,layout2,layout3;

    Runnable sliderRunnablek;
    private Handler sliderHandlerk= new Handler();

    RecyclerAdapterTop_Kids adapterTop_kids;

    ArrayList<Modalclass> arrayListtopk,arrayListmidk,arrayListmid2k,arrayListsliderk,arrayListmultik;

    RequestQueue requestQueuek;


    //--For Footwear--//
    RecyclerView rec_topf,rec_midf,rec_mid2f;
    ViewPager2 viewpagerf;
    ImageView gifimgf1,gifimgf2,gifimgf3;

    Runnable sliderRunnablef;
    private Handler sliderHandlerf= new Handler();

    RecyclerAdapterTop_Footwear adapterTop_footwear;

    ArrayList<Modalclass> arrayListtopf,arrayListmidf,arrayListmid2f,arrayListsliderf,arrayListmultif;

    RequestQueue requestQueuef;


    //--For Gadgets--//
    RecyclerView rec_topg,rec_midg,rec_mid2g;
    ViewPager2 viewpagerg;
    ImageView gifimgg1,gifimgg2,gifimgg3,gifimgg4,gifimgg5,search,back;
    RecyclerAdapterTop_Gadgets adapterTop_gadgets;

    Runnable sliderRunnableg;
    private Handler sliderHandlerg= new Handler();

    ArrayList<Modalclass> arrayListtopg,arrayListmidg,arrayListmid2g,arrayListsliderg,arrayListmultig;

    RequestQueue requestQueueg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_home, container, false);

        sharedPreferences=getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Hide Layouts
        layout=view.findViewById(R.id.linear_subhome1);
        layout2=view.findViewById(R.id.linear_subhome2);
        layout3=view.findViewById(R.id.linear_subhome3);

        //Redirect To Cart
        back=view.findViewById(R.id.back_subhome);
        search=view.findViewById(R.id.search_subhome);
        mycart=view.findViewById(R.id.bag_subhome);
        mycart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                String id = sharedPreferences.getString(KEY_ID,null);
                RequestQueue queue = Volley.newRequestQueue(getContext());

                String url="http://api.karanvarma.link/Webservice1.asmx/emptycart2api?userid="+id;

                StringRequest stringRequestempty = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.length()==0)
                            {

                                getFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                            }
                            else {

                                getFragmentManager().beginTransaction().replace(R.id.profile_container_main,new CartFragment()).addToBackStack(null).commit();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        getFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                    }
                });

                queue.add(stringRequestempty);

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new SearchFragment()).addToBackStack(null).commit();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });
        
        //Receiving Data From Home Fragment
        Bundle bundle=this.getArguments();
        String cid=bundle.getString("key");

        //-Data Men-//
        if (cid.equals("1")) {

            //Recycler View
            rec_top=view.findViewById(R.id.rectop_subhome);
            rec_mid=view.findViewById(R.id.recmid_subhome);
            rec_mid2=view.findViewById(R.id.recmid2_subhome);
            //rec_mid3=view.findViewById(R.id.recmid3_subhome);
            rec_mid4=view.findViewById(R.id.recmid4_subhome);
            //rec_end=view.findViewById(R.id.recend_subhome);

            //Image Slider
            viewpager1=view.findViewById(R.id.slider1_subhome);
            viewpager2=view.findViewById(R.id.slider2_subhome);

            //Image View
            gifimg1=view.findViewById(R.id.imggif1_subhome);
            img2=view.findViewById(R.id.img2_subhome);
            gifimg3=view.findViewById(R.id.imggif3_subhome);
            gifimg4=view.findViewById(R.id.imggif4_subhome);

            //Text View
            text1=view.findViewById(R.id.txt1_subhome);
            text2=view.findViewById(R.id.txt2_subhome);

            indicator3=view.findViewById(R.id.indicator);


            //Api
            requestQueue= Volley.newRequestQueue(getContext());
            arrayListtop=new ArrayList<>();
            arrayListmid=new ArrayList<>();
            arrayListmid2=new ArrayList<>();
            arrayListmid3=new ArrayList<>();
            arrayListend=new ArrayList<>();
            arrayListslider=new ArrayList<>();
            arrayListslider2=new ArrayList<>();


            //For Recycler View Top
            StringRequest stringRequesttop = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/mentopapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("mname");
                            String id = jsonObject.getString("id");

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("mimg");
                            modalclass.setName(name);
                            modalclass.setId(id);
                            modalclass.setImg(img);
                            arrayListtop.add(modalclass);
                            // For Recycler View First
                            adapterTopsubhome = new RecyclerAdapterTop_Subhome(getContext(), arrayListtop);
                            rec_top.setLayoutManager(new GridLayoutManager(getContext(), 5));
                            rec_top.setAdapter(adapterTopsubhome);

                            // Click listener
                            adapterTopsubhome.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {
                                    ProductFragment productFragment = new ProductFragment();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("key_sub", "" + arrayListtop.get(pos).getId());
                                    productFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.profile_container_main, productFragment).addToBackStack(null).commit();

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
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequesttop);


            // For Recycler View Mid
            StringRequest stringRequestmid = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/menmidapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("mname");
                            String id = jsonObject.getString("id");
                            modalclass.setName(name);
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("mimg");
                            modalclass.setImg(img);
                            arrayListmid.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome = new RecyclerAdapterMid_Subhome(getContext(), arrayListmid);
                            rec_mid.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_mid.setAdapter(adapterMidSubhome);

                            // Click listener

                        /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmid);


            // For Recycler View Mid 2
            StringRequest stringRequestmid2 = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/menmid2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("mname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("mimg");
                            modalclass.setImg(img);
                            arrayListmid2.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome2 = new RecyclerAdapterMid_Subhome2(getContext(), arrayListmid2);
                            rec_mid2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_mid2.setAdapter(adapterMidSubhome2);

                            // Click listener

                           /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmid2);


            // For Recycler View Mid 3
            StringRequest stringRequestmid3 = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/menmid3api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("mname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("mimg");
                            modalclass.setImg(img);
                            arrayListmid3.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome3 = new RecyclerAdapterMid_Subhome3(getContext(), arrayListmid3);
                            rec_mid4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_mid4.setAdapter(adapterMidSubhome3);

                            // Click listener

                           /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmid3);


            // For Recycler View End
           /* StringRequest stringRequestend = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/menendapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("mname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("mimg");
                            modalclass.setImg(img);
                            arrayListend.add(modalclass);
                            // For Recycler View First
                            adapterEndSubhome = new RecyclerAdapterEnd_Subhome(getContext(), arrayListend);
                            rec_end.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_end.setAdapter(adapterEndSubhome);

                            // Click listener

                           *//*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*//*


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestend);*/


            // For Image Slider First
            StringRequest stringRequestslider = new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/mensliderapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("mimg");

                            modalclass.setImg(image);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            viewpager1.setAdapter(new ImageSliderAdaptor_subhome(viewpager1, arrayListslider));
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
            viewpager1.setClipToPadding(false);
            viewpager1.setClipChildren(false);
            viewpager1.setOffscreenPageLimit(3);
            viewpager1.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            indicator3.setViewPager(this.viewpager1);
            indicator3.createIndicators(5,0);


            /*CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(20));
            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });*/
            
            sliderRunnable = new Runnable() {
                @Override
                public void run() {
                    viewpager1.setCurrentItem(viewpager1.getCurrentItem() + 1);
                }
            };
            
         //   viewpager1.setPageTransformer(compositePageTransformer);
            viewpager1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandler.removeCallbacks(sliderRunnable);
                    sliderHandler.postDelayed(sliderRunnable, 4000); // slide duration 2 seconds
                }
            });


            //For Image Slider Second
            StringRequest stringRequestslider2 = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/subcategoryapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("subimg");

                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            modalclass.setImg(image);
                            viewpager2.setAdapter(new ImageSliderAdaptor_subhome2(viewpager2, arrayListslider2));
                            arrayListslider2.add(modalclass);

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
            requestQueue.add(stringRequestslider2);

            // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
            viewpager2.setClipToPadding(false);
            viewpager2.setClipChildren(false);
            viewpager2.setOffscreenPageLimit(3);
            viewpager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            
            CompositePageTransformer compositePageTransformer2 = new CompositePageTransformer();
            compositePageTransformer2.addTransformer(new MarginPageTransformer(20));
            compositePageTransformer2.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });
            
            sliderRunnable2 = new Runnable() {
                @Override
                public void run() {
                    viewpager2.setCurrentItem(viewpager2.getCurrentItem() + 1);
                }
            };

            viewpager2.setPageTransformer(compositePageTransformer2);
            viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandler2.removeCallbacks(sliderRunnable2);
                    sliderHandler2.postDelayed(sliderRunnable2, 3000); // slide duration 2 seconds
                }
            });


            //For Text
            StringRequest stringRequesttext = new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/mentextapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String txt = jsonArray.getJSONObject(0).getString("tname");
                        text1.setText(txt);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequesttext);


            //For Text 2
            StringRequest stringRequesttext2 = new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/mentext2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String txt2 = jsonArray.getJSONObject(0).getString("tname");
                        text2.setText(txt2);

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequesttext2);


            //For Multi Images
            StringRequest stringRequestmulti_sub = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/imgsubmultiapi?catid=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String imag1 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(0).getString("iimg");
                        Glide.with(getContext()).load(imag1).into(gifimg1);

                        String imag2 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(1).getString("iimg");
                        Glide.with(getContext()).load(imag2).into(img2);

                        String imag3 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(2).getString("iimg");
                        Glide.with(getContext()).load(imag3).into(gifimg3);

                        String imag4 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(3).getString("iimg");
                        Glide.with(getContext()).load(imag4).into(gifimg4);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmulti_sub);
            
        }
        

        //-Data Women-//
        else if (cid.equals("2")) {

            //Recycler View
            rec_topW=view.findViewById(R.id.rectop_subhome);
            rec_midW=view.findViewById(R.id.recmid_subhome);
            rec_midW2=view.findViewById(R.id.recmid2_subhome);
            //rec_mid3=view.findViewById(R.id.recmid3_subhome);
            rec_midW4=view.findViewById(R.id.recmid4_subhome);
           // rec_endW=view.findViewById(R.id.recend_subhome);

            //Image Slider
            viewpagerW1=view.findViewById(R.id.slider1_subhome);
            viewpagerW2=view.findViewById(R.id.slider2_subhome);

            //Image View
            gifimgW1=view.findViewById(R.id.imggif1_subhome);
            imgW2=view.findViewById(R.id.img2_subhome);
            gifimgW3=view.findViewById(R.id.imggif3_subhome);
            gifimgW4=view.findViewById(R.id.imggif4_subhome);
            ImageView gifimgw=view.findViewById(R.id.imggifw_subhome);
            ImageView gifimgalt=view.findViewById(R.id.imggifalt_subhome);

            gifimgW3.setVisibility(View.GONE);
            gifimgW4.setVisibility(View.GONE);
            gifimgw.setVisibility(View.VISIBLE);
            gifimgalt.setVisibility(View.VISIBLE);

            //Text View
            textW1=view.findViewById(R.id.txt1_subhome);
            textW2=view.findViewById(R.id.txt2_subhome);

            indicator3=view.findViewById(R.id.indicator);

            //Api
            requestQueue= Volley.newRequestQueue(getContext());
            arrayListtopW=new ArrayList<>();
            arrayListmidW=new ArrayList<>();
            arrayListmidW2=new ArrayList<>();
            arrayListmidW3=new ArrayList<>();
            arrayListendW=new ArrayList<>();
            arrayListsliderW=new ArrayList<>();
            arrayListsliderW2=new ArrayList<>();

            //For Recycler View Top
            StringRequest stringRequesttopw = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womentopapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("response", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        Log.d("response2", response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("wname");
                            String id = jsonObject.getString("id");


                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");
                            // Toast.makeText(getContext(), "gggg"+img, Toast.LENGTH_SHORT).show();
                            modalclass.setName(name);
                            modalclass.setId(id);
                            modalclass.setImg(img);
                            arrayListtopW.add(modalclass);
                            // For Recycler View First
                            adapterTopWomen = new RecyclerAdapterTop_Women(getContext(), arrayListtopW);
                            rec_topW.setLayoutManager(new GridLayoutManager(getContext(), 5));
                            rec_topW.setAdapter(adapterTopWomen);

                            // Click listener
                       /* adapterTopsubhome.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {
                                Log.d("position", String.valueOf(pos));
                                ProductFragment productFragment = new ProductFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("key_sub",""+arrayListtop.get(pos).getId());
                                productFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.hide_container,productFragment).addToBackStack(null).commit();

                            }
                        });
*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequesttopw);


            // For Recycler View Women Mid
            StringRequest stringRequestmidw = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womenmidapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("wname");
                            String id = jsonObject.getString("id");
                            modalclass.setName(name);
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");
                            modalclass.setImg(img);
                            arrayListmidW.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome = new RecyclerAdapterMid_Subhome(getContext(), arrayListmidW);
                            rec_midW.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_midW.setAdapter(adapterMidSubhome);

                            // Click listener

                        /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmidw);


            // For Recycler View Women Mid 2
            StringRequest stringRequestmidw2 = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womenmid2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("wname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");
                            modalclass.setImg(img);
                            arrayListmidW2.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome2 = new RecyclerAdapterMid_Subhome2(getContext(), arrayListmidW2);
                            rec_midW2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_midW2.setAdapter(adapterMidSubhome2);

                            // Click listener

                           /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmidw2);


            // For Recycler View Women Mid 3
            StringRequest stringRequestmidw3 = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womenmid3api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("wname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");
                            modalclass.setImg(img);
                            arrayListmidW3.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome3 = new RecyclerAdapterMid_Subhome3(getContext(), arrayListmidW3);
                            rec_midW4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_midW4.setAdapter(adapterMidSubhome3);

                            // Click listener

                           /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmidw3);


            // For Recycler View Women End
         /*   StringRequest stringRequestendw = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womenendapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("wname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");
                            modalclass.setImg(img);
                            arrayListendW.add(modalclass);
                            // For Recycler View First
                            adapterEndSubhome = new RecyclerAdapterEnd_Subhome(getContext(), arrayListendW);
                            rec_endW.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_endW.setAdapter(adapterEndSubhome);

                            // Click listener

                              adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

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
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestendw);
*/

            // For Image Slider Women
            StringRequest stringRequestsliderw = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womensliderapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");

                            modalclass.setImg(image);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            viewpagerW1.setAdapter(new ImageSliderAdaptor_subhome(viewpagerW1, arrayListsliderW));
                            arrayListsliderW.add(modalclass);

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
            requestQueue.add(stringRequestsliderw);


            // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
            viewpagerW1.setClipToPadding(false);
            viewpagerW1.setClipChildren(false);
            viewpagerW1.setOffscreenPageLimit(3);
            viewpagerW1.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


            CompositePageTransformer compositePageTransformerw = new CompositePageTransformer();
            compositePageTransformerw.addTransformer(new MarginPageTransformer(20));
            compositePageTransformerw.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });
            
            sliderRunnableW = new Runnable() {
                @Override
                public void run() {
                    viewpagerW1.setCurrentItem(viewpagerW1.getCurrentItem() + 1);
                }
            };
            
            viewpagerW1.setPageTransformer(compositePageTransformerw);
            viewpagerW1.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandlerW.removeCallbacks(sliderRunnableW);
                    sliderHandlerW.postDelayed(sliderRunnableW, 3000); // slide duration 2 seconds
                }
            });


            // For Image Slider2 Women
            StringRequest stringRequestslider2w = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womenslider2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");

                            modalclass.setImg(image);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            viewpagerW2.setAdapter(new ImageSliderAdaptor_subhome(viewpagerW2, arrayListsliderW2));
                            arrayListsliderW2.add(modalclass);

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
            requestQueue.add(stringRequestslider2w);


            // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
            viewpagerW2.setClipToPadding(false);
            viewpagerW2.setClipChildren(false);
            viewpagerW2.setOffscreenPageLimit(3);
            viewpagerW2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


            CompositePageTransformer compositePageTransformer2w = new CompositePageTransformer();
            compositePageTransformer2w.addTransformer(new MarginPageTransformer(20));
            compositePageTransformer2w.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });

            sliderRunnableW2= new Runnable() {
                @Override
                public void run() {
                    viewpagerW2.setCurrentItem(viewpagerW2.getCurrentItem() + 1);
                }
            };

            viewpagerW2.setPageTransformer(compositePageTransformer2w);
            viewpagerW2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandlerW2.removeCallbacks(sliderRunnableW2);
                    sliderHandlerW2.postDelayed(sliderRunnableW2, 3000); // slide duration 2 seconds
                }
            });

            //For Multi Images Women
            StringRequest stringRequestmulti_w = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/womenmultiapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String imag1 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(0).getString("wiimg");
                        Glide.with(getContext()).load(imag1).into(gifimgW1);

                        String imag2 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(1).getString("wiimg");
                        Glide.with(getContext()).load(imag2).into(imgW2);

                        String imag3 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(2).getString("wiimg");
                        Glide.with(getContext()).load(imag3).into(gifimgw);

                        String imag4 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(3).getString("wiimg");
                        Glide.with(getContext()).load(imag4).into(gifimgalt);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(stringRequestmulti_w);



        }


        //-Data Kids-//
        else if (cid.equals("3")){

            //Recycler View
            rec_topk=view.findViewById(R.id.rectop_subhome_kid);
            rec_midk=view.findViewById(R.id.recmid_subhome_kid);
            rec_mid2k=view.findViewById(R.id.recmid2_subhome_kid);
            
            //Image Slider
            viewpagerk=view.findViewById(R.id.slider1_subhome_kid);
            
            //Image View
            gifimgk1=view.findViewById(R.id.imggif1_subhome_sec);
            gifimgk2=view.findViewById(R.id.imggif2_subhome_sec);
            gifimgk3=view.findViewById(R.id.ad3_kid);

            //Api
            requestQueuek= Volley.newRequestQueue(getContext());
            arrayListtopk=new ArrayList<>();
            arrayListmidk=new ArrayList<>();
            arrayListmid2k=new ArrayList<>();
            arrayListsliderk=new ArrayList<>();
            arrayListmultik=new ArrayList<>();
            
            //Hide Layout
            layout.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);
            

            //For Recycler View Top
            StringRequest stringRequesttopk = new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/kidstopapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("kname");
                            modalclass.setName(name);

                            String id = jsonObject.getString("id");
                            modalclass.setId(id);

                           // String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("wimg");
                           // modalclass.setImg(img);

                            arrayListtopk.add(modalclass);

                            adapterTop_kids = new RecyclerAdapterTop_Kids(getContext(), arrayListtopk);
                            rec_topk.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                            rec_topk.setAdapter(adapterTop_kids);

                            // Click listener
                       /* adapterTopsubhome.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {
                                Log.d("position", String.valueOf(pos));
                                ProductFragment productFragment = new ProductFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("key_sub",""+arrayListtop.get(pos).getId());
                                productFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.hide_container,productFragment).addToBackStack(null).commit();

                            }
                        });
*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuek.add(stringRequesttopk);


            // For Recycler View Mid
            StringRequest stringRequestmidk = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/kidsmidapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("kname");
                            modalclass.setName(name);

                            String id = jsonObject.getString("id");
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("kimg");
                            modalclass.setImg(img);

                            arrayListmidk.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome = new RecyclerAdapterMid_Subhome(getContext(), arrayListmidk);
                            rec_midk.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_midk.setAdapter(adapterMidSubhome);

                            // Click listener

                        /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuek.add(stringRequestmidk);


            // For Image Slider First
            StringRequest stringRequestsliderk = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/kidssliderapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("kimg");

                            modalclass.setImg(image);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            viewpagerk.setAdapter(new ImageSliderAdaptor_subhome(viewpagerk, arrayListsliderk));
                            arrayListsliderk.add(modalclass);

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
            requestQueuek.add(stringRequestsliderk);
            
            // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
            viewpagerk.setClipToPadding(false);
            viewpagerk.setClipChildren(false);
            viewpagerk.setOffscreenPageLimit(3);
            viewpagerk.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformerk = new CompositePageTransformer();
            compositePageTransformerk.addTransformer(new MarginPageTransformer(20));
            compositePageTransformerk.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });

            sliderRunnablek = new Runnable() {
                @Override
                public void run() {
                    viewpagerk.setCurrentItem(viewpagerk.getCurrentItem() + 1);
                }
            };
            
            viewpagerk.setPageTransformer(compositePageTransformerk);
            viewpagerk.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandlerk.removeCallbacks(sliderRunnablek);
                    sliderHandlerk.postDelayed(sliderRunnablek, 2000); // slide duration 2 seconds
                }
            });

            // For Recycler View Mid 2
            StringRequest stringRequestmid2k = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/kidsmid2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("kname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("kimg");
                            modalclass.setImg(img);
                            arrayListmid2k.add(modalclass);
                            // For Recycler View First
                            RecyclerAdapterMid_kid adapterMid_kid = new RecyclerAdapterMid_kid(getContext(), arrayListmid2k);
                            rec_mid2k.setLayoutManager(new GridLayoutManager(getContext(),3));
                            rec_mid2k.setAdapter(adapterMid_kid);

                            // Click listener

                           /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuek.add(stringRequestmid2k);


            //For Multi Images
            StringRequest stringRequestmulti_subk = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/kidsImgmultiapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String imag1 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(0).getString("kiimg");
                        Glide.with(getContext()).load(imag1).into(gifimgk1);

                        String imag2 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(1).getString("kiimg");
                        Glide.with(getContext()).load(imag2).into(gifimgk2);

                        String imag3 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(2).getString("kiimg");
                        Glide.with(getContext()).load(imag3).into(gifimgk3);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuek.add(stringRequestmulti_subk);

        }


        //-Data Footwear-//
        else if(cid.equals("4")){

            //Recycler View
            rec_topf=view.findViewById(R.id.rectop_subhome_kid);
            rec_midf=view.findViewById(R.id.recmid_subhome_kid);
            rec_mid2f=view.findViewById(R.id.recmid2_subhome_kid);

            //Image Slider
            viewpagerf=view.findViewById(R.id.slider1_subhome_kid);

            //Image View
            gifimgf1=view.findViewById(R.id.imggif1_subhome_sec);
            gifimgf2=view.findViewById(R.id.imggif2_subhome_sec);
            gifimgf3=view.findViewById(R.id.ad3_kid);

            //Api
            requestQueuef= Volley.newRequestQueue(getContext());
            arrayListtopf=new ArrayList<>();
            arrayListmidf=new ArrayList<>();
            arrayListmid2f=new ArrayList<>();
            arrayListsliderf=new ArrayList<>();
            arrayListmultif=new ArrayList<>();

            //Hide Layout
            layout.setVisibility(View.GONE);
            layout2.setVisibility(View.VISIBLE);


            //For Recycler View Top
            StringRequest stringRequesttopf = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/footweartopapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("fname");
                            modalclass.setName(name);

                            String id = jsonObject.getString("id");
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("fimg");
                            modalclass.setImg(img);

                            arrayListtopf.add(modalclass);

                            adapterTop_footwear = new RecyclerAdapterTop_Footwear(getContext(), arrayListtopf);
                            rec_topf.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
                            rec_topf.setAdapter(adapterTop_footwear);

                            // Click listener
                       /* adapterTopsubhome.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {
                                Log.d("position", String.valueOf(pos));
                                ProductFragment productFragment = new ProductFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("key_sub",""+arrayListtop.get(pos).getId());
                                productFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.hide_container,productFragment).addToBackStack(null).commit();

                            }
                        });
*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuef.add(stringRequesttopf);

            // For Recycler View Mid
            StringRequest stringRequestmidf = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/footwearmidapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("fname");
                            modalclass.setName(name);

                            String id = jsonObject.getString("id");
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("fimg");
                            modalclass.setImg(img);

                            arrayListmidf.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome = new RecyclerAdapterMid_Subhome(getContext(), arrayListmidf);
                            rec_midf.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_midf.setAdapter(adapterMidSubhome);

                            // Click listener

                        /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });
*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuef.add(stringRequestmidf);

            // For Recycler View Mid 2
            StringRequest stringRequestmid2f = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/footwearmid2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("fname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("fimg");
                            modalclass.setImg(img);
                            arrayListmid2f.add(modalclass);
                            // For Recycler View First
                            RecyclerAdapterMid2_Footwear adapterMid2_footwear = new RecyclerAdapterMid2_Footwear(getContext(), arrayListmid2f);
                            rec_mid2f.setLayoutManager(new GridLayoutManager(getContext(),3));
                            rec_mid2f.setAdapter(adapterMid2_footwear);

                            // Click listener
/*
                              adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuef.add(stringRequestmid2f);


            // For Image Slider First
            StringRequest stringRequestsliderf = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/footwearsliderapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("fimg");

                            modalclass.setImg(image);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            viewpagerf.setAdapter(new ImageSliderAdaptor_subhome(viewpagerf, arrayListsliderf));
                            arrayListsliderf.add(modalclass);

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
            requestQueuef.add(stringRequestsliderf);

            // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
            viewpagerf.setClipToPadding(false);
            viewpagerf.setClipChildren(false);
            viewpagerf.setOffscreenPageLimit(3);
            viewpagerf.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformerf = new CompositePageTransformer();
            compositePageTransformerf.addTransformer(new MarginPageTransformer(20));
            compositePageTransformerf.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });

            sliderRunnablef = new Runnable() {
                @Override
                public void run() {
                    viewpagerf.setCurrentItem(viewpagerf.getCurrentItem() + 1);
                }
            };

            viewpagerf.setPageTransformer(compositePageTransformerf);
            viewpagerf.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandlerf.removeCallbacks(sliderRunnablef);
                    sliderHandlerf.postDelayed(sliderRunnablef, 2000); // slide duration 2 seconds
                }
            });


            //For Multi Images
            StringRequest stringRequestmulti_subf = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/footwearimgmultiapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String imag1 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(0).getString("fiimg");
                        Glide.with(getContext()).load(imag1).into(gifimgf1);

                        String imag2 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(1).getString("fiimg");
                        Glide.with(getContext()).load(imag2).into(gifimgf2);

                        String imag3 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(2).getString("fiimg");
                        Glide.with(getContext()).load(imag3).into(gifimgf3);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueuef.add(stringRequestmulti_subf);

        }


        //-Data Gadgets-//
        else if (cid.equals("5")){

            //Recycler View
            rec_topg=view.findViewById(R.id.rectop_subhome_gad);
            rec_midg=view.findViewById(R.id.recmid_subhome_gad);
            rec_mid2g=view.findViewById(R.id.recmid2_subhome_gad);

            //Image Slider
            viewpagerg=view.findViewById(R.id.slider1_subhome_gad);

            //Image View
            gifimgg1=view.findViewById(R.id.imggif1_subhome_gad);
            gifimgg2=view.findViewById(R.id.imggif2_subhome_gad);
            gifimgg3=view.findViewById(R.id.imggif3_subhome_gad);
            gifimgg4=view.findViewById(R.id.ad3_gad);
            gifimgg5=view.findViewById(R.id.ad4_gad);

            //Api
            requestQueueg= Volley.newRequestQueue(getContext());
            arrayListtopg=new ArrayList<>();
            arrayListmidg=new ArrayList<>();
            arrayListmid2g=new ArrayList<>();
            arrayListsliderg=new ArrayList<>();
            arrayListmultig=new ArrayList<>();

            //Hide
            layout.setVisibility(View.GONE);
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.VISIBLE);


            //For Recycler View Top
            StringRequest stringRequesttopg = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/gadgettopapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String name = jsonObject.getString("gname");
                            modalclass.setName(name);

                            String id = jsonObject.getString("id");
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("gimg");
                            modalclass.setImg(img);

                            arrayListtopg.add(modalclass);

                            adapterTop_gadgets = new RecyclerAdapterTop_Gadgets(getContext(), arrayListtopg);
                            rec_topg.setLayoutManager(new GridLayoutManager(getContext(),5));
                            rec_topg.setAdapter(adapterTop_gadgets);

                            // Click listener
                       /* adapterTopsubhome.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {
                                Log.d("position", String.valueOf(pos));
                                ProductFragment productFragment = new ProductFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("key_sub",""+arrayListtop.get(pos).getId());
                                productFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.hide_container,productFragment).addToBackStack(null).commit();

                            }
                        });
*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueueg.add(stringRequesttopg);


            // For Recycler View Mid
            StringRequest stringRequestmidg = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/gadgetmidapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("gname");
                            modalclass.setName(name);

                            String id = jsonObject.getString("id");
                            modalclass.setId(id);

                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("gimg");
                            modalclass.setImg(img);

                            arrayListmidg.add(modalclass);
                            // For Recycler View First
                            adapterMidSubhome = new RecyclerAdapterMid_Subhome(getContext(), arrayListmidg);
                            rec_midg.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                            rec_midg.setAdapter(adapterMidSubhome);

                            // Click listener

                        /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });
*/
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueueg.add(stringRequestmidg);


            // For Image Slider First
            StringRequest stringRequestsliderg = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/gadsliderapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String image = "http://api.karanvarma.link/upload/" + jsonObject.getString("gimg");

                            modalclass.setImg(image);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            viewpagerg.setAdapter(new ImageSliderAdaptor_gadget(viewpagerg, arrayListsliderg));
                            arrayListsliderg.add(modalclass);

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
            requestQueueg.add(stringRequestsliderg);

            // viewPager2.setAdapter(new ImageSliderAdaptor(viewPager2, arraylist5));
            viewpagerg.setClipToPadding(false);
            viewpagerg.setClipChildren(false);
            viewpagerg.setOffscreenPageLimit(3);
            viewpagerg.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformerg = new CompositePageTransformer();
            compositePageTransformerg.addTransformer(new MarginPageTransformer(20));
            compositePageTransformerg.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });

            sliderRunnableg = new Runnable() {
                @Override
                public void run() {
                    viewpagerg.setCurrentItem(viewpagerg.getCurrentItem() + 1);
                }
            };

            viewpagerg.setPageTransformer(compositePageTransformerg);
            viewpagerg.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    sliderHandlerg.removeCallbacks(sliderRunnableg);
                    sliderHandlerg.postDelayed(sliderRunnableg, 3000); // slide duration 2 seconds
                }
            });


            //For Multi Images
            StringRequest stringRequestmulti_subg = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/gadimgmultiapi?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        String imag1 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(0).getString("giimg");
                        Glide.with(getContext()).load(imag1).into(gifimgg1);

                        String imag2 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(1).getString("giimg");
                        Glide.with(getContext()).load(imag2).into(gifimgg2);

                        String imag3 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(2).getString("giimg");
                        Glide.with(getContext()).load(imag3).into(gifimgg3);

                        String imag4 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(3).getString("giimg");
                        Glide.with(getContext()).load(imag4).into(gifimgg4);

                        String imag5 = "http://api.karanvarma.link/upload/" + jsonArray.getJSONObject(4).getString("giimg");
                        Glide.with(getContext()).load(imag5).into(gifimgg5);


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueueg.add(stringRequestmulti_subg);


            // For Recycler View Mid 2
            StringRequest stringRequestmid2g = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/gadmid2api?id=" + cid, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            Modalclass modalclass = new Modalclass();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("gname");
                            modalclass.setName(name);
                            String id = jsonObject.getString("id");

                            modalclass.setId(id);
                            String img = "http://api.karanvarma.link/upload/" + jsonObject.getString("gimg");
                            modalclass.setImg(img);
                            arrayListmid2g.add(modalclass);
                            // For Recycler View First
                            RecyclerAdapterMid_kid adapterMid_kid = new RecyclerAdapterMid_kid(getContext(), arrayListmid2g);
                            rec_mid2g.setLayoutManager(new GridLayoutManager(getContext(),3));
                            rec_mid2g.setAdapter(adapterMid_kid);

                            // Click listener

                           /*   adapter_top.onitemclicklistener(new Recyclerclicklistener() {
                                @Override
                                public void onclick(int pos) {

                                    SubHomeFragment subHomeFragment=new SubHomeFragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("key",""+pos);
                                    subHomeFragment.setArguments(bundle);
                                    getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();

                                }
                            });*/


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueueg.add(stringRequestmid2g);

        }

        return view;
    }

  /*  @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
         inflater=getActivity().getMenuInflater();
         inflater.inflate(R.menu.tools_subhome,menu);
         MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
             @Override
             public boolean onMenuItemActionExpand(@NonNull MenuItem menuItem) {
                 return true;
             }

             @Override
             public boolean onMenuItemActionCollapse(@NonNull MenuItem menuItem) {
                 return false;
             }
         };

        super.onCreateOptionsMenu(menu, inflater);

        return;
    }
*/

  /* //For Toolbar Items

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.bagsub:

            Toast.makeText(getContext(), "hgdosfuagf", Toast.LENGTH_SHORT).show();

            return false;
        }


        return super.onOptionsItemSelected(item);
    }*/
  public void onBackPressed()
  {
      FragmentManager fm = getActivity().getSupportFragmentManager();
      fm.popBackStack();
  }

}