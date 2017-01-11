package com.aohanyao.commodityattr.v2.dto;

import com.aohanyao.commodityattr.v2.model.CommodityAttrInfo;
import com.aohanyao.commodityattr.v2.model.CommodityInfo;

import java.util.List;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>解析的数据对象</p>
 */
public class CommodityInfoAndAttrDto {

    /**
     * retvalue : 0
     * msg : {"parent":{"pid":"42","psn":"20160512152112454","name":"华为 P9","showimg":"http://obh9jd33g.bkt.clouddn.com/20160810152352740.png","attrnames":"颜色,容量,版本"},"childs":[{"pid":"350","name":"华为 P9全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待","shopprice":"2899.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103318292.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"流光金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}}]}
     * pricemax : 3788.0000
     * pricemin : 2628.0000
     */

    private String retvalue;
    private MsgBean msg;
    private String pricemax;
    private String pricemin;

    public String getRetvalue() {
        return retvalue;
    }

    public void setRetvalue(String retvalue) {
        this.retvalue = retvalue;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public String getPricemax() {
        return pricemax;
    }

    public void setPricemax(String pricemax) {
        this.pricemax = pricemax;
    }

    public String getPricemin() {
        return pricemin;
    }

    public void setPricemin(String pricemin) {
        this.pricemin = pricemin;
    }

    public static class MsgBean {

        private ParentBean parent;
        private List<CommodityInfo> childs;

        public ParentBean getParent() {
            return parent;
        }

        public void setParent(ParentBean parent) {
            this.parent = parent;
        }

        public List<CommodityInfo> getChilds() {
            return childs;
        }

        public void setChilds(List<CommodityInfo> childs) {
            this.childs = childs;
        }

        public static class ParentBean {
            /**
             * pid : 42
             * psn : 20160512152112454
             * name : 华为 P9
             * showimg : http://obh9jd33g.bkt.clouddn.com/20160810152352740.png
             * attrnames : 颜色,容量,版本
             */

            private String pid;
            private String psn;
            private String name;
            private String showimg;
            private String attrnames;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPsn() {
                return psn;
            }

            public void setPsn(String psn) {
                this.psn = psn;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShowimg() {
                return showimg;
            }

            public void setShowimg(String showimg) {
                this.showimg = showimg;
            }

            public String getAttrnames() {
                return attrnames;
            }

            public void setAttrnames(String attrnames) {
                this.attrnames = attrnames;
            }
        }


    }
    public static class AttrinfoBean {
        /**
         * attrnames : 颜色,容量,版本
         * attrs : [{"attrvalue":"流光金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]
         */

        private String attrnames;
        private List<CommodityAttrInfo> attrs;

        public String getAttrnames() {
            return attrnames;
        }

        public void setAttrnames(String attrnames) {
            this.attrnames = attrnames;
        }

        public List<CommodityAttrInfo> getAttrs() {
            return attrs;
        }

        public void setAttrs(List<CommodityAttrInfo> attrs) {
            this.attrs = attrs;
        }


    }
}
