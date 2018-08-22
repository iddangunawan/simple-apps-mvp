package com.mylektop.simpleappsmvp.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylektop.simpleappsmvp.R;
import com.mylektop.simpleappsmvp.models.post.PostListDataResponse;

import java.util.List;

/**
 * Created by MyLektop on 21/08/2018.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<PostListDataResponse> data;
    private final OnItemClickListener listener;

    public PostAdapter(Context context, List<PostListDataResponse> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvBody.setText(data.get(position).getBody());
        holder.detail(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(PostListDataResponse item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        ImageView ivDetail;

        private ViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.postTitle);
            tvBody = (TextView) itemView.findViewById(R.id.postBody);
            ivDetail = (ImageView) itemView.findViewById(R.id.postDetail);
        }

        private void detail(final PostListDataResponse postListDataResponse, final OnItemClickListener listener) {
            ivDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(postListDataResponse);
                }
            });
        }
    }
}
