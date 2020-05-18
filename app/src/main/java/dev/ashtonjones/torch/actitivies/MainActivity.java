package dev.ashtonjones.torch.actitivies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import dev.ashtonjones.torch.R;
import dev.ashtonjones.torch.ui.AlarmReceiver;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    Toolbar topAppToolbar;

    // Navigation Component references
    private NavHostFragment navHostFragment;

    private NavController navController;

    private AppBarConfiguration appBarConfiguration;

    private BottomNavigationView bottomNavigationView;

    private AlarmManager alarmManager;
    private Intent alarmIntent;
    private PendingIntent alarmPendingIntent;
    private NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private SharedPreferences sharedPreferences;

    boolean alarmHasAlreadyBeenSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setSupportActionBar(topAppToolbar);

        initUIComponentsWithNavigation();

        showOrHideToolbarAndBottomNavigation();

        setDefaultPreferences();

        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

        alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);

        alarmPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, 0);

        createNotificationChannel();


    }


    private void initViews() {

        topAppToolbar = findViewById(R.id.top_app_bar_toolbar_main_activity);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home_fragment_dest, R.id.progress_fragment_dest, R.id.torch_discovery_answers_summary_fragment_dest).build();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    private void initUIComponentsWithNavigation() {

        NavigationUI.setupWithNavController(topAppToolbar, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.sign_out_button) {

            signOut();

        }


        if(item.getItemId() == R.id.tips_menu_item) {

            openMediumTipsArticle();

        }

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void showOrHideToolbarAndBottomNavigation() {

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                // Hide the Toolbar in the Sign In Fragment and Welcome Fragment
                if (destination.getId() == R.id.sign_in_fragment_dest || destination.getId() == R.id.welcome_fragment_dest || destination.getId() == R.id.set_torch_fragment_dest) {

                    topAppToolbar.setVisibility(View.GONE);


                } else {

                    topAppToolbar.setVisibility(View.VISIBLE);


                }

                if (destination.getId() == R.id.home_fragment_dest || destination.getId() == R.id.progress_fragment_dest || destination.getId() == R.id.torch_discovery_answers_summary_fragment_dest) {

                    bottomNavigationView.setVisibility(View.VISIBLE);

                } else {

                    bottomNavigationView.setVisibility(View.GONE);

                }
            }
        });

    }


    private void signOut() {

        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Signed out", Toast.LENGTH_SHORT).show();

                navController.navigate(R.id.sign_in_nav_graph);
            }
        });

    }

    public void createNotificationChannel() {

        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Notifications", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.argb(255, 51, 57, 89));
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Check in reminder to log your torch progress");
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }

    private void setAlarm() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0
        );

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);

        alarmHasAlreadyBeenSet = true;


    }

    private void setDefaultPreferences() {

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        if(sharedPreferences.getBoolean("notifications_pref_key", true) == true) {

            if(!alarmHasAlreadyBeenSet) {

                setAlarm();

                alarmHasAlreadyBeenSet = true;

            }

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("notifications_pref_key")) {

            boolean notificationsOn = sharedPreferences.getBoolean("notifications_pref_key", true);


            if (notificationsOn) {

                Toast.makeText(getApplicationContext(), "Notifications are on", Toast.LENGTH_SHORT).show();

                setAlarm();

            } else {

                Toast.makeText(getApplicationContext(), "Notifications are off", Toast.LENGTH_SHORT).show();

                notificationManager.cancelAll();

                if(alarmManager != null) {

                    alarmManager.cancel(alarmPendingIntent);

                }

            }

        }

    }

    private void openMediumTipsArticle() {

        String articleUrl = "https://medium.com/@TJgrapes/introducing-torch-5ed629a84378";

        Uri articleUri = Uri.parse(articleUrl);

        Intent intent = new Intent(Intent.ACTION_VIEW, articleUri);

        startActivity(intent);

    }
}
