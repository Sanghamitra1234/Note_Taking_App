package com.example.sang.loginsystemv;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText edUserName,edPassword, edAge;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUserName= findViewById(R.id.edName);
        edPassword=findViewById(R.id.edPassword);
        edAge=findViewById(R.id.edAge);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=edUserName.getText().toString();
                final String password=edPassword.getText().toString();
                final int age=Integer.parseInt(edAge.getText().toString());



                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");

                            if(success){
                                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                RegisterActivity.this.startActivity(intent);
                          }
                          else{
                                AlertDialog.Builder alert=new AlertDialog.Builder(RegisterActivity.this);

                                alert.setMessage("register failed").setNegativeButton("RETRY",null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest=new RegisterRequest(name,password,age,responseListener);
                RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
