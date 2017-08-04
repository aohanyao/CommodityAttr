package com.aohanyao.commodityattr.model;

/**
 * <p>作者：江俊超 on 2016/6/25 17:11</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>标签</p>
 */
public class TagInfo {
    private boolean isChecked;
    private String text;
    private boolean isSelect;
    private int positon=0;
    public TagInfo(boolean isChecked, String text) {
        this.isChecked = isChecked;
        this.text = text;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public TagInfo(String text) {
        this.isChecked = true;
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
