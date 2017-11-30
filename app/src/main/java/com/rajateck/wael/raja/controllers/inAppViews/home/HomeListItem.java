package com.rajateck.wael.raja.controllers.inAppViews.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.models.News;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by wael on 3/3/17.
 */
public class HomeListItem extends RecyclerView.ViewHolder {
//    private ShimmerFrameLayout shimmerLayout;
    private Context context;
    private Integer type;
    private ImageView icon;
    private ImageView play_image_view;
    private TextView oldPricel;
    private TextView itemName;
    private JCVideoPlayerStandard icon_video;
    private News data;

    public HomeListItem(View view) {
        super(view);
        this.icon = (ImageView) view.findViewById(R.id.icon);
//        this.shimmerLayout = view.findViewById(R.id.shimmerLayout);
//        if (shimmerLayout != null) {
//            shimmerLayout.setAutoStart(true);
//            shimmerLayout.startShimmerAnimation();
//        }
        this.oldPricel = (TextView) view.findViewById(R.id.price);
        this.itemName = (TextView) view.findViewById(R.id.cat_name);
        this.icon_video = (JCVideoPlayerStandard) view.findViewById(R.id.icon_video);

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

    public ImageView getPlay_image_view() {
        return play_image_view;
    }

    public void setPlay_image_view(ImageView play_image_view) {
        this.play_image_view = play_image_view;
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

    public void onBind(News news, Context context, Integer itemViewType) {
        if (news != null) {
            this.data = news;
        }

        if (context != null) {
            this.context = context;
        }

        if (itemViewType != null) {
            this.type = itemViewType;
        }

        if (this.type == null ||
                this.data == null ||
                this.context == null) {
            System.out.println("HomeListItem.onBind : null ");
            return;
        }

//        if (getShimmerLayout() != null ) {
//            try {
//                getShimmerLayout().stopShimmerAnimation();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            getShimmerLayout().startShimmerAnimation();
//        }
        if (type== HomeListAdapter.ViewType.VideoViewType.getValue()) {
            System.out.println("HomeListAdapter.fillItemData here is the video");


            System.out.println("HomeListAdapter.fillItemData here is the photo");
            getItemName().setText(data.getTitle());
            Glide.with(context)
                    .load(data.getImage())
                    .fitCenter()
                    .into(getIcon());

            getPlay_image_view().setVisibility(View.VISIBLE);

        } else {

            System.out.println("HomeListAdapter.fillItemData here is the photo");
            getItemName().setText(data.getTitle());
            Glide.with(context)
                    .load(data.getImage())
                    .fitCenter()
                    .into(getIcon());
        }
    }
}

