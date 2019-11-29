package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopWatchAct extends AppCompatActivity {

    Button btnstart, btnstop;
    ImageView icanchor;
    Chronometer timerHere;
    Animation roundingalone;

    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "Simplified Ccoding";
    private static final String CHANNEL_DESC = "Simplified Coding Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        icanchor = findViewById(R.id.icanchor);
        timerHere = findViewById(R.id.timerHere);

        btnstop.setAlpha(0);

        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);

        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        btnstart.setTypeface(MMedium);
        btnstop.setTypeface(MMedium);
        timerHere.setTypeface(MMedium);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
                timerHere.setBase(SystemClock.elapsedRealtime());
                timerHere.start();
                displayNotification();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.clearAnimation();
                btnstart.animate().alpha(1).setDuration(300).start();
                btnstop.animate().alpha(0).translationY(-80).setDuration(300).start();
                timerHere.stop();
            }
        });



    }

    private void displayNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.timer)
                .setContentTitle("The Stopwatch Has Started")
                .setContentText("Do your workout seriously")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat mNotification = NotificationManagerCompat.from(this);
        mNotification.notify(1, mBuilder.build());
    }
}
