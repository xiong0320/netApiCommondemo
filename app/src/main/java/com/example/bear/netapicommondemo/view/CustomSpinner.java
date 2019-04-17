package com.example.bear.netapicommondemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.bear.netapicommondemo.listener.SpinnerSelectListener;
import com.example.bear.netapicommondemo.utils.AppExecutors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomSpinner extends ListPopupWindow {
    private View anchorView;
    private ListView listView;
    private SpinnerSelectListener selectListener;

    public CustomSpinner(@NonNull Context context) {
        super(context);
    }

    public CustomSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void bindAnchorView(View anchorView, SpinnerSelectListener spinnerSelectListener) {
        this.selectListener = spinnerSelectListener;
        this.anchorView = anchorView;
//        FrameLayout frameLayout = new FrameLayout(anchorView.getContext());
//        Button button = new Button(anchorView.getContext());
//        button.setText("加载数据");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        frameLayout.addView(getListView());
//        frameLayout.addView(button);
        setWidth(this.anchorView.getWidth());
        setHeight((int) (this.anchorView.getWidth()*1.3));
        this.anchorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomSpinner.this.show();

            }
        });

        setAnchorView(this.anchorView);

        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = parent.getItemAtPosition(position);
                if (selectListener != null) {
                    selectListener.onSelect(o);
                    CustomSpinner.this.dismiss();
                }
            }
        });
    }

    public void removeCustomSpinner() {
        if (this.isShowing()) {
            dismiss();
        }
        anchorView = null;
        selectListener = null;
    }
}
