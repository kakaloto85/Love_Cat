
package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

class SingerAdapter extends BaseAdapter {
    ArrayList<SingerItem> items = new ArrayList<SingerItem>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(SingerItem singerItem) {
        items.add(singerItem);
    }

    @Override
    public SingerItem getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SingerViewer singerViewer = new SingerViewer(getApplicationContext());
        singerViewer.setItem(items.get(i));
        return singerViewer;
    }
}

