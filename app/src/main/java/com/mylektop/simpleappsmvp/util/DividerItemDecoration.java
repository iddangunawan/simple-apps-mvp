package com.mylektop.simpleappsmvp.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MyLektop on 30/08/2018.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private boolean isHorizontalLayout;

    public DividerItemDecoration(int space) {
        this.space = space;
    }

    public DividerItemDecoration(int space, boolean isHorizontalLayout) {
        this.space = space;
        this.isHorizontalLayout = isHorizontalLayout;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (isHorizontalLayout) {
            outRect.bottom = space;
            outRect.right = space;
            outRect.left = space;
            outRect.top = space;
        } else {
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            } else if (state.getItemCount() > 0 && parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
                outRect.top = space;
                outRect.bottom = 0;
            } else {
                outRect.top = space;
            }
        }
    }
}
