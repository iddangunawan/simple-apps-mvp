package com.mylektop.simpleappsmvp.post;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mylektop.simpleappsmvp.BaseApp;
import com.mylektop.simpleappsmvp.R;
import com.mylektop.simpleappsmvp.models.post.PostCommentListResponse;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;
import com.mylektop.simpleappsmvp.networking.Service;

import java.util.List;

import javax.inject.Inject;

public class PostDetailActivity extends BaseApp implements PostView {

    @Inject
    public Service service;
    private TextView tvPostDetailTitle, tvPostDetailBody, tvPostCommentCount;
    private RecyclerView list;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();

        PostPresenter presenter = new PostPresenter(service, this);
        presenter.getPostDetail(getIntent().getIntExtra("POST_ID", 0));
        presenter.getPostComment(getIntent().getIntExtra("POST_ID", 0));
    }

    public void renderView() {
        setContentView(R.layout.activity_post_detail);

        tvPostDetailTitle = (TextView) findViewById(R.id.postDetailTitle);
        tvPostDetailBody = (TextView) findViewById(R.id.postDetailBody);
        tvPostCommentCount = (TextView) findViewById(R.id.postCommentCount);
        list = (RecyclerView) findViewById(R.id.postComments);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    public void init() {
        list.setLayoutManager(new LinearLayoutManager(this));
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

    }

    @Override
    public void getPostDetailSuccess(PostListDataResponse postListDataResponse) {
        tvPostDetailTitle.setText(postListDataResponse.getTitle());
        tvPostDetailBody.setText(postListDataResponse.getBody());
    }

    @Override
    public void getPostCommentSuccess(List<PostCommentListResponse> postCommentListResponses) {
        PostCommentAdapter adapter = new PostCommentAdapter(getApplicationContext(), postCommentListResponses,
                new PostCommentAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(PostCommentListResponse item) {
                        Toast.makeText(getBaseContext(), item.getName() + " comment", Toast.LENGTH_SHORT).show();
                    }
                });

        list.setAdapter(adapter);
        tvPostCommentCount.setText(adapter.getItemCount() + " comments");
    }
}
