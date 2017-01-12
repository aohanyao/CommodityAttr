package com.jjc.commodity.attr.library.util;

import android.text.TextUtils;

/**
 * Created by 江俊超 on 2017/1/12 0012.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>数组集合工具类</p>
 */
public class ArrayUtils {
    /***
     * 遍历整个数据 判断其中的所有值是不是为空
     *
     * @param mSelectAttrValue
     * @return
     */
    public static boolean queryArrayValueIsNull(String[] mSelectAttrValue) {
        for (String array : mSelectAttrValue) {
            if (TextUtils.isEmpty(array)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看数组是否包含了某个值
     *
     * @param mSelectAttrValue
     * @param element
     * @return
     * @throws Exception
     */
    public static int queryArrayContainElement(String[] mSelectAttrValue, String element) throws Exception {
        for (int i = 0; i < mSelectAttrValue.length; i++) {

            if (TextUtils.isEmpty(mSelectAttrValue[i])) {
                throw new Exception("args array value is empty");
            }
            if (mSelectAttrValue[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
