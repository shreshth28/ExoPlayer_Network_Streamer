package com.example.masterflow;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    private String json;
    Context context;


    private int id;
    private int ingredientLength;
    private int stepsLength;
    private String name;
    private List<Integer> quantity;
    private List<String> measure;
    private List<String> ingredient;
    private List<String> shortDesciprtion;
    private List<String> description;
    private List<String> videoURL;
    private List<String> thumbnailURL;
    private int servings;
    private String image;


    public void loadJsonFromAsset(Context context)
    {
        this.context=context;
        json=null;
        try {
            InputStream inputStream=context.getAssets().open("baking.json");
            int size=inputStream.available();
            byte[] buffer=new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json=new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> formatJson()
    {
        List<Item> objectList=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(json);
            for(int x=0;x<jsonArray.length();x++)
            {
                quantity=new ArrayList<>();
                measure=new ArrayList<>();
                ingredient=new ArrayList<>();
                shortDesciprtion=new ArrayList<>();
                description=new ArrayList<>();
                videoURL=new ArrayList<>();
                thumbnailURL=new ArrayList<>();
                JSONObject item=jsonArray.getJSONObject(x);
                id=item.getInt("id");
                name=item.getString("name");
                JSONArray ingredients=item.getJSONArray("ingredients");
                ingredientLength=ingredients.length();
                for(int y=0;y<ingredientLength;y++)
                {
                    JSONObject subItem=ingredients.getJSONObject(y);
                    quantity.add(subItem.getInt("quantity"));
                    measure.add(subItem.getString("measure"));
                    ingredient.add(subItem.getString("ingredient"));
                }
                JSONArray steps=item.getJSONArray("steps");
                stepsLength=steps.length();
                for(int z=0;z<stepsLength;z++)
                {
                    shortDesciprtion.add(steps.getJSONObject(z).getString("shortDescription"));
                    description.add(steps.getJSONObject(z).getString("description"));
                    videoURL.add(steps.getJSONObject(z).getString("videoURL"));
                    thumbnailURL.add(steps.getJSONObject(z).getString("thumbnailURL"));
                }
                servings=item.getInt("servings");
                image=item.getString("image");
                Item object=new Item(id,ingredientLength,stepsLength,name,quantity,measure,ingredient,shortDesciprtion,description,videoURL,thumbnailURL,servings,image);
                objectList.add(object);
                quantity=null;
                measure=null;
                ingredient=null;
                shortDesciprtion=null;
                description=null;
                videoURL=null;
                thumbnailURL=null;

            }
            return objectList;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(JSONParser.class.getSimpleName(),e.getMessage());
        }
        return null;
    }
}
