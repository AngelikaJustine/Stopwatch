package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvSplash, tvSubSplash;
    Button btnget;

    Animation atg, btgone, btgtwo;
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSplash = findViewById(R.id.tvSplash);
        tvSubSplash = findViewById(R.id.tvSubSplash);
        btnget = findViewById(R.id.btnget);
        ivSplash = findViewById(R.id.ivSplash);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        btgone = AnimationUtils.loadAnimation(this, R.anim.btgone);
        btgtwo = AnimationUtils.loadAnimation(this, R.anim.btgtwo);

        ivSplash.startAnimation(atg);
        tvSplash.startAnimation(btgone);
        tvSubSplash.startAnimation(btgone);
        btnget.startAnimation(btgtwo);

        btnget.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, StopWatchAct.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");

        tvSplash.setTypeface(MRegular);
        tvSubSplash.setTypeface(MLight);
        btnget.setTypeface(MMedium);

        Calendar calender = Calendar.getInstance();
        calender.set(calender.HOUR_OF_DAY,1);
        calender.set(calender.MINUTE, 13);
        calender.set(calender.SECOND,30);

        Intent intent = new Intent(getApplicationContext(), Notification_Receiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(), 10000, pendingIntent);
//        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1*60*1000, pendingIntent);

//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(),
//                1000 * 60 * 1, pendingIntent);
    }
}
