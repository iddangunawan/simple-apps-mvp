package com.mylektop.simpleappsmvp.post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mylektop.simpleappsmvp.R;
import com.mylektop.simpleappsmvp.models.post.PostCommentListResponse;

import java.util.List;

/**
 * Created by MyLektop on 23/08/2018.
 */
public class PostCommentAdapter extends RecyclerView.Adapter<PostCommentAdapter.ViewHolder> {

    private Context context;
    private List<PostCommentListResponse> data;
    private final OnItemClickListener listener;

    public PostCommentAdapter(Context context, List<PostCommentListResponse> data, OnItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_comment, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCommentName.setText(data.get(position).getName());
        holder.tvCommentEmail.setText(data.get(position).getEmail());
        holder.tvCommentBody.setText(data.get(position).getBody());

        holder.click(data.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(PostCommentListResponse item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCommentName, tvCommentEmail, tvCommentBody;

        private ViewHolder(View itemView) {
            super(itemView);

            tvCommentName = (TextView) itemView.findViewById(R.id.postCommentName);
            tvCommentEmail = (TextView) itemView.findViewById(R.id.postCommentEmail);
            tvCommentBody = (TextView) itemView.findViewById(R.id.postCommentBody);
        }

        private void click(final PostCommentListResponse postCommentListResponse, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(postCommentListResponse);
                }
            });
        }
    }
}
