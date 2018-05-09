package com.example.sang.loginsystemv;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    public static final String Register_url = "https://sangie1234.000webhostapp.com/login.php";

    private Map<String, String> params;

    public LoginRequest(String user, String Password, Response.Listener<String> listener) {
        super(Method.POST, Register_url, listener, null);
        params = new HashMap<>();

        params.put("user", user);
        params.put("password", Password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
