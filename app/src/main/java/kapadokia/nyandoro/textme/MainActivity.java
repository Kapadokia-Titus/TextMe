package kapadokia.nyandoro.textme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button notify_button;
    private static final String PRIMARY_CHANNEL_ID= "primary_notification_channel";
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private EditText notificationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        notify_button = findViewById(R.id.notify);
        notificationText = findViewById(R.id.notifyText);
        notify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

    }

    public void createNotificationChannel() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Kapadokia's Notification", NotificationManager
                    .IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(notificationText.getText().toString());
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    // a helper method
    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Notification From kapadokia")
                .setContentText("Unajiskiaje msee")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);

        return notifyBuilder;
    }
}