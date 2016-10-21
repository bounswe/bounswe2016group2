package com.example.bounswegroup2.eatright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.bounswegroup2.Utils.Constants.emailRegex;
import static com.example.bounswegroup2.Utils.Constants.passRegex;

public class RegisterActivity extends AppCompatActivity {
    private Button expandFormBut;
    private EditText phoneText;
    private Button regBut;
    private EditText passText;
    private EditText passAgainText;
    private  EditText userNameText;
    private  EditText emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        expandFormBut = (Button) findViewById(R.id.button_to_server);
        phoneText = (EditText) findViewById(R.id.reg_phone);
        regBut = (Button) findViewById(R.id.reg_sign_up_button);
        passText = (EditText)findViewById(R.id.password_reg);
        passAgainText = (EditText)findViewById(R.id.password_reg_again);
        userNameText = (EditText)findViewById(R.id.reg_username);
        emailText = (EditText)findViewById(R.id.email_reg);
    }

    public void expandFormForServer(View v) {
        if (v.getId() == expandFormBut.getId()){
                if (phoneText.getVisibility() == View.GONE){
                        phoneText.setVisibility(View.VISIBLE);
                        expandFormBut.setText(R.string.button_to_user_text);
                }else if(phoneText.getVisibility() == View.VISIBLE){
                        phoneText.setVisibility(View.GONE);
                        expandFormBut.setText(R.string.button_to_server_text);
                }
        }
    }

    public void regButClicked(View v){
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
            if (!isEmailAvailable(email)) {
                //TODO .Asynctask for register case

                emailText.setError(getString(R.string.error_unavl_email));
                focusView = emailText;
                cancel = true;
            }
            if (!isUserNameAvailable(uname)) {
                //TODO  .Asynctask for register case

                userNameText.setError(getString(R.string.error_avl_uname));
                focusView = userNameText;
                cancel = true;
            }

            if(cancel){
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            }else{
                //TODO  .Registering will be done here
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
        //TODO: Replace this with your own logic
        return password.matches(passRegex);
    }
}
