package com.example.masterflow;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class RecipeStepDetailActivity extends AppCompatActivity {

    TextView detailInstructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);

        detailInstructions=findViewById(R.id.detail_instruction);
        VideoPlayerFragment.videoIndex=getIntent().getIntExtra("index",0);
        detailInstructions.setText(MainActivity.mainList.get(RecipeListFragment.indexSteps).getDescription().get(VideoPlayerFragment.videoIndex));
        FragmentManager fragmentManager=getSupportFragmentManager();

        VideoPlayerFragment videoPlayerFragment=new VideoPlayerFragment();
        fragmentManager.beginTransaction().add(R.id.video_player,videoPlayerFragment)
                .commit();

    }
}
