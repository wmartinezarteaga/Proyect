package com.example.proyect.core.database.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.proyect.R;

import java.util.Random;


public class NotificationsServices {
    private static final String CHANNEL_ID = "tareas";


    public static void createNotificationChannel(Context context, String message, String title) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = title;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(message);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
           NotificationManager notificationManager =  context.getSystemService(NotificationManager.class);
           notificationManager.createNotificationChannel(channel);
        }else {
        recordatorio(context,title,message);
        }
    }


    public static void recordatorio(@NonNull Context context, String title, String message) {

        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

      //  Intent intent = new Intent(context, MainActivity.class);
      //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
      //  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        int color = 0x00FF00;

        NotificationCompat.Builder notification = new NotificationCompat.Builder(
                context, CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.notifitcation_icon))
                .setSmallIcon(R.drawable.notifitcation_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setColor(color)
                .setWhen( System.currentTimeMillis() )
             //   .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_SOUND) //*Sonido!
                .setVibrate(new long[] {100, 250, 100, 500})
                .setAutoCancel(true);

        //.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" + context.getPackageName() +"/" + R.raw.barney_sonido));

        Random r = new Random();
        int randomNo = r.nextInt(100+1);
        nManager.notify(randomNo, notification.build());
    }

}
