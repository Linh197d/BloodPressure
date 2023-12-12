package blood.pressure.fingerprint.scanner.bpmonitor.util.utils

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import blood.pressure.fingerprint.scanner.bpmonitor.R

class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            if (intent.extras != null) {
                val notificationManager =
                    ContextCompat.getSystemService(
                        context,
                        NotificationManager::class.java
                    ) as NotificationManager

                notificationManager.sendNotification(context.getString(R.string.notification_info), context)
            }
        }
    }
}