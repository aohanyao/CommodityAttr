package com.aohanyao.commodityattr.model;

import java.util.List;

/**
 * <p>作者：江俊超 on 2016/6/18 10:06</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>商品详情返回数据对象</p>
 */
public class ShopDeialResponseDto {


    private String retvalue;

    private DataEntity msg;
    private String pricemax;
    private String pricemin;

    public String getRetvalue() {
        return retvalue;
    }

    public void setRetvalue(String retvalue) {
        this.retvalue = retvalue;
    }

    public DataEntity getMsg() {
        return msg;
    }

    public void setMsg(DataEntity msg) {
        this.msg = msg;
    }

    public String getPricemax() {
        //裁剪
        int i = pricemax.indexOf(".")+3;
        String t=pricemax;

        return t.substring(0,i);
    }

    public void setPricemax(String pricemax) {
        this.pricemax = pricemax;
    }

    public String getPricemin() {
        //裁剪
        int i = pricemin.indexOf(".")+3;
        String t=pricemin;

        return t.substring(0,i);
    }

    public void setPricemin(String pricemin) {
        this.pricemin = pricemin;
    }

    public static class DataEntity {

        private ParentEntity parent;

        private List<ChildsEntity> childs;

        public ParentEntity getParent() {
            return parent;
        }

        public void setParent(ParentEntity parent) {
            this.parent = parent;
        }

        public List<ChildsEntity> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsEntity> childs) {
            this.childs = childs;
        }

        public static class ParentEntity {
            private String Row;
            private String pid;
            private String psn;
            private String cateid;
            private String brandid;
            private String name;
            private String shopprice;
            private String marketprice;
            private String costprice;
            private String pstate;
            private String isbest;
            private String ishot;
            private String isnew;
            private String displayorder;
            private String weight;
            private String showimg;
            private String salecount;
            private String visitcount;
            private String reviewcount;
            private String star;
            private String putawaytime;
            private String description;
            private String ParInfo;
            private String ServiceTxt;
            private String parentid;
            private String attrnames;

            public String getRow() {
                return Row;
            }

            public void setRow(String Row) {
                this.Row = Row;
            }

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

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getBrandid() {
                return brandid;
            }

            public void setBrandid(String brandid) {
                this.brandid = brandid;
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

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getCostprice() {
                return costprice;
            }

            public void setCostprice(String costprice) {
                this.costprice = costprice;
            }

            public String getPstate() {
                return pstate;
            }

            public void setPstate(String pstate) {
                this.pstate = pstate;
            }

            public String getIsbest() {
                return isbest;
            }

            public void setIsbest(String isbest) {
                this.isbest = isbest;
            }

            public String getIshot() {
                return ishot;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public String getIsnew() {
                return isnew;
            }

            public void setIsnew(String isnew) {
                this.isnew = isnew;
            }

            public String getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(String displayorder) {
                this.displayorder = displayorder;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getShowimg() {
                return showimg;
            }

            public void setShowimg(String showimg) {
                this.showimg = showimg;
            }

            public String getSalecount() {
                return salecount;
            }

            public void setSalecount(String salecount) {
                this.salecount = salecount;
            }

            public String getVisitcount() {
                return visitcount;
            }

            public void setVisitcount(String visitcount) {
                this.visitcount = visitcount;
            }

            public String getReviewcount() {
                return reviewcount;
            }

            public void setReviewcount(String reviewcount) {
                this.reviewcount = reviewcount;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getPutawaytime() {
                return putawaytime;
            }

            public void setPutawaytime(String putawaytime) {
                this.putawaytime = putawaytime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getParInfo() {
                return ParInfo;
            }

            public void setParInfo(String ParInfo) {
                this.ParInfo = ParInfo;
            }

            public String getServiceTxt() {
                return ServiceTxt;
            }

            public void setServiceTxt(String ServiceTxt) {
                this.ServiceTxt = ServiceTxt;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getAttrnames() {
                return attrnames;
            }

            public void setAttrnames(String attrnames) {
                this.attrnames = attrnames;
            }
        }

        public static class ChildsEntity {
            private String pid;
            private String psn;
            private String cateid;
            private String brandid;
            private String name;
            private String shopprice;
            private String marketprice;
            private String costprice;
            private String pstate;
            private String isbest;
            private String ishot;
            private String isnew;
            private String displayorder;
            private String weight;
            private String showimg;
            private String salecount;
            private String visitcount;
            private String reviewcount;
            private String star;
            private String putawaytime;
            private String description;
            private String ParInfo;
            private String ServiceTxt;
            private String parentid;
            /**
             * attrnames : 颜色,容量,版本
             * attrs : [{"attrvalueid":"4","attrvalue":"玫瑰金","isinput":"1","attrid":"1","attrname":"颜色","attrdisplayorder":"1","showtype":"0","attrvaluedisplayorder":"1","attrgroupid":"1","attrgroupname":"基础属性","attrgroupdisplayorder":"1"},{"attrvalueid":"33","attrvalue":"全网通版","isinput":"1","attrid":"4","attrname":"制式","attrdisplayorder":"1","showtype":"0","attrvaluedisplayorder":"1","attrgroupid":"1","attrgroupname":"基础属性","attrgroupdisplayorder":"1"},{"attrvalueid":"47","attrvalue":"16GB","isinput":"1","attrid":"5","attrname":"内存","attrdisplayorder":"1","showtype":"0","attrvaluedisplayorder":"1","attrgroupid":"1","attrgroupname":"基础属性","attrgroupdisplayorder":"1"}]
             */

            private AttrinfoEntity attrinfo;

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

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getBrandid() {
                return brandid;
            }

            public void setBrandid(String brandid) {
                this.brandid = brandid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShopprice() {
                //裁剪
                int i = shopprice.indexOf(".")+3;
                String t=shopprice;

                return t.substring(0,i);
            }

            public void setShopprice(String shopprice) {
                this.shopprice = shopprice;
            }

            public String getMarketprice() {
                return marketprice;
            }

            public void setMarketprice(String marketprice) {
                this.marketprice = marketprice;
            }

            public String getCostprice() {
                return costprice;
            }

            public void setCostprice(String costprice) {
                this.costprice = costprice;
            }

            public String getPstate() {
                return pstate;
            }

            public void setPstate(String pstate) {
                this.pstate = pstate;
            }

            public String getIsbest() {
                return isbest;
            }

            public void setIsbest(String isbest) {
                this.isbest = isbest;
            }

            public String getIshot() {
                return ishot;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public String getIsnew() {
                return isnew;
            }

            public void setIsnew(String isnew) {
                this.isnew = isnew;
            }

            public String getDisplayorder() {
                return displayorder;
            }

            public void setDisplayorder(String displayorder) {
                this.displayorder = displayorder;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getShowimg() {
                return showimg;
            }

            public void setShowimg(String showimg) {
                this.showimg = showimg;
            }

            public String getSalecount() {
                return salecount;
            }

            public void setSalecount(String salecount) {
                this.salecount = salecount;
            }

            public String getVisitcount() {
                return visitcount;
            }

            public void setVisitcount(String visitcount) {
                this.visitcount = visitcount;
            }

            public String getReviewcount() {
                return reviewcount;
            }

            public void setReviewcount(String reviewcount) {
                this.reviewcount = reviewcount;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getPutawaytime() {
                return putawaytime;
            }

            public void setPutawaytime(String putawaytime) {
                this.putawaytime = putawaytime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getParInfo() {
                return ParInfo;
            }

            public void setParInfo(String ParInfo) {
                this.ParInfo = ParInfo;
            }

            public String getServiceTxt() {
                return ServiceTxt;
            }

            public void setServiceTxt(String ServiceTxt) {
                this.ServiceTxt = ServiceTxt;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public AttrinfoEntity getAttrinfo() {
                return attrinfo;
            }

            public void setAttrinfo(AttrinfoEntity attrinfo) {
                this.attrinfo = attrinfo;
            }

            public static class AttrinfoEntity {
                private String attrnames;
                /**
                 * attrvalueid : 4
                 * attrvalue : 玫瑰金
                 * isinput : 1
                 * attrid : 1
                 * attrname : 颜色
                 * attrdisplayorder : 1
                 * showtype : 0
                 * attrvaluedisplayorder : 1
                 * attrgroupid : 1
                 * attrgroupname : 基础属性
                 * attrgroupdisplayorder : 1
                 */

                private List<AttrsEntity> attrs;

                public String getAttrnames() {
                    return attrnames;
                }

                public void setAttrnames(String attrnames) {
                    this.attrnames = attrnames;
                }

                public List<AttrsEntity> getAttrs() {
                    return attrs;
                }

                public void setAttrs(List<AttrsEntity> attrs) {
                    this.attrs = attrs;
                }

                public static class AttrsEntity {
                    private String attrvalueid;
                    private String attrvalue;
                    private String isinput;
                    private String attrid;
                    private String attrname;
                    private String attrdisplayorder;
                    private String showtype;
                    private String attrvaluedisplayorder;
                    private String attrgroupid;
                    private String attrgroupname;
                    private String attrgroupdisplayorder;

                    public String getAttrvalueid() {
                        return attrvalueid;
                    }

                    public void setAttrvalueid(String attrvalueid) {
                        this.attrvalueid = attrvalueid;
                    }

                    public String getAttrvalue() {
                        return attrvalue;
                    }

                    public void setAttrvalue(String attrvalue) {
                        this.attrvalue = attrvalue;
                    }

                    public String getIsinput() {
                        return isinput;
                    }

                    public void setIsinput(String isinput) {
                        this.isinput = isinput;
                    }

                    public String getAttrid() {
                        return attrid;
                    }

                    public void setAttrid(String attrid) {
                        this.attrid = attrid;
                    }

                    public String getAttrname() {
                        return attrname;
                    }

                    public void setAttrname(String attrname) {
                        this.attrname = attrname;
                    }

                    public String getAttrdisplayorder() {
                        return attrdisplayorder;
                    }

                    public void setAttrdisplayorder(String attrdisplayorder) {
                        this.attrdisplayorder = attrdisplayorder;
                    }

                    public String getShowtype() {
                        return showtype;
                    }

                    public void setShowtype(String showtype) {
                        this.showtype = showtype;
                    }

                    public String getAttrvaluedisplayorder() {
                        return attrvaluedisplayorder;
                    }

                    public void setAttrvaluedisplayorder(String attrvaluedisplayorder) {
                        this.attrvaluedisplayorder = attrvaluedisplayorder;
                    }

                    public String getAttrgroupid() {
                        return attrgroupid;
                    }

                    public void setAttrgroupid(String attrgroupid) {
                        this.attrgroupid = attrgroupid;
                    }

                    public String getAttrgroupname() {
                        return attrgroupname;
                    }

                    public void setAttrgroupname(String attrgroupname) {
                        this.attrgroupname = attrgroupname;
                    }

                    public String getAttrgroupdisplayorder() {
                        return attrgroupdisplayorder;
                    }

                    public void setAttrgroupdisplayorder(String attrgroupdisplayorder) {
                        this.attrgroupdisplayorder = attrgroupdisplayorder;
                    }
                }
            }
        }
    }
}
