package com.example.bounswegroup2.eatright;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.bounswegroup2.Models.AteFoodUserless;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodTag;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Models.UserMore;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.OnBackPressedListener;
import com.example.bounswegroup2.Utils.SessionManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;

/**
 * The type User home activity.
 */
public class UserHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView userRecommendations;
    private TextView userHistory;
    private View headerView;
    private TextView tvName;
    private TextView tvRating;
    private ListView myFoodLv;
    private ArrayList<Food> lof = new ArrayList<>();
    private DrawerLayout drawer;
    private NavigationView mNavigationView;
    private TextView mShowName;
    private FloatingActionButton mFab ;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    /**
     * The Tag list.
     */
    ArrayList<String> tagList = new ArrayList<>();
    /**
     * The Foodies.
     */
    ArrayList<Food> foodies = new ArrayList<>();
    /**
     * The On back pressed listener.
     */
    protected OnBackPressedListener onBackPressedListener;

    /**
     * The M tf regular.
     */
    protected Typeface mTfRegular;
    /**
     * The M tf light.
     */
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

       // recomPager.setPageMargin(pageMargin);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);


        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        userRecommendations = (TextView) findViewById(R.id.user_home_recommendations);

        userRecommendations.setText(R.string.user_page_recommendations);

        Bundle bundle = getIntent().getExtras();
        //TODO will be activated after the main implementation

        //Bundle bundle = getIntent().getExtras();
        initSecondaryViews(bundle);
        editForServerLogin();
        //getMe();
        initiateFoodSearch();
        fillMyFoodList();
        initFoodRecommendations();

    }


    /**
     * Sets on back pressed listener.
     *
     * @param onBackPressedListener the on back pressed listener
     */
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    /**
     * Init secondary views.
     *
     * @param bundle the bundle
     */
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


    /**
     * Init food recommendations.
     */
    public void initFoodRecommendations(){
        //TODO will process recommended foods and will add to the recycler view
        myFoodLv = (ListView) findViewById(R.id.list_my_recommended);
        LayoutInflater inflater = getLayoutInflater();
        headerView = inflater.inflate(R.layout.food_list_header,null);
        tvName = (TextView) headerView.findViewById(R.id.food_list_header_name);
        tvRating = (TextView) headerView.findViewById(R.id.food_list_header_rating);
        headerView.findViewById(R.id.food_list_header_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        final FoodsAdapter adapter = new FoodsAdapter(getApplicationContext(), foodies);
        myFoodLv.addHeaderView(headerView);
        myFoodLv.setAdapter(adapter);
        myFoodLv.setDivider(ContextCompat.getDrawable(getApplicationContext(),android.R.color.black));
        myFoodLv.setDividerHeight(1);
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isSorted()){
                    Collections.sort(lof,Food.czToA);
                    adapter.setSorted(false);
                    tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                }else {
                    Collections.sort(lof,Food.caToZ);
                    adapter.setSorted(true);
                    tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                }
                adapter.setFoods(lof);
                adapter.notifyDataSetChanged();
            }
        });
        tvRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isSorted()){
                    Collections.sort(lof,Food.czToARating);
                    adapter.setSorted(false);
                    tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                }else {
                    Collections.sort(lof,Food.caToZRating);
                    adapter.setSorted(true);
                    tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                }
                adapter.setFoods(foodies);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initiateFoodSearch(){
        String token = "Token " + SessionManager.getPreferences(this,"token");
        ApiInterface[] test = {ApiInterface.retrofit.create(ApiInterface.class)};
        Call<TotalUserHistory> cb = test[0].getuserFoodHistory(token);
        cb.enqueue(new Callback<TotalUserHistory>() {
            @Override
            public void onResponse(Call<TotalUserHistory> call, Response<TotalUserHistory> response) {
                if(response.isSuccessful()){
                    TotalUserHistory userHistory = response.body();
                    ArrayList<AteFoodUserless> foodList = (ArrayList<AteFoodUserless>) userHistory.getTotal().getAteFoods();

                    for (AteFoodUserless ate : foodList){
                        List<FoodTag> tags = new ArrayList<FoodTag>();
                        tags = ate.getFood().getTags();
                        for (int i = 0; i < tags.size(); i++) {
                         tagList.add(tags.get(i).getName());
                            System.out.println("MOGO- " + tags.get(i).getName());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<TotalUserHistory> call, Throwable t) {
                System.out.println(t.getCause());
                System.out.println(t.getMessage());
            }
        });
    }

    private void fillMyFoodList() {
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("tag",tagList);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
        Call<List<Food>> cb = test.searchFood(Constants.API_KEY,body);
        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foodies = (ArrayList<Food>) response.body();
                System.out.println(foodies);
            }
            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });


    }


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


        } else if (id == R.id.nav_send) {
            ConsHistFragment consHistFragment = new ConsHistFragment().newInstance("Mogo","Fogo");
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_user_home,consHistFragment,consHistFragment.getTag()).commit();
        }
        findViewById(R.id.yigitLinear).setVisibility(View.GONE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class PageAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "Daily Macro","Daily Micro", "Macro History","Energy History" };

        /**
         * Instantiates a new Page adapter.
         *
         * @param fm the fm
         */
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
                    f = MicroPieChartFragment.newInstance(pos);
                    break;
                case 2:
                    f = FullHistoryFrag.newInstance(pos);
                    break;
                case 3:
                    f = fullHistoryEnergyFragment.newInstance(pos);
                    break;
            }

            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
    private class RecommendationPager extends FragmentPagerAdapter {

        private final String[] TITLES = { "Daily Macro","Daily Micro", "Total" };

        /**
         * Instantiates a new Recommendation pager.
         *
         * @param fm the fm
         */
        public RecommendationPager(FragmentManager fm) {
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
