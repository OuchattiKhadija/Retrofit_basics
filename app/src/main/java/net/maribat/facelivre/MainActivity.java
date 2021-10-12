package net.maribat.facelivre;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView title_tv, title_tv2, title_tv3, title_tv4, title_tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title_tv = findViewById(R.id.title_tv);
        title_tv2 = findViewById(R.id.title_tv2);
        title_tv3 = findViewById(R.id.title_tv3);
        title_tv4 = findViewById(R.id.title_tv4);
        title_tv5 = findViewById(R.id.title_tv5);

        Post post = new Post(5, "testRetrofix", "Teeeeeeeeeeeeeeeeest");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("title","title Map");
        map.put("body","Body Map tesssssst");
        map.put("userId",10);


                /*
                 * RestAPI url
                 * Interface
                 * Builder
                 * Conerter
                 * call  ===> OnResponse|| onFailaire
                 * */
                //Declaration Builder & Converter
                Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //insert data into the Inerface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        //============Test Get ===================
        //test call1
        Call<Post> call = apiInterface.getPostIdEqualTwo();

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                title_tv.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                title_tv.setText(t.getMessage());
            }
        });

        //test call2
        Call<Post> call2 = apiInterface.getPostwithEntredId(3);

        call2.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                title_tv2.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                title_tv2.setText(t.getMessage());
            }
        });
        //test call3
        Call<List<Post>> calltestTwo = apiInterface.getListPosts("1");

        calltestTwo.enqueue(new Callback<List<Post>>() {


            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                title_tv3.setText(response.body().get(0).getTitle());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                title_tv3.setText(t.getMessage());
            }
        });


        //============Test Post ===================
        Call<Post> call3 = apiInterface.storePoste(post);
        call3.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                title_tv4.setText(response.body().getId() + " " + response.body().getUserId() + " " + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                title_tv4.setText(t.getMessage());
            }
        });

        Call<Post> call4 = apiInterface.storePoste2(map);
        call4.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                title_tv5.setText(response.body().getId() + " " + response.body().getUserId() + " " + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                title_tv5.setText(t.getMessage());
            }
        });

    }
}

