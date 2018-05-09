package com.example.sang.loginsystemv;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
 EditText edUserName,edPassword;
 Button btnLogin;
 TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUserName= findViewById(R.id.edLoginUserName);
        edPassword=findViewById(R.id.edLoginPassword);
        btnLogin=findViewById(R.id.btnLogin);

        tvRegister=findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,com.example.sang.loginsystemv.RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                final String name=edUserName.getText().toString();
                final String password=edPassword.getText().toString();

                Response.Listener<String> responseListener =new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");

                            if(success){
                                Intent intent=new Intent(MainActivity.this,com.example.sang.loginsystemv.userActivity.class);
                                MainActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                                alert.setMessage("Login Failed").setNegativeButton("Register If not",null).create().show();

                               //Intent intent=new Intent(MainActivity.this,com.example.sang.loginsystemv.RegisterActivity.class);
                                //MainActivity.this.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequest loginRequest=new LoginRequest(name,password,responseListener);
                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });

    }
}
