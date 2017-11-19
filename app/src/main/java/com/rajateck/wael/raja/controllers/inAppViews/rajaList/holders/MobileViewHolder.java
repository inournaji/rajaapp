package com.rajateck.wael.raja.controllers.inAppViews.rajaList.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.models.Mobile;

/**
 * Created by wael on 3/3/17.
 */
public class MobileViewHolder extends RecyclerView.ViewHolder {
    private ImageView icon;
    private TextView price;
    private TextView mobile_name;
    private Mobile mobile;
    private Context context;

    public MobileViewHolder(View view) {
        super(view);
        icon = (ImageView) view.findViewById(R.id.icon);
        price = (TextView) view.findViewById(R.id.price);
        mobile_name = (TextView) view.findViewById(R.id.mobile_name);
    }


    public void onbind(Mobile _mobile, Context _context) {
        if (_mobile != null) {
            this.mobile = _mobile;
        }

        if (_context != null) {
            this.context = _context;
        }

        if (this.mobile == null ||
                context == null) {
            return;
        }

        Glide.with(context)
                .load(mobile.getImage().get(0))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(icon);

//        .placeholder(R.drawable.news_placeholder)

//        .listener(new RequestListener<String, GlideDrawable>() {
//            @Override
//            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//
//                ((RagaListItem) holder).getIcon().setVisibility(View.VISIBLE);
//                return false;
//            }
//        })

        mobile_name.setText(mobile.getTitle());
        price.setText(mobile.getPrice());
    }
}

