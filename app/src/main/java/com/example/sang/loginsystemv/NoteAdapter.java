package com.example.sang.loginsystemv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    @NonNull
    public Context mctx;
    public List<NoteModel> NoteList;

    public NoteAdapter(Context mctx,List<NoteModel>NoteList) {
        this.mctx=mctx;
        this.NoteList=NoteList;
    }


    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.list_layout,null);
        NoteViewHolder noteViewHolder=new NoteViewHolder(view);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        NoteModel noteModel=NoteList.get(position);
        holder.textView_name.setText(noteModel.getNote_name());
        holder.textView_content.setText((CharSequence) noteModel.getNote_content());

    }

    @Override
    public int getItemCount() {
        return NoteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView textView_name,textView_content;

        public NoteViewHolder(View itemView) {
            super(itemView);

            textView_name= itemView.findViewById(R.id.textView_name);
            textView_content=itemView.findViewById(R.id.textView_content);
        }
    }
}
