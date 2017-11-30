package com.rajateck.wael.raja.controllers.inAppViews.home;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rajateck.wael.raja.R;
import com.rajateck.wael.raja.controllers.inAppViews.rajaList.RagaListItem;
import com.rajateck.wael.raja.models.News;
import com.rajateck.wael.raja.utils.ScreenUtils;

import java.util.ArrayList;

/**
 * Created by wael on 3/3/17.
 */
public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<News> newsArrayList;
    private Context context;
    private ArrayList<Object> objectArrayList;
    private Activity activity;

    public HomeListAdapter(Context context, ArrayList<Object> objectArrayList, Activity activity) {
        this.context = context;
        this.objectArrayList = objectArrayList;
        this.activity = activity;
    }

    public HomeListAdapter(FragmentActivity activity, ArrayList<News> news, FragmentActivity activity1) {
        this.activity = activity;
        this.newsArrayList = news;
        this.context = activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setObjectArrayList(ArrayList<Object> objectArrayList) {
        this.objectArrayList = objectArrayList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.home_recycler_list_item, parent, false);
        viewHolder = new HomeListItem(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        fillItemData(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (newsArrayList != null) {
            if (newsArrayList.get(position).getVideo() != null &&
                    newsArrayList.get(position).getVideo().trim().length() > 0 &&
                    !newsArrayList.get(position).getVideo().equalsIgnoreCase("[]")) {
                return ViewType.VideoViewType.getValue();
            } else {
                return ViewType.PhotoViewType.getValue();
            }

        } else {
            return super.getItemViewType(position);
        }
    }


    private void fillItemData(final RecyclerView.ViewHolder holder, int position) {
        ((HomeListItem) holder).onBind (newsArrayList.get(position), context, holder.getItemViewType());
    }

    @Override
    public int getItemCount() {
        if (newsArrayList != null) {
            return newsArrayList.size();
        } else {
            return objectArrayList.size();
        }
    }


    public enum ViewType {
        PhotoViewType(0),
        VideoViewType(1);

        private int value;

        ViewType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
