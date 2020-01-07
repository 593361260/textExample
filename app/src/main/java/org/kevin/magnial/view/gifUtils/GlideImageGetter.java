package org.kevin.magnial.view.gifUtils;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

import org.kevin.magnial.R;

public class GlideImageGetter implements Html.ImageGetter, Drawable.Callback {

    private final Context mContext;

    private final TextView mTextView;


    public static GlideImageGetter get(View view) {
        return (GlideImageGetter) view.getTag(R.id.drawable_callback_tag);
    }

    public void clear() {
        Glide.with(mContext).clear(mTextView);
    }

    public GlideImageGetter(Context context, TextView textView) {
        this.mContext = context;
        this.mTextView = textView;
        mTextView.setTag(R.id.drawable_callback_tag, this);
    }

    @Override
    public Drawable getDrawable(String url) {
        final GifDraw urlDrawable = new GifDraw();
        Glide.with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(new ImageGetterViewTarget(mTextView, urlDrawable));
        return urlDrawable;
    }

    @Override
    public void invalidateDrawable(Drawable who) {
        mTextView.invalidate();
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {

    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {

    }

    private class ImageGetterViewTarget extends ViewTarget<TextView, Drawable> {

        private final GifDraw mDrawable;

        private ImageGetterViewTarget(TextView view, GifDraw drawable) {
            super(view);
            this.mDrawable = drawable;
        }

        @Override
        public void onLoadFailed(@Nullable Drawable errorDrawable) {

        }

        @Override
        public void onResourceReady(@NonNull final Drawable resource, @Nullable Transition<? super Drawable> transition) {
            Rect rect;
            //            if (resource.getIntrinsicWidth() > 100) {
            //                float width;
            //                float height;
            //                if (resource.getIntrinsicWidth() >= getView().getWidth()) {
            //                    float downScale = (float) resource.getIntrinsicWidth() / getView().getWidth();
            //                    width = (float) resource.getIntrinsicWidth() / downScale;
            //                    height = (float) resource.getIntrinsicHeight() / downScale;
            //                } else {
            //                    float multiplier = (float) getView().getWidth() / resource.getIntrinsicWidth();
            //                    width = (float) resource.getIntrinsicWidth() * multiplier;
            //                    height = (float) resource.getIntrinsicHeight() * multiplier;
            //                }
            //                rect = new Rect(8, 0, AppTools.Companion.dp2px(mContext, 15), AppTools.Companion.dp2px(mContext, 15));
            //            }
            rect = new Rect(8, 0, resource.getIntrinsicWidth() + 8, resource.getIntrinsicHeight());
            resource.setBounds(rect);
            mDrawable.setBounds(rect);
            mDrawable.setDrawable(resource);
            if (resource instanceof GifDrawable) {
                mDrawable.setCallback(get(getView()));
                ((GifDrawable) resource).start();
            }
            getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    /*
                     * 添加视图
                     */
                    if (resource instanceof Animatable)
                        ((GifDrawable) resource).start();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if (resource instanceof Animatable)
                        if (((GifDrawable) resource).isRunning())
                            ((GifDrawable) resource).stop();
                }
            });
            getView().setText(getView().getText());
            getView().invalidate();
        }

        private Request request;

        @Override
        public Request getRequest() {
            return request;
        }

        @Override
        public void setRequest(@Nullable Request request) {
            this.request = request;
        }
    }
}