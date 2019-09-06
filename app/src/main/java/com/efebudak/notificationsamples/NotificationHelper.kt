package com.efebudak.notificationsamples

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

const val CHANNEL_NAME = "Sample Channel Name"
const val CHANNEL_DESCRIPTION = "Sample Channel Description"
const val CHANNEL_ID = "Channel Id (but as String)"

const val INTERACTIVE_NOTIFICATION_ID = 13
fun createInteractiveNotification(context: Context, count: Int): Notification =
    NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Interactive Notification Title")
        .setContentText("Interactive Notification Text Count: $count")
        .setContentIntent(InteractiveNotificationBroadcastReceiver.newPendingIntent(context))
        .addAction(
            R.drawable.ic_launcher_foreground,
            "Increment",
            InteractiveNotificationBroadcastReceiver.newPendingIntent(context)
        )
        .build()

fun cancelAllNotifications(context: Context) {
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancelAll()
}

fun notifyNotification(context: Context, notificationId: Int, notification: Notification) =
    NotificationManagerCompat.from(context).notify(notificationId, notification)