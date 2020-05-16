package com.example.masterflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeListFragment extends Fragment{


    RecyclerView recyclerView;
    public RecipeListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rv=inflater.inflate(R.layout.fragment_master_list,container,false);
        recyclerView=rv.findViewById(R.id.recipe_list_rv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //check this
        Item myCustomItem=MainActivity.mainList.get(0);
        SelectRecipeAdapter mAdapter=new SelectRecipeAdapter(getActivity(),myCustomItem.getShortDesciprtion());
        recyclerView.setAdapter(mAdapter);
        return rv;
    }


}
