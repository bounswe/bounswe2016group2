package com.example.bounswegroup2.eatright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodComment;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.User;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;

import org.w3c.dom.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Comment page activity.
 */
public class CommentPageActivity extends AppCompatActivity {
    private ArrayList<FoodComment> lofc= new ArrayList<FoodComment>();
    private ArrayList<FoodComment> lofc2= new ArrayList<FoodComment>();
    /**
     * The List view.
     */
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_page);
        listView = (ListView) findViewById(R.id.comm_list);
        lofc = (ArrayList<FoodComment>) getIntent().getExtras().getSerializable("comments");
        getUsrnameForComments();
    }

    private void getUsrnameForComments() {
        for (final FoodComment fc:lofc) {
            ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
            Call<User> cb = test.getUserWithId("Token "+ Constants.API_KEY,fc.getUser());
            cb.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    String uname = user.getFirstName()+" "+user.getLastName();
                    /*FoodComment fc2 = new FoodComment();
                    fc2.setComment(fc.getComment());
                    fc2.setUser(fc.getUser());
                    fc2.setUsername(uname);
                    fc2.setFood(fc.getFood());*/
                    fc.setUsername(uname);
                    lofc2.add(fc);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        }
        CommentAdapter cmn = new CommentAdapter(CommentPageActivity.this,lofc2);
        listView.setAdapter(cmn);
    }
}
