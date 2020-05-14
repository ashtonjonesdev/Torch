package dev.ashtonjones.torch.ui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.navigation.NavDeepLinkBuilder;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dev.ashtonjones.torch.R;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 0;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotification(context);

    }

    private void deliverNotification(Context context) {

        PendingIntent pendingIntent = new NavDeepLinkBuilder(context).setGraph(R.navigation.main_nav_graph).setDestination(R.id.check_in_fragment_dest).createPendingIntent();

        /// Create the notification

        // Create the notification object using the NotificationCompat.Builder class
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Set the behavior of the notification
        notification
                .setSmallIcon(R.drawable.ic_adjust_black_24dp)
                .setContentTitle("Hey " + firebaseUser.getDisplayName() + "!")
                .setContentText("How did today go?")
                .addAction(R.drawable.ic_trending_up_black_24dp, "Check in", pendingIntent)
                .setSmallIcon(R.drawable.app_icon_torch)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Deliver the notification
        notificationManager.notify(NOTIFICATION_ID, notification.build());



    }
}


