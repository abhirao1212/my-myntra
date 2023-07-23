package com.jjj.myntra.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapterEnd_Subhome;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Product;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Top;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    RecyclerView recycler_product;
    RecyclerAdapter_Product adapter_product;
    ImageView img_product;
    TextView ptxt1,ptxt2,ptxt3,ptxt4,ptxt5,ptxt6;

    ArrayList<Modalclass> arrayListproduct;
    RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        //Receiving Data From Home Fragment
        Bundle bundle=this.getArguments();
        String cid_sub=bundle.getString("key_sub");

        requestQueue= Volley.newRequestQueue(getContext());
        arrayListproduct=new ArrayList<>();

        recycler_product=view.findViewById(R.id.recycler_product);
        img_product=view.findViewById(R.id.img_product);
        ptxt1=view.findViewById(R.id.text_product);
        ptxt2=view.findViewById(R.id.description_product);
        ptxt3=view.findViewById(R.id.cutprice_product);
        ptxt4=view.findViewById(R.id.price_product);
        ptxt5=view.findViewById(R.id.offer_product);

        ptxt6=view.findViewById(R.id.bannertxt_product);

        // For Recycler View First
        StringRequest stringRequestproduct=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/productapi?id="+cid_sub, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //    Log.d("response",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);

                  //  Log.d("response2",response);
                    for (int i=0;i<jsonArray.length();i++){
                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String name=jsonObject.getString("pname");
                        String name2=jsonObject.getString("pdes");
                        String name3=jsonObject.getString("ppricecut");
                        String name4=jsonObject.getString("pprice");
                        String name5=jsonObject.getString("poffer");
                        String name6=jsonObject.getString("pbanner");

                        modalclass.setName(name);
                        modalclass.setName2(name2);
                        modalclass.setName3(name3);
                        modalclass.setName4(name4);
                        modalclass.setName5(name5);
                        modalclass.setName6(name6);

                        String ID=jsonObject.getString("Id");
                        modalclass.setId(ID);

                        String img="http://api.karanvarma.link/upload/"+jsonObject.getString("pimg");

                        modalclass.setImg(img);
                        arrayListproduct.add(modalclass);
                        // For Recycler View First
                        adapter_product=new RecyclerAdapter_Product(getContext(),arrayListproduct);
                        recycler_product.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recycler_product.setAdapter(adapter_product);

                        // Click listener
                        adapter_product.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {

                                Product_Sec_Fragment product_sec_fragment=new Product_Sec_Fragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("product_name",""+arrayListproduct.get(pos).getName());
                                bundle.putString("product_name2",""+arrayListproduct.get(pos).getName2());
                                bundle.putString("product_name3",""+arrayListproduct.get(pos).getName3());
                                bundle.putString("product_name4",""+arrayListproduct.get(pos).getName4());
                                bundle.putString("product_name5",""+arrayListproduct.get(pos).getName5());
                                bundle.putString("imgp",""+arrayListproduct.get(pos).getImg());

                                bundle.putString("key_product",""+arrayListproduct.get(pos).getId());

                                product_sec_fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.profile_container_main,product_sec_fragment).addToBackStack(null).commit();

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
                Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestproduct);



        return view;
    }
}