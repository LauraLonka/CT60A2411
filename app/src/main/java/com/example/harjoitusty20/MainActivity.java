package com.example.harjoitusty20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CalculateCO2Fragment.CalculateCO2FragmentListener, ResultsFragment.ResultsFragmentListener {
    // NAVIGATION DRAWER
    private DrawerLayout drawer;

    // CALCULATE CO2 FRAGMENT
    private CalculateCO2Fragment calculateCO2Fragment;

    Log l = new Log();

    // MAIN CODE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CALCULATE CO2 FRAGMENT
        calculateCO2Fragment = new CalculateCO2Fragment();

        // NAVIGATION DRAWER
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    // NAVIGATION DRAWER
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_calculate_co2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalculateCO2Fragment()).commit();
                break;
            case R.id.nav_log:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogFragment(l)).commit();
                break;
            case R.id.nav_charts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChartsFragment()).commit();
                break;
            case R.id.nav_log_out:
                Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_close_app:
                Toast.makeText(this, "Closing app", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // NAVIGATION DRAWER
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onInputASent(CharSequence input) {

    }

}