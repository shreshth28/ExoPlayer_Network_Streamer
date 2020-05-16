package com.example.masterflow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SelectRecipeListActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_recipe_list);
        int index=getIntent().getIntExtra("index",0);
        RecipeListFragment.indexSteps=index;
    }

}
