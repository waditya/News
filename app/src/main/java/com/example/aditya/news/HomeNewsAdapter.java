package com.example.aditya.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aditya.news.model.Article;
import com.example.aditya.news.model.NewsArticle;
import com.example.aditya.news.utils.DateUtils;

import java.util.List;

/**
 * Created by Adity on 7/29/2017.
 */

public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder> {
    //private List<NewsArticle> newsArticles;
    private List<Article> newsArticles;

    //public HomeNewsAdapter(List<NewsArticle> newsArticles) {
    public HomeNewsAdapter(List<Article> newsArticles) {
        this.newsArticles = newsArticles;
    }

    @Override
    public HomeNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent, false);
        HomeNewsViewHolder homeNewsViewHolder = new HomeNewsViewHolder(view);
        return homeNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeNewsViewHolder holder, final int position) {
        //NewsArticle newsArticle = newsArticles.get(position);
        Article newsArticle = newsArticles.get(position);
        //Glide.with(holder.cardImageView.getContext()).load(newsArticle.getImageUrl())
        Glide.with(holder.cardImageView.getContext()).load(newsArticle.getUrlToImage())
                .centerCrop()
                .into(holder.cardImageView);
        holder.cardTitleTextView.setText(newsArticle.getTitle());
        //holder.cardTimeTextView.setText(newsArticle.getTime());
        holder.cardTimeTextView.setText(DateUtils.formatNewsApiDate(newsArticle.getPublishedAt()));

        //holder.cardTimeTextView.setText(newsArticle.getDetails());
        holder.cardContentTextView.setText(newsArticle.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NewsDetailsActivity.launch(v.getContext(), position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public static class HomeNewsViewHolder extends RecyclerView.ViewHolder{

        public HomeNewsViewHolder(View itemView) {
            super(itemView);
            cardImageView = (ImageView) itemView.findViewById(R.id.cards_news_image);
            cardTitleTextView = (TextView) itemView.findViewById(R.id.cards_news_image_title);
            cardTimeTextView = (TextView) itemView.findViewById(R.id.card_news_time);
            cardContentTextView = (TextView) itemView.findViewById(R.id.card_news_content);

        }

        ImageView cardImageView;
        TextView cardTitleTextView;
        TextView cardTimeTextView;
        TextView cardContentTextView;
    }
}