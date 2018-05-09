package com.example.sang.loginsystemv;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest
{
    public static final String Register_url="https://sangie1234.000webhostapp.com/register.php";

    private Map<String,String>params;

    public RegisterRequest(String user,String Password,int age, Response.Listener<String> listener) {
        super(Method.POST, Register_url, listener,null);
        params=new HashMap<>();

        params.put("user",user);
        params.put("password",Password);
        params.put("age",age+" ");


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}