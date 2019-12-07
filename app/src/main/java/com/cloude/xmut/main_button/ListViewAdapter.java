package com.cloude.xmut.main_button;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloude.xmut.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    List<String> list = new ArrayList<>();
    LayoutInflater inflater = null;

    public ListViewAdapter(List<String> list, Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView == null)
        {
            view = inflater.inflate(R.layout.phone_school_list_item, null);
        }else
            view = convertView;
        TextView textView = (TextView) view.findViewById(R.id.phone_school_text);
        textView.setText(list.get(position));
        return view;
    }
}