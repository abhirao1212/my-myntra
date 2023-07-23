package com.jjj.myntra.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterCategory;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Top;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    RecyclerView recyclercat;
    ImageView img1;

    ArrayList<Modalclass> arrayListcat;
    RequestQueue requestQueue;

    RecyclerAdapterCategory adaptercat;

    //Toolbar
    ImageView bag;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        sharedPreferences=getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        recyclercat=view.findViewById(R.id.category_recycler);

        //Toolbar
        bag=view.findViewById(R.id.bag_categories);

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = sharedPreferences.getString(KEY_ID,null);
                RequestQueue queue = Volley.newRequestQueue(getContext());

                String url=" http://api.karanvarma.link/Webservice1.asmx/emptycart2api?userid="+id;

                StringRequest stringRequestempty = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                           /* if (response==null){

                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                            }*/

                            if(jsonArray.length()==0)
                            {

                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                            }
                            else {

                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new CartFragment()).addToBackStack(null).commit();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                    }
                });

                queue.add(stringRequestempty);

            }
        });

        requestQueue= Volley.newRequestQueue(getContext());
        arrayListcat=new ArrayList<>();

        StringRequest stringRequestcat=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/categorypageapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String catimg="http://api.karanvarma.link/upload/"+jsonObject.getString("cimg");
                        modalclass.setImg(catimg);
                        arrayListcat.add(modalclass);
                        // For Recycler View First
                        adaptercat=new RecyclerAdapterCategory(getContext(),arrayListcat);
                        recyclercat.setLayoutManager(new GridLayoutManager(getContext(),3));
                        recyclercat.setAdapter(adaptercat);

                        // Click listener
                       /* adapter_top.onitemclicklistener(new Recyclerclicklistener() {
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
                Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestcat);


        //Category Img
        img1=view.findViewById(R.id.img_cat);

        StringRequest stringRequestmulti = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/imagesmultiapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    String imag1 = "http://api.karanvarma.link/upload/"+jsonArray.getJSONObject(6).getString("iimg");
                    Glide.with(getContext()).load(imag1).into(img1);


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