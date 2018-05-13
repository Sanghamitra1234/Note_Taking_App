package com.example.sang.loginsystemv;

import android.content.Intent;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class userActivity extends AppCompatActivity {
    private final static String note_url = "https://sangie1234.000webhostapp.com/note_print.php";
    private RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    ArrayList<NoteModel> NoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        NoteList = new ArrayList<>();
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        noteAdapter = new NoteAdapter(this, NoteList);
        recyclerView.setAdapter(noteAdapter);

        loadProducts();
    }


    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, note_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray note = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < note.length(); i++) {

                                //getting product object from json array
                                JSONObject noteObject = note.getJSONObject(i);

                                int note_id = noteObject.getInt("note_id");
                                String note_name = noteObject.getString("note_name");
                                String note_content = noteObject.getString("note_content");

                                NoteModel noteModel = new NoteModel(note_id, note_name, note_content);
                                NoteList.add(noteModel);
                            }
                            noteAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.note_add:
                Intent intent = new Intent(this, com.example.sang.loginsystemv.NoteActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
