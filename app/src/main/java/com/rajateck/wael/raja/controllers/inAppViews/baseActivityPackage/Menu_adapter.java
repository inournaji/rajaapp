package com.rajateck.wael.raja.controllers.inAppViews.baseActivityPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rajateck.wael.raja.R;

import java.util.ArrayList;


/**
 * Created by wael on 3/18/17.
 */
public class Menu_adapter extends BaseAdapter {
    Context context;
    ArrayList<String> data = new ArrayList<>();
    int type = 0;

    public Menu_adapter(Context context, ArrayList<String> data, int type) {
        this.context = context;
        this.data = data;
        this.type = type;
    }

    @Override
    public int getViewTypeCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item, null);

        } else {
            holder = (viewholder) convertView.getTag();
        }

        holder = new viewholder();
        holder.label = (TextView) convertView.findViewById(R.id.text);
        holder.label.setText(data.get(position));


        return convertView;
    }

    class viewholder {
        TextView label;
    }
}
