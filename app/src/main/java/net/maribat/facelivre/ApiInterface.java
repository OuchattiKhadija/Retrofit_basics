package net.maribat.facelivre;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //===========get function===========

    @GET("posts/2")
    public Call<Post> getPostIdEqualTwo();

    @GET("posts/{id}")
    public Call<Post> getPostwithEntredId(@Path("id") int postId);

    @GET("posts")
    public Call<List<Post>> getListPosts(@Query("userId") String userId);

    //===========Post function===========

    //post with class objects
    @POST("posts")
    public Call<Post> storePoste(@Body Post post);

    //post with map objects
    @POST("posts")
    public Call<Post> storePoste2(@Body HashMap<Object,Object> map);
}
