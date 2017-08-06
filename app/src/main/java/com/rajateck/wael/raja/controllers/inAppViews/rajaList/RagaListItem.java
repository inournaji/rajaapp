package com.rajateck.wael.raja.controllers.inAppViews.rajaList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.rajateck.wael.raja.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by wael on 3/3/17.
 */
public class RagaListItem extends RecyclerView.ViewHolder {
    private ImageView icon;
    private ImageView play_image_view;
    private TextView oldPricel;
    private TextView itemName;
    private JCVideoPlayerStandard icon_video;
    private NumberProgressBar app_progress;
    private TextView download;
    private TextView created_date;

    public ImageView getStop_downloading() {
        return stop_downloading;
    }

    private TextView version;
    private TextView size;
    private ImageView stop_downloading;

    public ImageView getIcon_holder() {
        return icon_holder;
    }

    public void setIcon_holder(ImageView icon_holder) {
        this.icon_holder = icon_holder;
    }

    private ImageView icon_holder;


    public TextView getVersion() {
        return version;
    }

    public void setVersion(TextView version) {
        this.version = version;
    }

    public TextView getSize() {
        return size;
    }

    public void setSize(TextView size) {
        this.size = size;
    }

    public RagaListItem(View view) {
        super(view);
        this.icon = (ImageView) view.findViewById(R.id.icon);

        this.oldPricel = (TextView) view.findViewById(R.id.price);
        this.itemName = (TextView) view.findViewById(R.id.cat_name);
        this.icon_video = (JCVideoPlayerStandard) view.findViewById(R.id.icon_video);
        this.app_progress = (NumberProgressBar) view.findViewById(R.id.app_progress);
        this.download = (TextView) view.findViewById(R.id.download);
        this.play_image_view = (ImageView) view.findViewById(R.id.play_image_view);
        this.version = (TextView) view.findViewById(R.id.version);
        this.size = (TextView) view.findViewById(R.id.size );
        this.created_date = (TextView) view.findViewById(R.id.created_date);
        this.icon_holder = (ImageView) view.findViewById(R.id.icon_holder);
        this.stop_downloading = (ImageView) view.findViewById(R.id.stop_downloading);
    }

    public TextView getDownload() {
        return download;
    }

    public void setDownload(TextView download) {
        this.download = download;
    }

    public ImageView getPlay_image_view() {
        return play_image_view;
    }

    public void setPlay_image_view(ImageView play_image_view) {
        this.play_image_view = play_image_view;
    }

    public JCVideoPlayerStandard getIcon_video() {
        return icon_video;
    }

    public void setIcon_video(JCVideoPlayerStandard icon_video) {
        this.icon_video = icon_video;
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

    public NumberProgressBar getApp_progress() {
        return app_progress;
    }

    public void setApp_progress(NumberProgressBar app_progress) {
        this.app_progress = app_progress;
    }

    public TextView getCreated_date() {
        return created_date;
    }

    public void setCreated_date(TextView created_date) {
        this.created_date = created_date;
    }
}

