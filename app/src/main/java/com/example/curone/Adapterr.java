package com.example.curone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Adapterr extends RecyclerView.Adapter<Adapterr.MyViewHolder> {
    private List<Model> mList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public Adapterr(List<Model>mList, Activity activity){
        this.mList = mList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public Adapterr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapterr.MyViewHolder holder, int position) {
        final Model data = mList.get(position);
        holder.edit_teks.setText(data.getPostingan());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        EditText edit_teks;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            edit_teks = itemView.findViewById(R.id.edit_teks);
            card = itemView.findViewById(R.id.card);
        }
    }
}
