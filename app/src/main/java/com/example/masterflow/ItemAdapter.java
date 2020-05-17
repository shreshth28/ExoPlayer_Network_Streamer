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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{


    Context context;
    List<Item> myList;
    OnFoodItemClickListener myListener;

    public ItemAdapter(Context context, List<Item> myList) {
        this.context = context;
        this.myList = myList;
        this.myListener= (OnFoodItemClickListener) context;
    }

    interface OnFoodItemClickListener{
        void onItemClick(int index);
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.foodItemTV.setText(myList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.food_item) TextView foodItemTV;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedIndex=getAdapterPosition();
            myListener.onItemClick(clickedIndex);

        }
    }

    public void setData(List changedList)
    {
        myList=changedList;
        notifyDataSetChanged();
    }
}
