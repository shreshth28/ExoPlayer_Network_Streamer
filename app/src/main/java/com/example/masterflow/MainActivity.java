package com.example.masterflow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnFoodItemClickListener{

    RecyclerView foodListRv;
   public static boolean isTablet;
   static List<Item>mainList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONParser jsonParser=new JSONParser();
        jsonParser.loadJsonFromAsset(this);
        mainList=jsonParser.formatJson();
        foodListRv=findViewById(R.id.food_list_rv);
        try {
            isTablet = getResources().getBoolean(R.bool.isTablet);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if(isTablet)
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
            foodListRv.setLayoutManager(gridLayoutManager);
        }
        else{
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            foodListRv.setLayoutManager(linearLayoutManager);
        }

        ItemAdapter adapter=new ItemAdapter(this,mainList);
        foodListRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(int index) {
        Intent newIntent=new Intent(MainActivity.this,SelectRecipeListActivity.class);
        newIntent.putExtra("index",index);
        startActivity(newIntent);
    }
}
