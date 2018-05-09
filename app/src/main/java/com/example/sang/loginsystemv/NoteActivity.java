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

public class NoteActivity extends AppCompatActivity {
    EditText note_name,note_content;
    Button note_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);


        note_name=findViewById(R.id.Note_edName);
        note_content=findViewById(R.id.Note_edContent);

        note_btn=findViewById(R.id.note_savebtn);

        note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name =note_name.getText().toString();
                final String content =note_content.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                             boolean success=jsonObject.getBoolean("success");
                             if(success){
                                 Intent intent= new Intent(NoteActivity.this,userActivity.class);
                                 NoteActivity.this.startActivity(intent);
                             }
                             else{
                                 AlertDialog.Builder alert=new AlertDialog.Builder(NoteActivity.this);

                                 alert.setMessage("cannot be saved").setNegativeButton("RETRY",null).create().show();
                             }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                NoteCreate noteCreate=new NoteCreate(name,content,responseListener);
                RequestQueue queue= Volley.newRequestQueue(NoteActivity.this);
                queue.add(noteCreate);
            }

        });
    }
}
