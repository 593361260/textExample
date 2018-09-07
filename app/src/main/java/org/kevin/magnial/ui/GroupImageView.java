package org.kevin.magnial.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 654417248qq.com on 17/12/11.
 */

public class GroupImageView extends RelativeLayout {
    private int mWidth;
    private int mMWidth;
    private int mHeight;
    private Context mContext;
    private float mDensity;
    private float mRadus;
    private List<Rect> layouts;

    public GroupImageView(Context context) {
        this(context, null, 0);
    }

    public GroupImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mDensity = mContext.getResources().getDisplayMetrics().density;

        layouts = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMWidth = w;
        mHeight = h;
        mRadus = (mWidth - mDensity * 18) * 1f / 3 + 0.5f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int a = 0; a < getChildCount(); a++) {
            /**
             * 均分  正方形
             * 计算位置
             */

            if (a%3==0){
//                初始化颜色  红



            }else if(a%3==1){

            }else if(a%3==2){

            }


        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


    }
}
