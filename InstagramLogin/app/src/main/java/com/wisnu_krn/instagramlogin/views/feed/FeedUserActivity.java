package com.wisnu_krn.instagramlogin.views.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.wisnu_krn.instagramlogin.R;
import com.wisnu_krn.instagramlogin.models.Post;
import com.wisnu_krn.instagramlogin.presenter.FeedUserPresenter;
import com.wisnu_krn.instagramlogin.utils.PreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedUserActivity extends AppCompatActivity implements FeedUserInterface {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressLoading) ProgressBar progressBar;

    private FeedUserPresenter presenter;
    private FeedAdapter adapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_user);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new FeedUserPresenter(this, this);

        adapter = new FeedAdapter(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        presenter.getUserMedia(PreferencesUtils.getUserID(this), PreferencesUtils.getToken(this));
    }

    @Override public void showData(List<Post> posts) {
        adapter.setListData(posts);
        adapter.notifyDataSetChanged();
    }

    @Override public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
