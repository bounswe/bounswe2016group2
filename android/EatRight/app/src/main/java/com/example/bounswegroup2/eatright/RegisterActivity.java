package com.example.bounswegroup2.eatright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    private Button expandFormBut;
    private EditText phoneText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        expandFormBut = (Button) findViewById(R.id.button_to_server);
        phoneText = (EditText) findViewById(R.id.reg_phone);
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
}
