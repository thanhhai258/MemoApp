package com.example.memoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<MemoItem> {
    private Context context;
    protected int resource;
    List<MemoItem> memoItemList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<MemoItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.memoItemList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_memo, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTvId = convertView.findViewById(R.id.tv_id);
            viewHolder.mTitle = convertView.findViewById(R.id.memo_tilte_text);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MemoItem memoItem = memoItemList.get(position);
        viewHolder.mTvId.setText(String.valueOf(memoItem.getmId()));
        viewHolder.mTitle.setText(memoItem.getmTilte());


        return convertView;
    }

    public class ViewHolder {
        public TextView mTvId;
        private TextView mTitle;

    }

    //item click disable
    @Override
    public boolean isEnabled(int position) {
        if (MainActivity.ONFF == 1) {
            return false;
        } else {
            return true;
        }
    }
}