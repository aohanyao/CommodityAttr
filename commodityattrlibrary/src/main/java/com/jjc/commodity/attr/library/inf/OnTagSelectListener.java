package com.jjc.commodity.attr.library.inf;

import com.jjc.commodity.attr.library.ui.FlowTagLayout;

import java.util.List;

/**
 * Created by HanHailong on 15/10/20.
 */
public interface OnTagSelectListener {
    void onItemSelect(FlowTagLayout parent, List<Integer> selectedList);
}
