package com.jjj.myntra.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Cart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter_Cart adapter_cart;
    SharedPreferences sharedPreferences;
    TextView textView;

    StringRequest stringRequestcartpage;
    RequestQueue queue;
    ArrayList<Modalclass> arrayListcart;

    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";
    private static final String KEY_ADDRESS = "address";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        //SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayout);
       // TextView textView = (TextView)view.findViewById(R.id.tv1);

        // Refresh  the layout

        sharedPreferences= getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String id = sharedPreferences.getString(KEY_ID,null);
        String Address=sharedPreferences.getString(KEY_ADDRESS,null);
        textView=view.findViewById(R.id.txt_cart);
        textView.setText(Address);

        recyclerView=view.findViewById(R.id.recycler_cart);
        arrayListcart = new ArrayList<>();
        queue = Volley.newRequestQueue(getContext());

        stringRequestcartpage = new StringRequest(Request.Method.GET,"http://api.karanvarma.link/Webservice1.asmx/displaycartapi?userid="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i++) {

                        Modalclass modalclass = new Modalclass();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String cartid = jsonObject.getString("Id");
                        modalclass.setId(cartid);

                        String cimg = jsonObject.getString("cartimg");
                        modalclass.setImg(cimg);

                        String cname = jsonObject.getString("cartname");
                        modalclass.setName(cname);

                        String cdes = jsonObject.getString("cartdes");
                        modalclass.setName2(cdes);

                        String cpcut = jsonObject.getString("cartpricecut");
                        modalclass.setName3(cpcut);

                        String cprice = jsonObject.getString("cartprice");
                        modalclass.setName4(cprice);

                        String coffer = jsonObject.getString("cartoffer");
                        modalclass.setName5(coffer);

                        String cbanner = jsonObject.getString("cartbanner");
                        modalclass.setName6(cbanner);

                        String csize = jsonObject.getString("cartsize");
                        modalclass.setName7(csize);

                        arrayListcart.add(modalclass);

                        adapter_cart = new RecyclerAdapter_Cart(getContext(), arrayListcart);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                        recyclerView.setAdapter(adapter_cart);


                        adapter_cart.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {

                           StringRequest stringRequestdeletecart = new StringRequest(Request.Method.GET,"http://api.karanvarma.link/Webservice1.asmx/deletecartapi?Id="+cartid, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();

                                    }

                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Toast.makeText(getContext(),""+error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(stringRequestdeletecart);

                              getFragmentManager().beginTransaction().replace(R.id.refresh_container,new CartFragment()).commit();

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

                Toast.makeText(getContext(),""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequestcartpage);

        return view;
    }
}