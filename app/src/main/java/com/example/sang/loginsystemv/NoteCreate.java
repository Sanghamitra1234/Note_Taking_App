package com.example.sang.loginsystemv;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NoteCreate extends StringRequest
{
    public static final String Register_url="https://sangie1234.000webhostapp.com/note.php";

    private Map<String,String>params;

    public NoteCreate(String note_name,String note_content, Response.Listener<String> listener) {

        super(Method.POST, Register_url, listener,null);
        params=new HashMap<>();

        params.put("note_name",note_name);
        params.put("note_content",note_content);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
