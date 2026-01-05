package com.example.final_semesterptoject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    Button AU, GS, Appointment, drs;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the hamburger menu button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Video player setup
            VideoView videoView1 = findViewById(R.id.videoView);
            Uri uri1 = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1);
            videoView1.setVideoURI(uri1);

            MediaController mediaController1 = new MediaController(this);
            mediaController1.setAnchorView(videoView1);
            videoView1.setMediaController(mediaController1);
            videoView1.start();

            // Assign buttons
            AU = findViewById(R.id.button1); // About Us button
            GS = findViewById(R.id.button2); // Get Started button
            Appointment = findViewById(R.id.button3); // Appointment button
            drs = findViewById(R.id.button4); // Doctors button

            return insets;
        });

        // Drawer layout setup
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.nav);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        // Initialize Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // About Us button handler
    public void aboutus(View v) {
        Intent intent = new Intent(MainActivity.this, Aboutus.class);
        startActivity(intent);
    }

    // Get Started button handler
    public void getstarted(View v) {
        Intent intent = new Intent(MainActivity.this, GetStarted.class);
        startActivity(intent);
    }

    // Appointment button handler
    public void Appointment(View v) {
        Intent intent = new Intent(MainActivity.this, com.example.final_semesterptoject.Appointmentform.class);
        startActivity(intent);
    }

    // Doctors button handler
    public void doctors(View v) {
        Intent intent = new Intent(MainActivity.this, DoctorListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker and move the camera
        LatLng location = new LatLng(33.5651, 73.0169);
        mMap.addMarker(new MarkerOptions().position(location).title("Pakistan(rawalpindi)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.home1) {
            // Already on home screen
        } else if (id == R.id.how) {
            startActivity(new Intent(this, GetStarted.class));
        } else if (id == R.id.dr) {
            startActivity(new Intent(this, DoctorListActivity.class));
        } else if (id == R.id.reg) {
            startActivity(new Intent(this, RegistrationActivity.class));
        } else if (id == R.id.appointment) {
            startActivity(new Intent(this, Appointmentform.class));
        } else if (id == R.id.evaluation) {
            Toast.makeText(this, "Evaluation not implemented yet", Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
