package com.example.bounswegroup2.eatright;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.KeyEvent;
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
import android.view.WindowManager;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.UserMore;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.FoodAdapter;
import com.example.bounswegroup2.Utils.OnBackPressedListener;
import com.example.bounswegroup2.Utils.QueryWrapper;
import com.example.bounswegroup2.Utils.SessionManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    protected OnBackPressedListener onBackPressedListener;

    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ViewPager pager = (ViewPager) findViewById(R.id.history_pager);
        pager.setOffscreenPageLimit(3);

        PageAdapter a = new PageAdapter(getSupportFragmentManager());
        pager.setAdapter(a);

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        userRecommendations = (TextView) findViewById(R.id.user_home_recommendations);
        userHistory = (TextView) findViewById(R.id.user_home_history);
        userRecommendations.setText(R.string.user_page_recommendations);

        userHistory.setText(R.string.user_page_histroy);
        Bundle bundle = getIntent().getExtras();
        //TODO will be activated after the main implementation
//        mFab = (FloatingActionButton) findViewById(R.id.fab);
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //Bundle bundle = getIntent().getExtras();
       initSecondaryViews(bundle);
        editForServerLogin();
        //getMe();
      //  initFoodHistory();
    }


    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
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
       // mHistoryRecyclerView = (RecyclerView) findViewById(R.id.History_recycler);
//        mHistoryRecyclerView.setHasFixedSize(true);
//        mHistoryLinearLayoutManager = new GridLayoutManager(getApplicationContext(),1);
//        mHistoryLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mHistoryRecyclerView.setLayoutManager(mHistoryLinearLayoutManager);
      //  getFoods();

    }

    public void initFoodRecommendations(){
        //TODO will process recommended foods and will add to the recycler view
    }

    /*public void getFoods(){
        //TODO will be implemented
        ApiInterface foodCall = ApiInterface.retrofit.create(ApiInterface.class);
        QueryWrapper query = new QueryWrapper();

        Call<List<Food>> cb = foodCall.getFoods();
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
    }*/


    private void editForServerLogin(){
        ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
        Call<UserMore> cb2 = test2.getMe("Token "+Constants.API_KEY);
        cb2.enqueue(new Callback<UserMore>() {
            @Override
            public void onResponse(Call<UserMore> call, Response<UserMore> response) {
         /*      boolean b = response.body().getIsServer();
                Constants.isServer = b;
                if (b){
                    mNavigationView.getMenu().getItem(1).setVisible(false);
                }*/
            }

            @Override
            public void onFailure(Call<UserMore> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
            startActivity(new Intent(this,UserHomeActivity.class));
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
        } else if (id == R.id.nav_log_out) {
            SessionManager.clearCredet(this);
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
            UserHomeActivity.this.finish();
        } else if (id == R.id.nav_my_foods) {
            MyFoodsFragment myFoodsFragment = MyFoodsFragment.newInstance("SWE","451");
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,myFoodsFragment,
                    myFoodsFragment.getTag()).commit();
        } else if (id == R.id.nav_settings) {
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,
                                               settingsFragment,settingsFragment.getTag()).commit();


        } else if (id == R.id.nav_my_foods) {

        }
        findViewById(R.id.yigitLinear).setVisibility(View.GONE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class PageAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "Daily", "Total" };

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int pos) {
            Fragment f = null;

            switch(pos) {
                case 0:
                    f = pieChartFrag.newInstance(pos);
                    break;
                case 1:
                    f = FullHistoryFrag.newInstance(pos);
                    break;
            }

            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
    //Kendini çekme methodu kullanırız
    /*private void getMe(){
    ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<UserMore> call = test.getMe(Constants.API_KEY);
        call.enqueue(new Callback<UserMore>() {
            @Override
            public void onResponse(Call<UserMore> call, Response<UserMore> response) {
                UserMore um=response.body();
                Constants.user = um.getEmail();
            }

            @Override
            public void onFailure(Call<UserMore> call, Throwable t) {
            System.out.println("kendimi çekemedim");
            }
        });
    }*/
}
