package com.jjj.myntra.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjj.myntra.Activities.MainActivity;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import com.jjj.myntra.RecyclerAdapters.RecyclerAdapter_Explore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    ArrayList<Modalclass> arrayListexplore;
    RequestQueue requestQueue;
    RecyclerAdapter_Explore adapterExplore;
    RecyclerView recyclerView;

    //Toolbar
    ImageView search,bag;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        sharedPreferences=getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        recyclerView = view.findViewById(R.id.recycler_explore);

        //Toolbar
        search=view.findViewById(R.id.search_luxe);
        bag=view.findViewById(R.id.bag_luxe);

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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new SearchFragment()).addToBackStack(null).commit();

            }
        });

        requestQueue= Volley.newRequestQueue(getContext());
        arrayListexplore=new ArrayList<>();

        StringRequest stringRequestexplore=new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/exploreapi", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        Modalclass modalclass=new Modalclass();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                       /* String name=jsonObject.getString("ename");
                        modalclass.setName(name);*/

                        String ID=jsonObject.getString("id");
                        modalclass.setId(ID);

                        String img="http://api.karanvarma.link/upload/"+jsonObject.getString("eimg");
                        modalclass.setImg(img);
                        arrayListexplore.add(modalclass);

                        // For Recycler View First

                        adapterExplore=new RecyclerAdapter_Explore(getContext(),arrayListexplore);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(adapterExplore);

                        // Click listener
                        adapterExplore.onitemclicklistener(new Recyclerclicklistener() {
                            @Override
                            public void onclick(int pos) {

                             /*   SubHomeFragment subHomeFragment=new SubHomeFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("key",""+pos);
                                subHomeFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.hide_container,subHomeFragment).addToBackStack(null).commit();
*/
                                Toast.makeText(getContext(), "Myntra Luxe", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequestexplore);


        return view;
    }
}