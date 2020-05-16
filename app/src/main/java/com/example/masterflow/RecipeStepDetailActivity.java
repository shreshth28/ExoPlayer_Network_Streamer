package com.example.masterflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class RecipeStepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);


        FragmentManager fragmentManager=getSupportFragmentManager();

        VideoPlayerFragment videoPlayerFragment=new VideoPlayerFragment();
        fragmentManager.beginTransaction().add(R.id.video_player,videoPlayerFragment)
                .commit();
    }
}
