package com.example.masterflow;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class RecipeStepDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView detailInstructions;
        setContentView(R.layout.activity_recipe_step_detail);
        if(!MainActivity.isTablet) {
            detailInstructions = findViewById(R.id.detail_instruction);
            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.video_player, videoPlayerFragment)
                    .commit();
            VideoPlayerFragment.videoIndex = getIntent().getIntExtra("index", 0);
            detailInstructions.setText(MainActivity.mainList.get(RecipeListFragment.indexSteps).getDescription().get(VideoPlayerFragment.videoIndex));

        }

    }
}
