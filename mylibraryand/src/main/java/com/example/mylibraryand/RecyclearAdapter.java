package com.example.mylibraryand;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclearAdapter extends RecyclerView.Adapter<RecyclearAdapter.ViewHolder> {

    public ArrayList<String> jokelist;
    Context context;

    public RecyclearAdapter(Context context, ArrayList<String> jokelist) {
        this.context = context;
        this.jokelist = jokelist;
    }



    @Override
    public RecyclearAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recyclear,parent,false);

        return new RecyclearAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclearAdapter.ViewHolder holder, int position) {
        holder.txt_joke.setText(jokelist.get(position));

    }

    @Override
    public int getItemCount() {
        return jokelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_joke;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_joke = (TextView) itemView.findViewById(R.id.txt_joke);
        }
    }
}
