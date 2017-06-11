package com.tania.weatherapp.bindings;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.tania.weatherapp.R;

public final class BindingAdapters {

    private BindingAdapters() {
    }

    @BindingAdapter(value = {"gifUrl", "placeholderImage"},
            requireAll = false)
    public static void setGifUrl(ImageView imageView, String url, int placeholder) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(
                        new GlideDrawableImageViewTarget(imageView) {
                            @Override
                            public void onResourceReady(
                                    GlideDrawable gif, GlideAnimation<? super GlideDrawable> animation) {

                                super.onResourceReady(gif, animation);
                                gif.start();
                            }
                        });
    }

    @BindingAdapter(value = {"url", "placeholderImage"},
            requireAll = false)
    public static void setImageUrl(final ImageView imageView, String url, int placeholder) {
        Context context = imageView.getContext();
        DrawableRequestBuilder builder = Glide.with(context).load(url).diskCacheStrategy(
                DiskCacheStrategy.SOURCE).animate(R.anim.cross_fade);

        if (placeholder != 0) {
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            builder.placeholder(placeholder).into(
                    new GlideDrawableImageViewTarget(imageView) {
                        @Override
                        public void onResourceReady(
                                GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {

                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            super.onResourceReady(resource, animation);
                        }
                    });
        } else {
            builder.into(imageView);
        }

    }

}
