package org.kevin.magnial.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.kevin.magnial.R;

/**
 * Created by 654417248qq.com on 17/9/20.
 */

public class MangniferView extends View {
    private Context mContext;
    private Paint mPaint;
    private float radius = 50;
    private float DP = 1;
    private int width;
    private int height;

    public MangniferView(Context context) {
        this(context, null, 0);
    }

    public MangniferView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MangniferView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    /**
     *
     */
    private void initView() {
        DP = mContext.getResources().getDisplayMetrics().density;
        radius = 50 * DP;

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.drawable_splash);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        downX = w * 1f / 2;
        downY = h * 1f / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(downX, downY, radius, mPaint);
    }


    private float downX;
    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_POINTER_2_DOWN){
            return false;
        }
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                //移动中心点  判断是否超出边界
                float moveX = event.getX();
                float moveY = event.getY();
                downX = event.getX();
                downY = event.getY();
                if (downX - radius <= 0) {
                    downX = radius;
                    invalidate();
                    return false;
                } else if (downX + radius > width) {
                    downX = width - radius;
                    invalidate();
                    return false;
                }
                if (downY - radius <= 0) {
                    downY = radius;
                    invalidate();
                    return false;
                } else if (downY + radius > height) {
                    downY = height - radius;
                    invalidate();
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
