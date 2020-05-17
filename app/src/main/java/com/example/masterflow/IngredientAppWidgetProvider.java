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

    public static final String ACTION_TOAST="actionRefresh";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId: appWidgetIds){
            Intent buttonIntent=new Intent(context,MainActivity.class);
            PendingIntent buttonPendingIntent=PendingIntent.getActivity(context,0,buttonIntent,0);




            Intent serviceIntent = new Intent(context,IngredientWidgetService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));

            Intent clickIntent=new Intent(context,IngredientAppWidgetProvider.class);
            clickIntent.setAction(ACTION_TOAST);
            PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                    0,clickIntent,0);



            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.ingredient_widget);
            views.setOnClickPendingIntent(R.id.ingredient_widget_btn,buttonPendingIntent);
            views.setRemoteAdapter(R.id.ingredient_widget_list_view,serviceIntent);
            views.setEmptyView(R.id.ingredient_widget_list_view,R.id.ingredient_widget_empty_view);
            views.setPendingIntentTemplate(R.id.ingredient_widget_list_view,clickPendingIntent);

//            Bundle appWidgetOptions=appWidgetManager.getAppWidgetOptions(appWidgetId);
            views.setViewVisibility(R.id.ingredient_widget_btn, View.VISIBLE);
            appWidgetManager.updateAppWidget(appWidgetId,views);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.ingredient_widget_list_view);

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

    @Override
    public void onReceive(Context context, Intent intent) {
        if(ACTION_TOAST.equals(intent.getAction()))
        {
            int appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
            AppWidgetManager appWidgetManager= AppWidgetManager.getInstance(context);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,R.id.ingredient_widget_list_view);
        }
        super.onReceive(context, intent);
    }
}
