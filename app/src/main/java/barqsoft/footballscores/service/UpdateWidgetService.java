package barqsoft.footballscores.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

/**
 * Created by Aditya on 04-Mar-16.
 */
public class UpdateWidgetService extends IntentService {

    public static final String TAG = UpdateWidgetService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateWidgetService(String name) {
        super(name);
    }

    public UpdateWidgetService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

        int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        for (int i = 0; i < appWidgetIds.length; i++) {
            int id = appWidgetIds[i];

            Intent inten = new Intent(this.getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, inten, 0);

            RemoteViews views = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.widget_frame);


            views.setTextViewText(R.id.home_name,"Home");
            views.setTextViewText(R.id.score_textview,"0-1");
            views.setTextViewText(R.id.away_name,"Away");
            views.setTextViewText(R.id.data_textview,"This is some data");

            views.setOnClickPendingIntent(R.id.frame_widget, pendingIntent);

            appWidgetManager.updateAppWidget(id, views);

        }
    }
}
