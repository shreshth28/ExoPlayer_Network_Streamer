package com.example.masterflow;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class IngredientAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId: appWidgetIds){
            Intent intent=new Intent(context,MainActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);



            Intent serviceIntent = new Intent(context,IngredientWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.ingredient_widget);
            views.setOnClickPendingIntent(R.id.ingredient_widget_btn,pendingIntent);
            views.setOnClickPendingIntent(R.id.ingredient_widget_item_text,pendingIntent);
            views.setRemoteAdapter(R.id.ingredient_widget_list_view,serviceIntent);
            views.setEmptyView(R.id.ingredient_widget_list_view,R.id.ingredient_widget_empty_view);

//            Bundle appWidgetOptions=appWidgetManager.getAppWidgetOptions(appWidgetId);
            views.setViewVisibility(R.id.ingredient_widget_btn, View.VISIBLE);
            appWidgetManager.updateAppWidget(appWidgetId,views);

        }
    }


    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.ingredient_widget);
        appWidgetManager.updateAppWidget(appWidgetId,views);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
