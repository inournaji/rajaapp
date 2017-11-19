package com.rajateck.wael.raja.controllers.inAppViews.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajateck.wael.raja.R;

/**
 * Created by wael on 3/3/17.
 */
public class HomeListItem extends RecyclerView.ViewHolder {
    ImageView icon;
    TextView oldPricel;
    TextView itemName;

    public HomeListItem(View view) {
        super(view);
        this.icon = (ImageView) view.findViewById(R.id.icon);
        this.oldPricel = (TextView) view.findViewById(R.id.price);
        this.itemName = (TextView) view.findViewById(R.id.cat_name);
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public TextView getOldPricel() {
        return oldPricel;
    }

    public void setOldPricel(TextView oldPricel) {
        this.oldPricel = oldPricel;
    }

    public TextView getItemName() {
        return itemName;
    }

    public void setItemName(TextView itemName) {
        this.itemName = itemName;
    }
}

