package com.android.expandedrecyclerview.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by lxm on 17/1/18.
 */

public class ExpandableItemIndicator extends FrameLayout {
    static abstract class Impl {
        public abstract void onInit(Context context,AttributeSet attrs,int defStyleAttr,ExpandableItemIndicator thiz);
        public abstract void setExpandedState(boolean isExpande,boolean animate);
    }
    private Impl mImpl;
    public ExpandableItemIndicator(Context context) {
        super(context);
        onInit(context,null,0);
    }

    public ExpandableItemIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInit(context,attrs,0);
    }

    public ExpandableItemIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInit(context,attrs,defStyleAttr);
    }

    public ExpandableItemIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void onInit(Context context,AttributeSet attrs,int defStyleAttr) {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mImpl = new ExpandableItemIndicatorImplAnim();
        } else {
            mImpl = new ExpandableItemIndicatorImplNoAnim();
        }
        mImpl.onInit(context,attrs,defStyleAttr,this);
    }

    public void setExpandedState(boolean isExpanded, boolean animate) {
        mImpl.setExpandedState(isExpanded, animate);
    }
}
