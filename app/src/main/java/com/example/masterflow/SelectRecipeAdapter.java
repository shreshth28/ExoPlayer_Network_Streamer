package com.example.masterflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectRecipeAdapter extends RecyclerView.Adapter<SelectRecipeAdapter.SelectRecipeViewHolder> {

    Context mContext;
    List<String> mMylist;
    RecipeClickListener myRecipeClickListener;

    public SelectRecipeAdapter(Context mContext, List<String> mMylist) {
        this.mContext = mContext;
        this.mMylist = mMylist;
    }

    interface RecipeClickListener{
        void onRecipeClickListener(int index);
    }

    @NonNull
    @Override
    public SelectRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.steps,parent,false);
        return new SelectRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectRecipeViewHolder holder, int position) {
        holder.stepTV.setText(mMylist.get(position));
    }

    @Override
    public int getItemCount() {
        if(mMylist!=null) {
            return mMylist.size();
        }
        else{
            return 0;
        }
    }

    public class SelectRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView stepTV;

        public SelectRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTV=itemView.findViewById(R.id.steps_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedIndex=getAdapterPosition();
            myRecipeClickListener.onRecipeClickListener(clickedIndex);
        }
    }
    public void setData(List localList)
    {
        mMylist=localList;
        notifyDataSetChanged();
    }
}
