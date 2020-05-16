package com.example.masterflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeListFragment extends Fragment{

    RecyclerView recyclerView;
    static int indexSteps;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rv=inflater.inflate(R.layout.fragment_master_list,container,false);
        recyclerView=rv.findViewById(R.id.recipe_list_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Item myCustomItem=MainActivity.mainList.get(indexSteps);
        SelectRecipeAdapter mAdapter=new SelectRecipeAdapter(getContext(),myCustomItem.getShortDesciprtion());
        recyclerView.setAdapter(mAdapter);
        return rv;
    }

}
