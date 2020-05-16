package com.example.masterflow;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SelectRecipeListActivity extends AppCompatActivity implements RecipeListFragment.Callback{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_recipe_list);
        int index=getIntent().getIntExtra("index",0);
        RecipeListFragment.indexSteps=index;
    }

    @Override
    public void callbacklistener(int index) {
            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.video_player, videoPlayerFragment)
                    .commit();
            VideoPlayerFragment.videoIndex = index;
    }
}
