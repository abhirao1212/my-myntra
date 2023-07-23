package com.jjj.myntra.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jjj.myntra.Activities.MainActivity;
import com.jjj.myntra.Activities.SplashActivity;
import com.jjj.myntra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {

    Button btn;
    BottomSheetDialog sheetDialog;

    EditText ed1,ed2;

    ImageView crossp;
    SharedPreferences sharedPreferences;
    RequestQueue queue;
    StringRequest stringRequestlogin,stringRequestprofile;


    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOB = "mobile";
    private static final String KEY_EMAIL = "email";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences= getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String ee=sharedPreferences.getString("e","0");
        if (ee!="0"){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container_main,new ProfileSecFragment()).commit();
        }

        btn=view.findViewById(R.id.btn_profile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sheetDialog=new BottomSheetDialog(getContext(),R.style.bottomsheetstyle);

                View view1=LayoutInflater.from(getContext()).inflate(R.layout.fragment_bottomsheet,null);

                ed1=view1.findViewById(R.id.editTextTextEmailAddress);
                ed2=view1.findViewById(R.id.editTextTextPassword);

                crossp=view1.findViewById(R.id.cross_profile);
                crossp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sheetDialog.dismiss();
                    }
                });

                //For SignUp
                view1.findViewById(R.id.sheet_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String m=ed1.getText().toString();
                        String p=ed2.getText().toString();

                        String papi="http://api.karanvarma.link/Webservice1.asmx/profileapi?e="+m;

                        queue = Volley.newRequestQueue(getContext());
                        stringRequestlogin = new StringRequest(Request.Method.GET, "http://api.karanvarma.link/Webservice1.asmx/loginapi?e=" + m + "&p=" + p + "", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                    try {
                                        JSONArray jsonArray = new JSONArray(response);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String ms = jsonObject.getString("logindemo");

                                        if (ms.equals("1")) {

                                            SharedPreferences.Editor editor=sharedPreferences.edit();
                                            editor.putString("e",ed1.getText().toString());
                                            editor.commit();

                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.bottom_container_main,new ProfileSecFragment()).addToBackStack(null).commit();

                                           //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container_header,new HeaderFragment()).addToBackStack(null).commit();

                                            sheetDialog.dismiss();

                                        } else {
                                            Toast.makeText(getActivity(), "Invalid User Id & Password", Toast.LENGTH_SHORT).show();
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

                        queue.add(stringRequestlogin);


                        stringRequestprofile = new StringRequest(Request.Method.GET,papi, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i=0; i<jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        String id = jsonObject.getString("id");
                                        String nm = jsonObject.getString("Name");
                                        String es = jsonObject.getString("Email");
                                        String mo = jsonObject.getString("Mobile");
                                        String ad = jsonObject.getString("Address");

                                        SharedPreferences.Editor editor=sharedPreferences.edit();

                                        editor.putString(KEY_ID,id);
                                        editor.putString(KEY_NAME,nm);
                                        editor.putString(KEY_EMAIL,es);
                                        editor.putString(KEY_MOB,mo);
                                        editor.putString(KEY_ADDRESS,ad);
                                        editor.commit();

                                   //   Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();

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

                        queue.add(stringRequestprofile);

                    }
                });

                //For Redirecting To SignUp Page
                view1.findViewById(R.id.sheet_signup).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new RegisterFragment()).addToBackStack(null).commit();
                        sheetDialog.dismiss();
                    }
                });

                //For Forgetting Password
                view1.findViewById(R.id.forgotPassword).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new ForgetFragment()).addToBackStack(null).commit();
                        sheetDialog.dismiss();

                    }
                });

                sheetDialog.setContentView(view1);
                sheetDialog.show();
            }
        });



        return view;
    }
}