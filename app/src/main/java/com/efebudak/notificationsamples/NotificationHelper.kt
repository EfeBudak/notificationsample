package com.efebudak.notificationsamples

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

const val CHANNEL_NAME = "Sample Channel Name"
const val CHANNEL_DESCRIPTION = "Sample Channel Description"
const val CHANNEL_ID = "Channel Id (but as String)"

const val INTERACTIVE_NOTIFICATION_ID = 13
fun createInteractiveNotification(context: Context, count: Int): Notification =
    NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification_sample)
        .setColor(ContextCompat.getColor(context, R.color.colorAccent))
        .setLargeIcon(
            generateBitmapFromVectorDrawable(
                context,
                R.drawable.ic_notification_sample
            )
        )
        .setColorized(true)
        .setContentTitle("Interactive Notification Title")
        .setContentText("Interactive Notification Text Count: $count")
        .setContentIntent(InteractiveNotificationBroadcastReceiver.newPendingIntent(context))
        .addAction(
            R.drawable.ic_launcher_sample_foreground,
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

fun generateBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(context, drawableId) as Drawable
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}