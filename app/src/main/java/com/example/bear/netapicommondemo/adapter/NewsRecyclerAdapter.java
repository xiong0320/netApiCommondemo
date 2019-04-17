package com.example.bear.netapicommondemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bear.netapicommondemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private Context mContext;

    public NewsRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item,viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView.setText("我是你爸爸，我是你爸爸，我是你爸爸，我是你爸爸，我是你爸爸，我是你爸爸");
        viewHolder.textView.setOnClickListener(v -> popClickItem());
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.news_item_contents)
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    void popClickItem() {
        Toast.makeText(mContext,"点击了内容",Toast.LENGTH_SHORT).show();
    }

}
