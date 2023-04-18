package com.example.weatherly;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.weatherly.databinding.ActivityMainBinding;
import com.example.weatherly.rest.FetchService;
import com.example.weatherly.rest.RetrofitProvider;
import com.example.weatherly.rest.repo.WeatherRepo;
import com.example.weatherly.rest.utils.UtilsKt;
import com.example.weatherly.ui.search.SearchViewModelFactory;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.sameep.iiflassignment.db.ArticlesDb;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        LocationListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1001;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private ActivityViewModel activityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityViewModel = new ViewModelProvider(this
                , new ActivityViewModelFactory(new WeatherRepo(RetrofitProvider.INSTANCE.getRetrofit().create(FetchService.class)
                , ArticlesDb.Companion.getDatabase(getApplicationContext()).articleDao()
                , getBaseContext())))
                .get(ActivityViewModel.class);

        activityViewModel.printLog("FirstLog");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (UtilsKt.isInternetAvailable(getApplicationContext())) {


            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            // Create the LocationRequest object
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(600 * 1000);// 10 seconds, in milliseconds
        }

        setUpObservers();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            // Permission already granted.
            if (UtilsKt.isInternetAvailable(getApplicationContext()))
                startLocationUpdates();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        // Stop location updates
        if (UtilsKt.isInternetAvailable(getApplicationContext()))
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Restart location updates if the app is resumed
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (UtilsKt.isInternetAvailable(getApplicationContext()))
                startLocationUpdates();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop location updates if the app is paused
        if (UtilsKt.isInternetAvailable(getApplicationContext()))
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted.
                    startLocationUpdates();
                } else {
                    // Permission denied.
                }
                return;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //change toolbar title
    private void setUpObservers() {

        activityViewModel.observeTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.appBarMain.toolbar.setTitle(s);
            }
        });

        activityViewModel.getLocGeoCode().observe(this, cityData -> {
            activityViewModel.updateLoc(cityData.get(0).getName());
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            // Do something with the location.
            if (UtilsKt.isInternetAvailable(getApplicationContext())) {


                Location location = locationResult.getLastLocation();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                activityViewModel.updateLatlong(latitude, longitude);
                Log.d("TAGActivityMain", "Location: " + latitude + ", " + longitude);
                activityViewModel.getLocRevGeoCode(new Coordinates(latitude, longitude));
                activityViewModel.printLog("$LAT = " + latitude + ", LONG = " + longitude);
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            }
        }
    };

    protected void startLocationUpdates() {
        // Request location updates
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (UtilsKt.isInternetAvailable(getApplicationContext()))
                mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                        mLocationCallback,
                        Looper.myLooper());
        }
    }


}