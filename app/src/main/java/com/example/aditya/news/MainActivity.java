package com.example.aditya.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.aditya.news.model.GetArticlesResponse;
import com.example.aditya.news.model.NewsArticle;
import com.example.aditya.news.networking.NewsAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*List<NewsArticle> newsArticles = new ArrayList<>();
        newsArticles.add(new NewsArticle("Forget Mahindra, Elon Musk's Tesla now has a bigger threat in India - Volvo",
            "Finally, Tesla Motors may have some competition — not just from domestic first-mover in electric vehicles (EVs), Mahindra & Mahindra, but competition of the global variety. Early this month, Volvo Cars made headlines across the world when the 90-year-old Swedish carmaker declared that it will go all electric by 2019, pulling the plug on cars with just an internal combustion engine. \n",
            "http://img.etimg.com/thumb/msid-59825931,width-210,resizemode-4/untitled-23.jpg",
            "Jul 30, 2017, 06.28 AM IST ",
            "http://economictimes.indiatimes.com/industry/auto/news/passenger-vehicle/cars/how-volvo-hopes-to-ride-on-indian-electrical-vehicle-opportunities/articleshow/59825424.cms"));

        newsArticles.add(new NewsArticle("GST nets a million more taxpayers, registrations cross the 10 lakh mark",
                "New registrations under the goods & services tax (GST) crossed the 10 lakh mark on Saturday, a milestone that brings cheer to policymakers who have been hoping for an increase in the tax base after the rollout of the new tax measure.",
                "http://img.etimg.com/thumb/msid-59828913,width-210,imglength-51567,resizemode-4/gst-nets-a-million-more-taxpayers-registrations-cross-the-10-lakh-mark.jpg",
                "Jul 30, 2017, 08.48 AM IST",
                "http://economictimes.indiatimes.com/news/economy/finance/gst-nets-a-million-more-taxpayers-registrations-cross-the-10-lakh-mark/articleshow/59828859.cms"));


        NewsStore.setNewsArticles(newsArticles);
*/
        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* --This is old position of this piece of code for demo---
        HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(NewsStore.getNewsArticles());
        newsRecyclerView.setAdapter(homeNewsAdapter);
        //Code commented out
        */

        //retrofit2.Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles("the-hindu", "top");

        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles("reuters", "top");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                GetArticlesResponse getArticlesResponse = response.body();
                Toast.makeText(MainActivity.this, "Response Received", Toast.LENGTH_SHORT).show();
                HomeNewsAdapter homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Received", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
