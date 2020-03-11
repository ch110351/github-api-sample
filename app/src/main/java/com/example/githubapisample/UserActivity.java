package com.example.githubapisample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.githubapisample.ui.UserRepoFragment;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("data", MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName", "");
        String userAvatar = sharedPreferences.getString("userAvatar", "");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(userName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Glide.with(this)
                .asDrawable()
                .load(userAvatar)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                getSupportActionBar().setLogo(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
        String tag = UserRepoFragment.TAG;
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            UserRepoFragment fragment = UserRepoFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, tag)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //clean cookie for logout
            case R.id.action_logout:
                final SharedPreferences sharedPreferences = getApplication().getSharedPreferences("data", MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                Log.d("Wesley", "UserActivity token " + token);
                CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(UserActivity.this);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                cookieManager.removeSessionCookies(null);
                cookieManager.removeAllCookies(null);
                CookieManager.getInstance().flush();
                cookieSyncManager.sync();

                sharedPreferences.edit().remove("token").apply();
                Intent intent = new Intent();
                intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.setClass(UserActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_search:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
