package com.rajateck.wael.raja.controllers.inAppViews.rajaList;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.enums.FragmentTags;
import com.rajateck.wael.raja.models.Mobile;

import java.util.ArrayList;

/**
 * Created by wael on 4/8/17.
 */

public class AccessoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Object> objectArrayList;
    private ArrayList<Mobile> mobiles;
    private Activity activity;
    private FragmentTags fragmentTag;

    public AccessoryListAdapter(Context context, ArrayList<Object> objectArrayList, Activity activity, FragmentTags fragmentTag) {
        this.context = context;
        this.objectArrayList = objectArrayList;
        this.activity = activity;
        this.fragmentTag = fragmentTag;
    }

    public AccessoryListAdapter(Context context, ArrayList<Mobile> objectArrayList, Activity activity) {
        this.context = context;
        this.mobiles = objectArrayList;
        this.activity = activity;
        this.fragmentTag = FragmentTags.MobileFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.recycler_list_item, parent, false);
        viewHolder = new RagaListItem(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (fragmentTag.equals(FragmentTags.MobileFragment)) {

//            ((RagaListItem) holder).getIcon().setBackgroundResource(R.drawable.test_mobile);
            Glide.with(context)
                    .load(mobiles.get(position).getImage().get(0))
                    .placeholder(R.drawable.news_placeholder)
                    .into(((RagaListItem) holder).getIcon());
            ((RagaListItem) holder).getItemName().setText(mobiles.get(position).getTitle());
            ((RagaListItem) holder).getOldPricel().setText(mobiles.get(position).getPrice());

        } else if (fragmentTag.equals(FragmentTags.HardWareFragment)) {
            ((RagaListItem) holder).getItemName().setText("Hardware");
//            ((RagaListItem) holder).getIcon().setBackgroundResource(R.drawable.testhardware);

        } else if (fragmentTag.equals(FragmentTags.ExtensionsFragment)) {
            ((RagaListItem) holder).getItemName().setText("Selfie");
//            ((RagaListItem) holder).getIcon().setBackgroundResource(R.drawable.testaccessory);
        }
    }

    @Override
    public int getItemCount() {
        if (mobiles != null) {
            return mobiles.size();
        } else {
            return objectArrayList.size();
        }

    }

}
