package com.example.bounswegroup2.eatright;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bounswegroup2.Models.User;
import com.example.bounswegroup2.Utils.API;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.SessionManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.internal.framed.Http2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

import static com.example.bounswegroup2.Utils.Constants.API_KEY;
import static com.example.bounswegroup2.Utils.Constants.CONNECTION_TIMEOUT;
import static com.example.bounswegroup2.Utils.Constants.READ_TIMEOUT;
import static com.example.bounswegroup2.Utils.Constants.BASE_URL;
import static com.example.bounswegroup2.Utils.Constants.emailRegex;
import static com.example.bounswegroup2.Utils.Constants.passRegex;

public class RegisterActivity extends AppCompatActivity {
    private Button expandFormBut;
    private EditText phoneText;
    private Button regBut;
    private EditText passText;
    private EditText passAgainText;
    private EditText userNameText;
    private EditText emailText;
    // private UserRegTask mAuthTask = null;
    private View mRegFormView;
    private View mregProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        expandFormBut = (Button) findViewById(R.id.button_to_server);
        expandFormBut.setPaintFlags(expandFormBut.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        phoneText = (EditText) findViewById(R.id.reg_phone);
        regBut = (Button) findViewById(R.id.reg_sign_up_button);
        passText = (EditText) findViewById(R.id.password_reg);
        passAgainText = (EditText) findViewById(R.id.password_reg_again);
        userNameText = (EditText) findViewById(R.id.reg_username);
        emailText = (EditText) findViewById(R.id.email_reg);
        mRegFormView = (View) findViewById(R.id.reg_form);
        mregProgressView = (View) findViewById(R.id.reg_progress);
    }

    public void expandFormForServer(View v) {
        if (v.getId() == expandFormBut.getId()) {
            if (phoneText.getVisibility() == View.GONE) {
                phoneText.setVisibility(View.VISIBLE);
                expandFormBut.setText(R.string.button_to_user_text);
            } else if (phoneText.getVisibility() == View.VISIBLE) {
                phoneText.setVisibility(View.GONE);
                expandFormBut.setText(R.string.button_to_server_text);
            }
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mregProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mregProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mregProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mregProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void regButClicked(View v) {
        if (v.getId() == regBut.getId()) {
            // Reset errors.
            emailText.setError(null);
            passText.setError(null);
            passAgainText.setError(null);
            userNameText.setError(null);
            boolean cancel = false;
            View focusView = null;

            String email = emailText.getText().toString();
            String pass = passText.getText().toString();
            String passAgain = passAgainText.getText().toString();
            String phone = phoneText.getText().toString();
            String uname = userNameText.getText().toString();

            if (!isPasswordValid(pass)) {
                passText.setError(getString(R.string.error_invalid_password));
                focusView = passText;
                cancel = true;
            }
            if (!pass.equals(passAgain)) {
                passAgainText.setError(getString(R.string.error_incorrect_password_again));
                focusView = passAgainText;
                cancel = true;
            }

            if (!isEmailValid(email)) {
                emailText.setError(getString(R.string.error_invalid_email));
                focusView = emailText;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                if (!phone.isEmpty()) {

                } else {
                    //  mAuthTask = new UserRegTask(email,pass,uname);
                    //  mAuthTask.execute();
                }
            }

        }
    }


    private boolean isEmailAvailable(String email) {
        //TODO .Check email from db
        return true;
    }

    private boolean isUserNameAvailable(String email) {
        //TODO .Check uname from db
        return true;
    }

    private boolean isEmailValid(String email) {
        return email.matches(emailRegex);
    }

    private boolean isPasswordValid(String password) {
        return password.matches(passRegex);
    }

    private void registerWithRetro(final String email, final String pass, String uname) {
        ApiInterface apiService = API.getClient().create(ApiInterface.class);
        Call<User> call = apiService.createUser(email, pass, uname, API_KEY);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int statusCode = response.code();
                if (statusCode == 1) {
                    SessionManager.setPreferences(RegisterActivity.this, "usermail", email);
                    SessionManager.setPreferences(RegisterActivity.this, "userpass", pass);
                    Intent intent = new Intent(RegisterActivity.this, UserHomeActivity.class);
                    startActivity(intent);
                    RegisterActivity.this.finish();
                } else {

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

}
