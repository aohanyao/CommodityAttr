package com.aohanyao.commodityattr.model;

import com.aohanyao.commodity.library.inf.AttributesValueInf;
import com.aohanyao.commodity.library.inf.CommodityInf;

import java.util.List;

/**
 * <p>作者：江俊超 on 2016/6/18 10:06</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>商品详情返回数据对象</p>
 */
public class ShopDetailResponseDto {


    /**
     * retvalue : 0
     * msg : {"parent":{"pid":"42","name":"华为 P9","showimg":"http://obh9jd33g.bkt.clouddn.com/20160810152352740.png"},"childs":[{"pid":"350","name":"华为 P9全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待","shopprice":"2899.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103318292.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"流光金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"351","name":"华为 P9全网通 3GB+32GB版 皓月银 移动联通电信4G手机 双卡双待","shopprice":"2948.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103338624.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"皓月银","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"352","name":"华为 P9全网通 3GB+32GB版 钛银灰 移动联通电信4G手机 双卡双待","shopprice":"2848.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103403773.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"钛银灰","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"353","name":"华为 P93GB+32GB版 皓月银 移动4G手机 双卡双待","shopprice":"2628.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103444612.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"皓月银","attrname":"颜色"},{"attrvalue":"移动4G版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"355","name":"华为 P9全网通 4GB+64GB版 玫瑰金 移动联通电信4G手机 双卡双待","shopprice":"3788.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103318292.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"玫瑰金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"4GB+64GB","attrname":"内存"}]}},{"pid":"356","name":"华为 P9全网通 4GB+64GB版 琥珀金 移动联通电信4G手机 双卡双待","shopprice":"3588.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103530766.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"琥珀金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"4GB+64GB","attrname":"内存"}]}},{"pid":"357","name":"华为 P9全网通 4GB+64GB版 陶瓷白 移动联通电信4G手机 双卡双待","shopprice":"3588.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103542777.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"陶瓷白","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"4GB+64GB","attrname":"内存"}]}},{"pid":"358","name":"华为 P93GB+32GB版 皓月银 联通4G手机 双卡双待","shopprice":"2658.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103553423.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"皓月银","attrname":"颜色"},{"attrvalue":"联通4G","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}}]}
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
        /**
         * parent : {"pid":"42","name":"华为 P9","showimg":"http://obh9jd33g.bkt.clouddn.com/20160810152352740.png"}
         * childs : [{"pid":"350","name":"华为 P9全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待","shopprice":"2899.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103318292.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"流光金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"351","name":"华为 P9全网通 3GB+32GB版 皓月银 移动联通电信4G手机 双卡双待","shopprice":"2948.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103338624.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"皓月银","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"352","name":"华为 P9全网通 3GB+32GB版 钛银灰 移动联通电信4G手机 双卡双待","shopprice":"2848.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103403773.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"钛银灰","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"353","name":"华为 P93GB+32GB版 皓月银 移动4G手机 双卡双待","shopprice":"2628.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103444612.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"皓月银","attrname":"颜色"},{"attrvalue":"移动4G版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}},{"pid":"355","name":"华为 P9全网通 4GB+64GB版 玫瑰金 移动联通电信4G手机 双卡双待","shopprice":"3788.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103318292.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"玫瑰金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"4GB+64GB","attrname":"内存"}]}},{"pid":"356","name":"华为 P9全网通 4GB+64GB版 琥珀金 移动联通电信4G手机 双卡双待","shopprice":"3588.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103530766.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"琥珀金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"4GB+64GB","attrname":"内存"}]}},{"pid":"357","name":"华为 P9全网通 4GB+64GB版 陶瓷白 移动联通电信4G手机 双卡双待","shopprice":"3588.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103542777.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"陶瓷白","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"4GB+64GB","attrname":"内存"}]}},{"pid":"358","name":"华为 P93GB+32GB版 皓月银 联通4G手机 双卡双待","shopprice":"2658.0000","showimg":"http://obh9jd33g.bkt.clouddn.com/20160709103553423.png","attrinfo":{"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"皓月银","attrname":"颜色"},{"attrvalue":"联通4G","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}}]
         */

        private ParentBean parent;
        private List<CommodityBean> childs;

        public ParentBean getParent() {
            return parent;
        }

        public void setParent(ParentBean parent) {
            this.parent = parent;
        }

        public List<CommodityBean> getChilds() {
            return childs;
        }

        public void setChilds(List<CommodityBean> childs) {
            this.childs = childs;
        }

        public static class ParentBean {
            /**
             * pid : 42
             * name : 华为 P9
             * showimg : http://obh9jd33g.bkt.clouddn.com/20160810152352740.png
             */

            private String pid;
            private String name;
            private String showimg;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
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
        }

        public static class CommodityBean implements CommodityInf{
            /**
             * pid : 350
             * name : 华为 P9全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待
             * shopprice : 2899.0000
             * showimg : http://obh9jd33g.bkt.clouddn.com/20160709103318292.png
             * attrinfo : {"attrnames":"颜色,容量,版本","attrs":[{"attrvalue":"流光金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]}
             */

            private String pid;
            private String name;
            private String shopprice;
            private String showimg;
            private AttrInfoBean attrinfo;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShopprice() {
                return shopprice;
            }

            public void setShopprice(String shopprice) {
                this.shopprice = shopprice;
            }

            public String getShowimg() {
                return showimg;
            }

            public void setShowimg(String showimg) {
                this.showimg = showimg;
            }

            public AttrInfoBean getAttrinfo() {
                return attrinfo;
            }

            public void setAttrinfo(AttrInfoBean attrinfo) {
                this.attrinfo = attrinfo;
            }

            @Override
            public String getCommodityName() {
                return name;
            }

            public static class AttrInfoBean {
                /**
                 * attrnames : 颜色,容量,版本
                 * attrs : [{"attrvalue":"流光金","attrname":"颜色"},{"attrvalue":"全网通版","attrname":"制式"},{"attrvalue":"3GB+32GB","attrname":"内存"}]
                 */

                private String attrnames;
                private List<AttrsValueBean> attrs;

                public String getAttrnames() {
                    return attrnames;
                }

                public void setAttrnames(String attrnames) {
                    this.attrnames = attrnames;
                }

                public List<AttrsValueBean> getAttrs() {
                    return attrs;
                }

                public void setAttrs(List<AttrsValueBean> attrs) {
                    this.attrs = attrs;
                }

                public static class AttrsValueBean implements AttributesValueInf{
                    /**
                     * attrvalue : 流光金
                     * attrname : 颜色
                     */

                    private String attrvalue;
                    private String attrname;

                    public String getAttrvalue() {
                        return attrvalue;
                    }

                    public void setAttrvalue(String attrvalue) {
                        this.attrvalue = attrvalue;
                    }

                    public String getAttrname() {
                        return attrname;
                    }

                    public void setAttrname(String attrname) {
                        this.attrname = attrname;
                    }

                    @Override
                    public String getAttributesValue() {
                        return attrvalue;
                    }

                    @Override
                    public String getAttributesName() {
                        return attrname;
                    }
                }
            }
        }
    }
}
