package com.example.c4q.capstone.userinterface.alerts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.TempUserActivity;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventInviteActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;

/**
 * Created by amirahoxendine on 3/30/18.
 */

public class InviteNotifications {
    private static final int NOTIFICATION_ID = 555;
    private Context context;
    private PendingIntent pendingIntent;
    private NotificationManager notificationManager;

    public InviteNotifications (Context context){
        this.context = context;


    }
    public InviteNotifications(String title, String description, Context context, String eventId) {
        this.context = context;
        initClass(eventId);
        initNot(title, description);
    }

    public void initClass(String eventId) {
        Intent intent = new Intent(context, EventInviteActivity.class);
        intent.putExtra("eventID", eventId);
        intent.putExtra("eventType", "notnew");
        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        pendingIntent = PendingIntent.getActivity(context, requestID, intent, flags);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void initNot(String title, String description) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_grupp_icon_24)
                .setContentTitle(title)
                .setContentText(description)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    public void showNotificationVoteComplete(String eventId){

        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("eventID", eventId);
        intent.putExtra("eventType", "notnew");
        pendingIntent = PendingIntent.getActivity(context, requestID, intent, flags);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_grupp_icon_24)
                .setContentTitle("Your Friends Voted!")
                .setContentText("Mirror Mirror on the wall, see what venue is best of all. \n See what venue your friends will host your event.")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .build();
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

}
