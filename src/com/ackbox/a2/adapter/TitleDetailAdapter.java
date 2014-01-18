package com.ackbox.a2.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ackbox.a2.R;
import com.ackbox.a2.model.Displayable;

/**
 * Simple list item adapter with title and detail fields.
 * 
 * @author trein
 * 
 */
public class TitleDetailAdapter extends ArrayAdapter<Displayable> {

    private final Map<Displayable, Integer> mEntriesMap = new HashMap<Displayable, Integer>();

    public TitleDetailAdapter(Context context, List<Displayable> objects) {
        super(context, R.layout.fragment_item_list, objects);

        for (int i = 0; i < objects.size(); ++i) {
            this.mEntriesMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        Displayable item = getItem(position);
        return this.mEntriesMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.fragment_item_list, parent, false);

            viewHolder = new ViewHolderItem();
            viewHolder.titleViewItem = (TextView) convertView.findViewById(R.id.list_item_title);
            viewHolder.detailViewItem = (TextView) convertView.findViewById(R.id.list_item_details);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        Displayable item = getItem(position);

        if (item != null) {
            viewHolder.titleViewItem.setText(item.getTitle(getContext()));
            viewHolder.detailViewItem.setText(item.getDetail(getContext()));
        }

        return convertView;
    }

    static class ViewHolderItem {
        TextView titleViewItem;
        TextView detailViewItem;
    }

}
