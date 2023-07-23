package com.jjj.myntra.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jjj.myntra.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {

    EditText ed1,ed2,ed3,ed4,ed5;
    TextInputLayout textInputemail,textInputpass;
    CheckBox checkBox;
    TextView already;
    Button btn;

    BottomSheetDialog sheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ed1=view.findViewById(R.id.regname);
        ed2=view.findViewById(R.id.regemail);
        ed3=view.findViewById(R.id.regpassword);
        ed4=view.findViewById(R.id.mobile);
        ed5=view.findViewById(R.id.address);
        already=view.findViewById(R.id.alreadyHaveAccount);
        checkBox=view.findViewById(R.id.checkBoxreg);

        textInputemail=view.findViewById(R.id.inputemail);
        textInputpass=view.findViewById(R.id.inputpassword);

        sheetDialog=new BottomSheetDialog(getContext(),R.style.bottomsheetstyle);
        ed3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password=charSequence.toString();
                if (password.length()>=8){
                    Pattern pattern=Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher=pattern.matcher(password);
                    boolean isPwdContainsSpeChar = matcher.find();
                    if (isPwdContainsSpeChar){
                        textInputpass.setHelperText("Strong Password");
                        textInputpass.setError("");
                    }
                    else {
                        textInputpass.setHelperText("");
                        textInputpass.setError("Weak Password. Include minimum 1 special char.");
                    }

                } else if (password.isEmpty()) {

                    textInputpass.setError("Empty Password");

                } else {
                    textInputpass.setHelperText("Enter Minimum 8 char");
                    textInputpass.setError("");

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn=view.findViewById(R.id.regbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue queue = Volley.newRequestQueue(getContext());

                String name = ed1.getText().toString();
                String email = ed2.getText().toString();
                String pass = ed3.getText().toString();
                String mobile = ed4.getText().toString();
                String address = ed5.getText().toString();

                //For Validations
                //registerUser(view);

                boolean valid = true;
                String val = ed2.getText().toString();

                if (val.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(val).matches()){
                    ed2.setError("Wrong Email");
                    valid = false;
                } else {
                    ed2.setError(null);
                }

              //  closefragment();

                String url= "http://api.karanvarma.link/Webservice1.asmx/registerapi?n="+name+"&e="+email+"&p="+pass+"&ph="+mobile+"&ad="+address;
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        //closefragment();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

                queue.add(stringRequest);

            }
        });

        //alreadyHaveAccount...

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closefragment();

            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkBox.isChecked()){

                    btn.setEnabled(true);

                }
                else {

                    btn.setEnabled(false);
                }
            }
        });


        return view;
    }

    private void closefragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    //For Validations
    private Boolean validateName() {
        String val = ed1.getText().toString();
        if (val.isEmpty()) {
            ed1.setError("Field cannot be empty");
            return false;
        }
        else if (val.length() >= 15) {
            ed1.setError("Username too long");
            return false;
        }
        else {
            ed1.setError(null);
            return true;
        }
    }
    private Boolean validateEmail() {
        /*String val = ed2.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            ed2.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern)) {
            ed2.setError("Invalid email address");
            return false;
        }
        else {
            ed2.setError(null);
            return true;
        }*/

        Boolean valid = true;
        String val = ed2.getText().toString();

        if (val.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            ed2.setError("Bhosdike Email to Sahi dall le");
            valid = false;
        } else {
            ed2.setError(null);
        }

        return true;
    }
    private Boolean validatePhoneNo() {
        String val = ed4.getText().toString();
        if (val.isEmpty()) {
            ed4.setError("Field cannot be empty");
            return false;
        }
        else {
            ed4.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = ed3.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            ed3.setError("Field cannot be empty");
            return false;
        }
        else {
            ed3.setError(null);
            return true;
        }
    }

    public void registerUser(View view) {
        if (!validateName() | !validatePhoneNo() | !validatePassword()) {
            return;
        }
    }
    }