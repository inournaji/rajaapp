package com.rajateck.wael.raja.controllers.inAppViews.rajaList.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.models.AccessoryItem;

/**
 * Created by wael on 3/3/17.
 */
public class AccessoryViewHolder extends RecyclerView.ViewHolder {
    private ImageView icon;
    private TextView price;
    private TextView accessName;
    private AccessoryItem accessoryItem;
    private Context context;

    public AccessoryViewHolder(View view) {
        super(view);
        icon = (ImageView) view.findViewById(R.id.icon);
        price = (TextView) view.findViewById(R.id.price);
        accessName = (TextView) view.findViewById(R.id.mobile_name);
    }


    public void onbind(AccessoryItem _mobile, Context _context) {
        if (_mobile != null) {
            this.accessoryItem = _mobile;
        }

        if (_context != null) {
            this.context = _context;
        }

        if (this.accessoryItem == null ||
                context == null) {
            return;
        }

        Glide.with(context)
                .load(accessoryItem.getImage().get(0))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(icon);


        accessName.setText(accessoryItem.getTitle());
        price.setText(accessoryItem.getPrice());
    }
}

