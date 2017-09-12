package com.aohanyao.commodityattr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aohanyao.commodityattr.model.TagInfo;
import com.aohanyao.commodityattr.ui.OnInitSelectedPosition;

import java.util.List;

import aohanyao.com.commodityattr.R;


/**
 * 商品属性
 */
public class PropertyTagAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private Context mContext;
    private List<TagInfo> mDataList;

    public PropertyTagAdapter(Context mContext, List<TagInfo> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        TagInfo tagInfo = mDataList.get(position);
        textView.setEnabled(tagInfo.isChecked());
        view.setEnabled(tagInfo.isChecked());
//        textView.setFocusable(tagInfo.isChecked());
//        textView.setClickable(tagInfo.isChecked());
        textView.setText(tagInfo.getText());
        view.setTag(tagInfo);
        return view;
    }

    public void isSelect(int positon) {
        for (int i = 0; i < mDataList.size(); i++) {
            mDataList.get(i).setSelect(positon == i);
        }
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
