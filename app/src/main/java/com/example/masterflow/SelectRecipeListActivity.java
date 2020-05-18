package com.example.masterflow;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SelectRecipeListActivity extends AppCompatActivity implements RecipeListFragment.Callback{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_recipe_list);
        int index=getIntent().getIntExtra("index",0);
        RecipeListFragment.indexSteps=index;
        Context context = getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context, IngredientAppWidgetProvider.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.ingredient_widget_list_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void callbacklistener(int index) {
            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.video_player, videoPlayerFragment)
                    .commit();
            VideoPlayerFragment.videoIndex = index;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
