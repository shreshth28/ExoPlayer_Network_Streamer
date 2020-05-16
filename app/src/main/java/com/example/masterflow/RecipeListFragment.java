package com.example.masterflow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends Fragment implements SelectRecipeAdapter.RecipeClickListener{

    RecyclerView recyclerView;
    static int indexSteps;
    RecyclerView ingredientRv;
    Callback mCallback;

    interface Callback{
        void callbacklistener(int index);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rv=inflater.inflate(R.layout.fragment_master_list,container,false);
        List<Ingredient> list=new ArrayList<>();
        recyclerView=rv.findViewById(R.id.recipe_list_rv);
        ingredientRv=rv.findViewById(R.id.ingredients_list_rv);
        LinearLayoutManager ingLayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ingredientRv.setLayoutManager(ingLayoutManager);
        Item myCustomItem=MainActivity.mainList.get(indexSteps);
        int length=myCustomItem.getIngredientLength();
        for(int y=0;y<length;y++)
        {
            list.add(new Ingredient(myCustomItem.getIngredient().get(y),myCustomItem.getQuantity().get(y),myCustomItem.getMeasure().get(y)));
        }
        SelectIngredientAdapter selectIngredientAdapter=new SelectIngredientAdapter(getActivity(),list);
        ingredientRv.setAdapter(selectIngredientAdapter);
        SelectRecipeAdapter mAdapter=new SelectRecipeAdapter(getActivity(),myCustomItem.getShortDesciprtion(),this);
        recyclerView.setAdapter(mAdapter);
        return rv;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RecipeClickListener Interface");
        }
    }

    @Override
    public void onRecipeClickListener(int index) {
        if(!MainActivity.isTablet) {
            Intent detailActivity = new Intent(getActivity(), RecipeStepDetailActivity.class);
            detailActivity.putExtra("index", index);
            startActivity(detailActivity);
        }
        else{
            mCallback.callbacklistener(index);
        }

    }
}
