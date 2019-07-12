package com.example.bear.netapicommondemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyRecyclerView extends RecyclerView {
    final static int Line1 = 450;
    public ScrollToCallback scrollToCallback;
    int curPositioin = 0;
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public void setScrollToCallback(ScrollToCallback scrollToCallback) {
        this.scrollToCallback = scrollToCallback;
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        Log.i("feiweilog"," x = " + e.getX() + " y = " + e.getY());



            if (e.getAction() == MotionEvent.ACTION_UP) {
                View view0 = findChildViewUnder(e.getX(),0);
                if (view0 != null) {
                    final int itemHight = view0.getHeight()+view0.getPaddingBottom()+ view0.getPaddingTop();
                    View view1 = findChildViewUnder(e.getX(),itemHight);
                    if (view1!=null) {
                        int position = getChildLayoutPosition(view0);
                        final int y1 = (int)view1.getY();
                        int offset = 0;
                        if (y1< itemHight/2){
                            position++;
                            offset = y1;
                            if (getChildAt(position) == null) {
                                position --;
                            }
                        } else {
                            offset = (int) view0.getY();
                        }
                        if (null != scrollToCallback) {
                            Log.i("feiweilog"," scrollTo = " + position);
                            scrollToCallback.scrollToOffset(position,offset);
                        }
                    }


                }


                    return false;
                }


        return super.onTouchEvent(e);
    }
}
