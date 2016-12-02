package com.example.bounswegroup2.eatright;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.FoodAdapter;
import com.example.bounswegroup2.Utils.QueryWrapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bounswegroup2.eatright.R.layout.nav_header_user_home;

public class UserHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView userRecommendations;
    private TextView userHistory;
    private RecyclerView mHistoryRecyclerView;
    private RecyclerView mRecomRecyclerView;
    private LinearLayoutManager mHistoryLinearLayoutManager;
    private LinearLayoutManager mRecomLinearLayoutManager;
    private FoodAdapter foodAdapterH;
    private FoodAdapter foodAdapterR;
    private DrawerLayout drawer;
    private NavigationView mNavigationView;
    private TextView mShowName;
    private FloatingActionButton mFab ;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    ArrayList<Food> HistoryFoods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        userRecommendations = (TextView) findViewById(R.id.user_home_recommendations);
        userHistory = (TextView) findViewById(R.id.user_home_history);
        userRecommendations.setText(R.string.user_page_recommendations);
        userHistory.setText(R.string.user_page_histroy);

        //TODO will be activated after the main implementation
//        mFab = (FloatingActionButton) findViewById(R.id.fab);
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Bundle bundle = getIntent().getExtras();
        initSecondaryViews(bundle);
        initFoodHistory();
    }

    public void initSecondaryViews(Bundle bundle){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        View header=mNavigationView.getHeaderView(0);
        mShowName = (TextView)header.findViewById(R.id.textView_email);

        if(bundle!=null) {
            String un = bundle.getString("username");
            if(un != null)
                mShowName.setText(un);
            else
                mShowName.setText(bundle.getString("email"));
        }
    }

    public void initFoodHistory(){
        mHistoryRecyclerView = (RecyclerView) findViewById(R.id.History_recycler);
        mHistoryRecyclerView.setHasFixedSize(true);
        mHistoryLinearLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        mHistoryLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHistoryRecyclerView.setLayoutManager(mHistoryLinearLayoutManager);
        getFoods();

    }

    public void initFoodRecommendations(){
        //TODO will process recommended foods and will add to the recycler view
    }

    public void getFoods(){
        //TODO will be implemented
        ApiInterface foodCall = ApiInterface.retrofit.create(ApiInterface.class);
        QueryWrapper query = new QueryWrapper();

        Call<List<Food>> cb = foodCall.getFoods(query.getOptions());
        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                HistoryFoods = new ArrayList<Food>();
                HistoryFoods = (ArrayList<Food>) response.body();
                foodAdapterH = new FoodAdapter(getApplicationContext(),HistoryFoods);
                foodAdapterH.setOnItemClickListener(new FoodAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Food food = HistoryFoods.get(position);
                        Intent intent = new Intent(UserHomeActivity.this, FoodPageActivity.class);
                        intent.putExtra("food", food);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position, View v) {

                    }
                });
                mHistoryRecyclerView.setAdapter(foodAdapterH);


            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
            }
        });
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_view, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SettingsFragment settingsFragment = new SettingsFragment();
                //settingsFragment.setArgs(query);
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_user_home,
                        settingsFragment,settingsFragment.getTag()).commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


        });
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_food) {
            FoodAddFragment foodAddFragment = new FoodAddFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,foodAddFragment,foodAddFragment.getTag()).commit();
        } else if (id == R.id.nav_adv_search){
            FoodSearchFragment foodSearchFragment = new FoodSearchFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,
                    foodSearchFragment ,foodSearchFragment .getTag()).commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_cons_hist) {
            ConsHistFragment consHistFragment = ConsHistFragment.newInstance("SWE","451");
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,consHistFragment,
                                               consHistFragment.getTag()).commit();
        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,
                                               settingsFragment,settingsFragment.getTag()).commit();


        } else if (id == R.id.nav_send) {

        }
        findViewById(R.id.yigitLinear).setVisibility(View.GONE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
