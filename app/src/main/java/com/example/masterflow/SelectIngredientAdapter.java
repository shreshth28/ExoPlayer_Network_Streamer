package com.example.masterflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SelectIngredientAdapter extends RecyclerView.Adapter<SelectIngredientAdapter.SelectIngredientViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    public SelectIngredientAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public SelectIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.ingredient,parent,false);
        return new SelectIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectIngredientViewHolder holder, int position) {
        holder.measure.setText("Measure: "+ingredients.get(position).getMeasure());
        holder.ingredient.setText("Ingredient: "+ingredients.get(position).getIngredient());
        holder.quantity.setText("Quantity: "+ingredients.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class SelectIngredientViewHolder extends RecyclerView.ViewHolder {
        TextView quantity;
        TextView ingredient;
        TextView measure;
        public SelectIngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity=itemView.findViewById(R.id.quantity);
            ingredient=itemView.findViewById(R.id.ingredient);
            measure=itemView.findViewById(R.id.measure);
        }
    }
}
