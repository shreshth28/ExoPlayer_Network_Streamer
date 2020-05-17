package com.example.masterflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectRecipeAdapter extends RecyclerView.Adapter<SelectRecipeAdapter.SelectRecipeViewHolder> {

    Context mContext;
    List<String> mMylist;
    RecipeClickListener listener;

    public SelectRecipeAdapter(Context mContext, List<String> mMylist,RecipeClickListener listener) {
        this.mContext = mContext;
        this.mMylist = mMylist;
        this.listener=listener;
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

        @BindView(R.id.steps_textView)TextView stepTV;

        public SelectRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedIndex=getAdapterPosition();
            listener.onRecipeClickListener(clickedIndex);
        }
    }
    public void setData(List localList)
    {
        mMylist=localList;
        notifyDataSetChanged();
    }
}
