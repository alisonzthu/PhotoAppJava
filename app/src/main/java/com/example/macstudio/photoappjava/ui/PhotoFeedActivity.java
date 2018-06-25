package com.example.macstudio.photoappjava.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.macstudio.photoappjava.R;
import com.example.macstudio.photoappjava.networking.PhotoServiceClient;
import com.example.macstudio.photoappjava.networking.models.PhotoAppDataResponse;
import com.example.macstudio.photoappjava.viewModel.SharedViewModel;
import com.example.macstudio.photoappjava.viewModel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.macstudio.photoappjava.AppConstants.AUTHORIZATION;

public class PhotoFeedActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    @Inject
    SharedPreferences mSharedPreferences;
    //todo: remove
    @Inject
    PhotoServiceClient mPhotoServiceClient;
    //end of to remove
    @Inject
    ViewModelFactory viewModelFactory;

    SharedViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_feed_activity);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SharedViewModel.class);
//        mViewModel.

        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra(AUTHORIZATION)) {
            final String authorization = intent.getStringExtra(AUTHORIZATION);
            final SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(AUTHORIZATION, authorization);
            editor.apply();

            // todo: put these in view model:
            Call<PhotoAppDataResponse> call = mPhotoServiceClient.photoForUser(authorization);

            call.enqueue(new Callback<PhotoAppDataResponse>() {
                @Override
                public void onResponse(@NonNull Call<PhotoAppDataResponse> call, @NonNull Response<PhotoAppDataResponse> response) {
                    if(response.isSuccessful()) {
                        //todo: set data to viewmodel
                        Log.d("alison", "response successful");
                        Log.d("alison", "data: " + response.body().getData());

                    } else {
                        Log.d("alison", "response failed");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PhotoAppDataResponse> call, @NonNull Throwable t) {
                    Log.d("alison", "onFailure");
                    //todo: display failure page
                }
            });
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu_icon);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                // todo add code here to update the UI based on item selected. e.g. swap fragments here
                return true;
            }
        });

        if (findViewById(R.id.main_content) != null) {
            // if we're being restored from a previous state we don'tneed to do anything
            // otherwise we could end up with overlapping fragments
            if (savedInstanceState != null) {
                return;
            }
            final PhotoListFragment photoListFragment = new PhotoListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, photoListFragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
