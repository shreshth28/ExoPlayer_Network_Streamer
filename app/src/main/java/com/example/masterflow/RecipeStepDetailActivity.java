package com.example.masterflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class RecipeStepDetailActivity extends AppCompatActivity{

    Button nextBtn;
    Button prevBtn;
    TextView detailInstructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_step_detail);
        nextBtn=findViewById(R.id.next_btn);
        prevBtn=findViewById(R.id.prev_btn);
        if(!MainActivity.isTablet) {
            VideoPlayerFragment.videoIndex = getIntent().getIntExtra("index", 0);
            executeDisplay();
        }
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VideoPlayerFragment.videoIndex<MainActivity.mainList.get(RecipeListFragment.indexSteps).getStepsLength()-1) {
                    ++VideoPlayerFragment.videoIndex;
                    VideoPlayerFragment.releasePlayer();
                    executeDisplay();
                }
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VideoPlayerFragment.videoIndex>0)
                {
                    VideoPlayerFragment.releasePlayer();
                    --VideoPlayerFragment.videoIndex;
                    executeDisplay();
                }
            }
        });


    }



    public void executeDisplay()
    {
        detailInstructions = findViewById(R.id.detail_instruction);
        VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.video_player, videoPlayerFragment)
                .commit();

        detailInstructions.setText(MainActivity.mainList.get(RecipeListFragment.indexSteps).getDescription().get(VideoPlayerFragment.videoIndex));
    }
}

