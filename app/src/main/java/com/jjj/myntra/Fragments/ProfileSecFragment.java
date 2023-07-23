package com.jjj.myntra.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterProfile_Bottom;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterProfile_Mid;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterProfile_Top;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterProfile_end;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Top;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileSecFragment extends Fragment {

    RecyclerView recyclerview_top,recyclerview_mid,recyclerview_bottom,recyclerview_end;
    RecyclerAdapterProfile_Top adapterProfile_top;
    RecyclerAdapterProfile_Mid adapterProfile_mid;
    RecyclerAdapterProfile_Bottom adapterProfile_bottom;
    RecyclerAdapterProfile_end adapterProfile_end;
    Button logout;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_NAME = "name";
    //private static final String KEY_ADDRESS = "address";

    TextView t1,t2,t3;

    ArrayList<Modalclass> arrayListtop,arrayListmid,arrayListbottom,arrayListend;
    RequestQueue requestQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_sec, container, false);

        recyclerview_top=view.findViewById(R.id.profile_recyclerview);
        recyclerview_mid=view.findViewById(R.id.profile_rec_mid);
        recyclerview_bottom=view.findViewById(R.id.profile_rec_bottom);
        recyclerview_end=view.findViewById(R.id.profile_rec_end);
        requestQueue= Volley.newRequestQueue(getContext());
        arrayListtop=new ArrayList<>();
        arrayListmid=new ArrayList<>();
        arrayListbottom=new ArrayList<>();
        arrayListend=new ArrayList<>();

        t1=view.findViewById(R.id.profile_fragtext);
        t2=view.findViewById(R.id.text_profile_sec);
        t3=view.findViewById(R.id.profile_fragtext2);

        sharedPreferences= getContext().getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String Name=sharedPreferences.getString(KEY_NAME,null);

        t1.setText(Name);
        t2.setText(Name);
        t3.setText(Name);


        // For Recycler View Top
        StringRequest stringRequesttop=new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/profiletopapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("pname");
                        String nametwo=jsonObject.getString("pnametwo");
                        modalclass.setName(name);
                        modalclass.setName2(nametwo);

                        String ptopimg="http://api.karanvarma.link/upload/"+jsonObject.getString("pimg");
                        modalclass.setImg(ptopimg);
                        arrayListtop.add(modalclass);
                        // For Recycler View First
                        adapterProfile_top=new RecyclerAdapterProfile_Top(getContext(),arrayListtop);
                        recyclerview_top.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerview_top.setAdapter(adapterProfile_top);

                        // Click listener
                       /* adapterProfile_top.onitemclicklistener(new Recyclerclicklistener() {
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
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequesttop);


        // For Recycler View Mid
        StringRequest stringRequestmid=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/profilemidapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("pname");
                        String nametwo=jsonObject.getString("pnametwo");
                        modalclass.setName(name);
                        modalclass.setName2(nametwo);

                        String pmidimg="http://api.karanvarma.link/upload/"+jsonObject.getString("pimg");
                        modalclass.setImg(pmidimg);
                        arrayListmid.add(modalclass);
                        // For Recycler View First
                        adapterProfile_mid=new RecyclerAdapterProfile_Mid(getContext(),arrayListmid);
                        recyclerview_mid.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerview_mid.setAdapter(adapterProfile_mid);

                        // Click listener
                           /* adapterProfile_top.onitemclicklistener(new Recyclerclicklistener() {
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
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestmid);



        // For Recycler View Bottom
        StringRequest stringRequestbottom=new StringRequest(Request.Method.GET, " http://api.karanvarma.link/Webservice1.asmx/profilebottomapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("pname");
                        String nametwo=jsonObject.getString("pnametwo");
                        modalclass.setName(name);
                        modalclass.setName2(nametwo);

                        String pbottomimg="http://api.karanvarma.link/upload/"+jsonObject.getString("pimg");
                        modalclass.setImg(pbottomimg);
                        arrayListbottom.add(modalclass);
                        // For Recycler View First
                        adapterProfile_bottom=new RecyclerAdapterProfile_Bottom(getContext(),arrayListbottom);
                        recyclerview_bottom.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerview_bottom.setAdapter(adapterProfile_bottom);

                        // Click listener
                           /* adapterProfile_top.onitemclicklistener(new Recyclerclicklistener() {
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
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestbottom);



        // For Recycler View End
        StringRequest stringRequestend=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/profileendapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("pname");
                        modalclass.setName(name);

                        String pendimg="http://api.karanvarma.link/upload/"+jsonObject.getString("pimg");
                        modalclass.setImg(pendimg);

                        arrayListend.add(modalclass);
                        // For Recycler View First
                        adapterProfile_end=new RecyclerAdapterProfile_end(getContext(),arrayListend);
                        recyclerview_end.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerview_end.setAdapter(adapterProfile_end);

                        // Click listener
                           /* adapterProfile_top.onitemclicklistener(new Recyclerclicklistener() {
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
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestend);


        sharedPreferences=getContext().getSharedPreferences("session", Context.MODE_PRIVATE);
        String ee=sharedPreferences.getString("e","0");

        //Logout

        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("e");
                editor.remove(KEY_NAME);

                editor.commit();

                if(getActivity()!=null){

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.bottom_container_main, new ProfileFragment()).commit();

                }
                else {
                    Toast.makeText(getContext(), "something wrong", Toast.LENGTH_SHORT).show();

                }

              //  getFragmentManager().beginTransaction().replace(R.id.profile_container,new ProfileFragment()).commit();

            }
        });

        return view;
    }
}