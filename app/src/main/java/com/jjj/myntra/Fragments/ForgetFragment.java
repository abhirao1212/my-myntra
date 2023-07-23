package com.jjj.myntra.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jjj.myntra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForgetFragment extends Fragment {

    Button button;
    EditText number,pass;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_MOB = "mobile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forget, container, false);
        sharedPreferences= getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);


        button=view.findViewById(R.id.button);
        number=view.findViewById(R.id.editTextPhone);
        pass=view.findViewById(R.id.editTextTextPassword2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(getContext());

                String mobile=number.getText().toString();
                String password=pass.getText().toString();

                String url2 = "http://api.karanvarma.link/Webservice1.asmx/mobileapi?ph=" + mobile;

                StringRequest stringRequestmobile = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String ms = jsonObject.getString("forget");

                            if (ms.equals("1")){

                                String url = "http://api.karanvarma.link/Webservice1.asmx/forgetapi?p=" + password + "&ph=" + mobile;
                                StringRequest stringRequestforget = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        Toast.makeText(getContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                                queue.add(stringRequestforget);

                            }else {

                                Toast.makeText(getActivity(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();

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

                queue.add(stringRequestmobile);
            }
        });


        return view;
    }
}