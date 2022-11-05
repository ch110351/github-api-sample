package com.example.githubapisample.api;

import com.example.githubapisample.data.model.AccessToken;
import com.example.githubapisample.data.model.Commit;
import com.example.githubapisample.data.model.Contributors;
import com.example.githubapisample.data.model.LoginUser;
import com.example.githubapisample.data.model.RepoSearchResponse;
import com.example.githubapisample.data.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    @GET("search/repositories")
    Call<RepoSearchResponse> searchRepos(@Query("q") String query);

    @GET("users/{user}/repos")
    Call<List<Repository>> listRepositories(@Path("user") String user);

    @GET("repos/{user}/{repo}/contributors")
    Call<List<Contributors>> listContributors(@Path("user") String user, @Path("repo") String repo, @Query("page") int page);

    @GET("repos/{user}/{repo}/commits")
    Call<List<Commit>> listCommitList(@Path("user") String user, @Path("repo") String repo, @Query("page") int page);

    @GET("user")
    Call<LoginUser> getUserData(@Header("Authorization") String token);

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );
}
