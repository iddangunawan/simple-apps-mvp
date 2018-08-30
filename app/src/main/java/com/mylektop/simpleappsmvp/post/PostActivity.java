package com.mylektop.simpleappsmvp.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mylektop.simpleappsmvp.BaseApp;
import com.mylektop.simpleappsmvp.R;
import com.mylektop.simpleappsmvp.models.post.PostCommentListResponse;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;
import com.mylektop.simpleappsmvp.networking.Service;
import com.mylektop.simpleappsmvp.util.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by MyLektop on 21/08/2018.
 */
public class PostActivity extends BaseApp implements PostView {

    @Inject
    public Service service;
    private RecyclerView list;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        PostPresenter presenter = new PostPresenter(service, this);
        presenter.getPostList();
    }

    public void renderView() {
        setContentView(R.layout.activity_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetStartWithNavigation(0);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            // Remove default title text
            actionBar.setDisplayShowTitleEnabled(false);
            // Get access to the custom title view
            TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            mTitle.setText(getResources().getString(R.string.app_name));
        }

        list = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init() {
        list.setLayoutManager(new LinearLayoutManager(this));
        list.addItemDecoration(new DividerItemDecoration(10));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getPostListSuccess(List<PostListDataResponse> postListDataResponses) {
        PostAdapter adapter = new PostAdapter(getApplicationContext(), postListDataResponses,
                new PostAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(PostListDataResponse item) {
                        detail(item.getId());
                    }
                });

        list.setAdapter(adapter);
    }

    @Override
    public void getPostDetailSuccess(PostListDataResponse postListDataResponse) {

    }

    @Override
    public void getPostCommentSuccess(List<PostCommentListResponse> postCommentListResponses) {

    }

    private void detail(int post_id) {
        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra("POST_ID", post_id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
}
