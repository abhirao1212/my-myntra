package com.jjj.myntra.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.jjj.myntra.Fragments.AnimationCart;
import com.jjj.myntra.Fragments.CartFragment;
import com.jjj.myntra.Fragments.CategoryFragment;
import com.jjj.myntra.Fragments.ExploreFragment;
import com.jjj.myntra.Fragments.HeaderFragment;
import com.jjj.myntra.Fragments.HomeFragment;
import com.jjj.myntra.Fragments.Home_blank;
import com.jjj.myntra.Fragments.NotificationFragment;
import com.jjj.myntra.Fragments.ProfileFragment;
import com.jjj.myntra.Fragments.ProfileSecFragment;
import com.jjj.myntra.Fragments.RegisterFragment;
import com.jjj.myntra.Fragments.SearchFragment;
import com.jjj.myntra.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView actionbar,search,bell,bag;

    //Login
    BottomSheetDialog sheetDialog;
    EditText ed1,ed2;
    TextView textView,textView2;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    //Bottom Navigation Bar
    BubbleNavigationLinearView bubbleNavigation;

    Fragment selectedFragment = null;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String Name=sharedPreferences.getString(KEY_NAME,null);
        String ee=sharedPreferences.getString("e","0");
        if (ee!="0"){

         //  getSupportFragmentManager().beginTransaction().replace(R.id.container_header,new HeaderFragment()).addToBackStack(null).commit();

        }

        frameLayout=findViewById(R.id.bottom_container_main);

        actionbar=findViewById(R.id.icon);
        search=findViewById(R.id.search_main);
        bell=findViewById(R.id.notify_main);
        bag=findViewById(R.id.bag_main);

        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigation);
        toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //ToolBar
        actionbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = sharedPreferences.getString(KEY_ID,null);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url=" http://api.karanvarma.link/Webservice1.asmx/emptycart2api?userid="+id;

                StringRequest stringRequestempty = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            /*if (response==null){
                                getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                            }*/

                            if(jsonArray.length()==0)
                            {

                                getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                            }
                            else {

                                getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new CartFragment()).addToBackStack(null).commit();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new AnimationCart()).addToBackStack(null).commit();

                    }
                });

                queue.add(stringRequestempty);



            }
        });

        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new NotificationFragment()).addToBackStack(null).commit();

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new SearchFragment()).addToBackStack(null).commit();


            }
        });

       /* ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/

       //getSupportFragmentManager().beginTransaction().add(R.id.container_demo,new HomeFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();

                if (id==R.id.categories){

                    getSupportFragmentManager().beginTransaction().replace(R.id.profile_container_main,new CategoryFragment()).addToBackStack(null).commit();

                }
                else if(id==R.id.orders){

                    Toast.makeText(MainActivity.this, "Orders", Toast.LENGTH_SHORT).show();

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        View headerview = navigationView.getHeaderView(0);
        textView = headerview.findViewById(R.id.header_name);
        textView2=headerview.findViewById(R.id.header_text);

         /* RelativeLayout header = headerview.findViewById(R.id.relativeLayout);
          header.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

              // getSupportFragmentManager().beginTransaction().replace(R.id.profile_container,new ProfileFragment()).commit();
               Toast.makeText(MainActivity.this, "Go To Login Page", Toast.LENGTH_SHORT).show();

               sheetDialog=new BottomSheetDialog(MainActivity.this,R.style.bottomsheetstyle);
               View view1= LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_bottomsheet,null);

               ed1=view1.findViewById(R.id.editTextTextEmailAddress);
               ed2=view1.findViewById(R.id.editTextTextPassword);

               //For Redirecting To SignUp Page
               view1.findViewById(R.id.sheet_signup).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       getSupportFragmentManager().beginTransaction().replace(R.id.hide_container,new RegisterFragment()).addToBackStack(null).commit();
                       sheetDialog.dismiss();
                   }
               });

               sheetDialog.setContentView(view1);
               sheetDialog.show();

               drawerLayout.closeDrawer(GravityCompat.START);
           }
       });*/

        if (ee!="0") {

            textView.setText(Name);
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.GONE);

        }else {
            textView2.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }

        //Bottom Navigation Bar
         bubbleNavigation = findViewById(R.id.bubbleNavigation);
        // getSupportFragmentManager().beginTransaction().replace(R.id.karan, new Home_blank()).commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.container_demo,
                new HomeFragment()).commit();

         bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        replace1(new HomeFragment());
                        frameLayout.setVisibility(View.GONE);
                        break;
                    case 1:
                        replace(new CategoryFragment());
                        frameLayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        replace(new ExploreFragment());
                        frameLayout.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        replace(new ProfileFragment());
                        frameLayout.setVisibility(View.VISIBLE);
                        break;
                }


            }
        });

        /*bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.karan, new Home_blank()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                  // Fragment temp = null;
                int id=item.getItemId();

                if (id == R.id.mHome)
                {
                    replace1(new HomeFragment());
                    frameLayout.setVisibility(View.GONE);
                }

                else  if (id==R.id.mcategories)
                {

                    replace(new CategoryFragment());
                    frameLayout.setVisibility(View.VISIBLE);

                }
                else if (id==R.id.mexplore)
                {

                    replace(new ExploreFragment());
                    frameLayout.setVisibility(View.VISIBLE);

                }
                else {

                    replace(new ProfileFragment());
                    frameLayout.setVisibility(View.VISIBLE);

                }

                return true;

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.mHome);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.tools_toolbar,menu);
        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return false;
            }
        };
        return super.onCreateOptionsMenu(menu);

    }

    //Bottom Navigation Bar
/*    public void loadfrag(Fragment fragment, boolean flag){

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(flag)
            ft.add(R.id.container_demo,fragment);

        else
            ft.replace(R.id.container_demo,fragment);
            ft.commit();

    }*/

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    //For Toolbar Items
       @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.bag:


            break;


        }


        return super.onOptionsItemSelected(item);
    }

    private  void replace (Fragment fragment){

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.bottom_container_main,fragment);
        fragmentTransaction.commit();

    }

        private void replace1 (Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_demo, fragment);
        fragmentTransaction.commit();

    }
}