package com.rajateck.wael.raja.controllers.inAppViews.rajaList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.models.OfferItem;

/**
 * Created by wael on 11/18/17 at raja-tec.
 * at November
 */

public class OffersViewHolder extends RecyclerView.ViewHolder {
    private ImageView bigImage;
    private TextView offerDetail;
    private TextView createdDate;
    private Context context;
    private OfferItem offerItem;

    public OffersViewHolder(View view) {
        super(view);
        findViews(view);
    }


    private void findViews(View view) {
        bigImage = (ImageView) view.findViewById(R.id.bigImage);
        offerDetail = (TextView) view.findViewById(R.id.offer_detail);
        createdDate = (TextView) view.findViewById(R.id.created_date);
    }

    public void onBind(Context _context, OfferItem _offerItem) {

        if (_context != null) {
            context = _context;
        }

        if (_offerItem != null) {
            offerItem = _offerItem;
        }

        if (context == null ||
                offerItem == null) {
            return;
        }


        if (offerItem.getBigImage() != null) {
            bigImage.setVisibility(View.VISIBLE);


            Glide.with(context)
                    .load(offerItem.getBigImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(bigImage);

        }

        offerDetail.setText(offerItem.getBody().getValue());
        createdDate.setText(offerItem.getCreated());
    }
}
