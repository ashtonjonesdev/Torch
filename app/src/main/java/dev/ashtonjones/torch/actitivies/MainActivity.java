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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.ashtonjones.torch.R;

public class MainActivity extends AppCompatActivity {

    Toolbar topAppToolbar;

    // Navigation Component references
    private NavHostFragment navHostFragment;

    private NavController navController;

    private AppBarConfiguration appBarConfiguration;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setSupportActionBar(topAppToolbar);

        initUIComponentsWithNavigation();

        showOrHideToolbar();


    }



    private void initViews() {

        topAppToolbar = findViewById(R.id.top_app_bar_toolbar_main_activity);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        navController = navHostFragment.getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.home_fragment_dest, R.id.progress_fragment_dest, R.id.torch_discovery_answers_summary_fragment_dest).build();

        bottomNavigationView = findViewById(R.id.bottom_navigation);

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

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

    private void showOrHideToolbar() {

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                // Hide the Toolbar in the Sign In Fragment and Welcome Fragment
                if(destination.getId() == R.id.sign_in_fragment_dest || destination.getId() == R.id.welcome_fragment_dest || destination.getId() == R.id.set_torch_fragment_dest) {

                    topAppToolbar.setVisibility(View.GONE);


                }

                else {

                    topAppToolbar.setVisibility(View.VISIBLE);


                }

                if(destination.getId() == R.id.home_fragment_dest || destination.getId() == R.id.progress_fragment_dest || destination.getId() == R.id.torch_discovery_answers_summary_fragment_dest) {

                    bottomNavigationView.setVisibility(View.VISIBLE);

                }

                else {

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

    @Override
    protected void onResume() {
        super.onResume();

    }
}
