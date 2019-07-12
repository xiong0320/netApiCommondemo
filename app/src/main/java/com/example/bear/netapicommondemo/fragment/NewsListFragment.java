package com.example.bear.netapicommondemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bear.netapicommondemo.R;
import com.example.bear.netapicommondemo.adapter.NewsRecyclerAdapter;
import com.example.bear.netapicommondemo.view.MyCustomLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsListFragment extends Fragment{
    @BindView(R.id.news_recycler_view)
    RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    private static final String TAG = "xxaa";
    boolean canScroll = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new NewsRecyclerAdapter(getActivity()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                Log.d(TAG, "onScrollStateChanged: newState = " + newState);
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    canScroll = true;
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE && canScroll) {
                    startSmartScroll(recyclerView);
                    canScroll = false;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Log.d(TAG, "onScrolled: dx =" + dx +" dy = " + dy +" state = " + recyclerView.getScrollState());
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    public void startSmartScroll(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int first = linearLayoutManager.findFirstVisibleItemPosition();
        View firstView = linearLayoutManager.findViewByPosition(first);
        int last = linearLayoutManager.findLastVisibleItemPosition();
        View lastView = linearLayoutManager.findViewByPosition(last);
        if (firstView!=null && lastView!=null) {
            int dy = firstView.getBottom();//上滑y绝对值，上滑>0
            int total = recyclerView.getAdapter().getItemCount();
            int ph = recyclerView.getHeight();

            if (firstView.getBottom()<firstView.getHeight()/2 &&
                    (last!=total-1 || dy< lastView.getHeight()- (ph-lastView.getTop())
                    )) { //上滑

            } else { // 下滑
                dy = firstView.getTop() ;
                firstView.animate().scaleX(1.1f).setDuration(200).start();
            }
            mRecyclerView.smoothScrollBy(0,dy);
            Log.d(TAG, "startSmartScroll: dy = " + dy);
        }
    }




    @OnClick(R.id.scroll_to_btn)
    public void scrollTo () {
//        mRecyclerView.scrollToPosition(1);
        mRecyclerView.smoothScrollBy(0,20);
    }
}
