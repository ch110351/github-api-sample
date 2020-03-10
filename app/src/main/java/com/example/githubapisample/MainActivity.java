package com.example.githubapisample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.githubapisample.api.ApiResponse;
import com.example.githubapisample.api.GithubService;
import com.example.githubapisample.data.model.AccessToken;
import com.example.githubapisample.data.model.LoginUser;
import com.example.githubapisample.databinding.MainActivityBinding;
import com.example.githubapisample.ui.MainViewModel;
import com.example.githubapisample.viewmodel.GithubViewModelFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String clientId = "93e0542aacf25899e1be";
    private static final String clientSecret = "1598386e1d65370f8907af01191e2ddd56f36411";
    private static final String redirectUrl = "wesley://callback";
    private static final String authUrl = "https://github.com/login/oauth/authorize" + "?client_id=" + clientId +
            "&scope=repo&redirect_url=" + redirectUrl;
    private MainViewModel mainViewModel;
    private MainActivityBinding binding;
    private GithubViewModelFactory factory = new GithubViewModelFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPreferences = getApplication().getSharedPreferences("data", MODE_PRIVATE);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);

        mainViewModel.getUserData().observe(this, new Observer<ApiResponse<LoginUser>>() {
            @Override
            public void onChanged(ApiResponse<LoginUser> response) {
                if (response.isSuccessful()) {
                    Log.d("Wesley", "user name: " + response.body.getName());
                    Log.d("Wesley", "userAvatar: " + response.body.getAvatar_url());
                    String userName = response.body.getName();
                    String login = response.body.getLogin();
                    String userAvatar = response.body.getAvatar_url();
                    sharedPreferences.edit().putString("userName", userName).apply();//user name
                    sharedPreferences.edit().putString("userAvatar", userAvatar).apply();//avatar url
                    sharedPreferences.edit().putString("login", login).apply(); //登入帳號
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    String msg = response.errorMessage;
                    Log.d("Wesley", "erro rMessage : " + msg);
                }
            }
        });
        String token = sharedPreferences.getString("token", "");
        Log.d("Wesley", "Access token : " + token);
        if (token.isEmpty()) {
            Log.d("Wesley", "isEmpty");
            loadWebview();
        } else
            checkTokenAccess(token);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * Check Token access api /user
     *
     * @param token
     */
    private void checkTokenAccess(String token) {
        String accessToken = "Bearer " + token;
        mainViewModel.checkToken(accessToken);
    }

    /**
     * load Github to login and get access token
     */
    private void loadWebview() {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setAllowContentAccess(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(authUrl);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.toString().startsWith(redirectUrl)) {
                    String code = uri.getQueryParameter("code");
                    Log.d("Wesley", "code " + code);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://github.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    GithubService githubService = retrofit.create(GithubService.class);
                    githubService.getAccessToken(clientId, clientSecret, code).enqueue(new Callback<AccessToken>() {
                        @Override
                        public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                            Log.d("Wesley", "OAuth success " + response.body().getAccessToken());
                            String token = response.body().getAccessToken();
                            SharedPreferences sharedPreferences = getApplication().getSharedPreferences("data", MODE_PRIVATE);
                            sharedPreferences.edit().putString("token", token).apply();
                            checkTokenAccess(token);

//                            Intent intent = new Intent();
//                            intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
//                            intent.setClass(MainActivity.this, UserActivity.class);
//                            startActivity(intent);
//                            finish();
                        }

                        @Override
                        public void onFailure(Call<AccessToken> call, Throwable t) {
                            Log.d("Wesley", "OAuth fail");
                        }
                    });
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });
    }
}
