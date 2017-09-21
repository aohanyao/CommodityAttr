---
title: Android开发小技巧之商品属性筛选与商品筛选
date: 2016-09-12 09:53:14
tags:
---

#原版代码请查看[这里](https://github.com/aohanyao/CommodityAttr/tree/c1a9e24ebaa450fb3d03d241d5b748bf87cb2d28)

#前言
一周一篇文章，果真是不太容易。顺便吐槽一下上周也就是9月5号的文章，几天之内就耗完了我1.4G的空间流量，吓得我都抽搐了。

这个次为大家带来的是一个完整的商品属性筛选与商品筛选。什么意思？都见过淘宝、京东等爱啪啪吧，里面有个商品详情，可以选择商品的属性，然后筛选出这个商品的具体型号，这样应该知道了吧？不知道也没关系，下面会有展示图。
![筛选属性最终完成](筛选属性最终完成.gif)

关于商品筛选是有两种方式(至少我只见到两种)：

	第一种： 将所有的商品的所有属性及详情返回给客户端，由客户端进行筛选。
			淘宝用的就是这种。
	第二种： 将所有的属性返回给客户端，客户选择完成属性后将属性发送给后台
			，再由后台根据属性筛选出具体商品返回给客户端。
			京东就是这样搞的。。

两种方式各有各的好处：

第一种：体验性特别好，用户感觉不到延迟，立即选中立即就筛选出了详情。就是客户端比较费劲。。。

第二种：客户端比较省时间，但是体验性太差了，你想想，在网络不是很通畅的时候，你选择一个商品还得等老半天。

因为当时我没有参加到这个接口的设计，导致一直在变化。。我才不会告诉不是后台不给力，筛选不出来才一股脑的将所有锅甩给客户端。


## 技术点
1. 流式布局

		商品的属性并不是一样长的，所以需要自动适应内容的一个控件。
		推荐hongyang的博客。我就是照着那个搞的。
2. RxJava

		不要问我，我不知道，我也是新手，我就是用它做出了效果，至于有没有
		用对，那我就不知道了。反正目的是达到了。
3. Json解析？？？

## 准备

1. [FlowLayout](https://github.com/hongyangAndroid/FlowLayout)
2. [RxJava](https://github.com/ReactiveX/RxJava)

## xml布局
这个部分的布局不是很难，只是代码量较多，咱们就省略吧，直接看效果吧

![布局完成](http://obh9jd33g.bkt.clouddn.com/%E5%95%86%E5%93%81%E5%B1%9E%E6%80%A7%E5%B8%83%E5%B1%80%E5%AE%8C%E6%88%90.png)

可以看到机身颜色、内存、版本下面都是空的，因为我们还没有将属性筛选出来。

## 数据分析
先看看整体的数据结构是怎么样的
![数据结构](http://obh9jd33g.bkt.clouddn.com/%E5%95%86%E5%93%81%E5%B1%9E%E6%80%A7%E6%95%B4%E4%BD%93%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.png)

每一个商品都有一个父类，仅作标识，不参与计算，比如数据中的华为P9就是一个商品的类目，在这下面有着各种属性组成的商品子类，这才是真正的商品。

而一个详细的商品是有三个基础属性所组成：

	1. 版本
	2. 内存
	3. 制式
如上图中一个具体的商品的名称:"华为 P9全网通 3GB+32GB版 流光金 移动联通电信4G手机 双卡双待"

![商品属性据结构](http://obh9jd33g.bkt.clouddn.com/%E5%95%86%E5%93%81%E5%B1%9E%E6%80%A7%E6%8D%AE%E7%BB%93%E6%9E%84.png)

所以，要获得一个具体的商品是非常的简单，只需要客户选中的三个属性与上图中所对应的属性完全相同，就能得到这个商品。其中最关键的还是将所有的商品属性筛选出来。


## 筛选出所有属性及图片
本文中使用的数据是直接从Assets目录中直接读取的。

筛选出该商品的所有属性，怎么做呢？其实也是很简单的，直接for所有商品的所有属性，然后存储起来，去除重复的属性，那么最后剩下的就是该商品的属性了

	 /**
     * 初始化商品信息
     * <li>1. 提取所有的属性</li>
     * <li>2. 提取所有颜色的照片</li>
     */
    private void initGoodsInfo() {
        //所有的颜色
        mColors = new ArrayList<>();
        //筛选过程中临时存放颜色
        mTempColors = new ArrayList<>();
        //所有的内存
        mMonerys = new ArrayList<>();
        //筛选过程中临时的内存
        mTempMonerys = new ArrayList<>();
        //所有的版本
        mVersions = new ArrayList<>();
        //筛选过程中的临时版本
        mTempVersions = new ArrayList<>();
        //获取到所有的商品
        shopLists = responseDto.getMsg().getChilds();
        callBack.refreshSuccess("￥" + responseDto.getPricemin() + " - " + responseDto.getPricemax(), responseDto.getMsg().getParent().getName());
        callBack.parentName(responseDto.getMsg().getParent().getName());
        //遍历商品
        Observable.from(shopLists)
                //转换对象 获取所有商品的属性集合
                .flatMap(childsEntity -> Observable.from(childsEntity.getAttrinfo().getAttrs()))
                .subscribe(attrsEntity -> {
                    //判断颜色
                    if (mActivity.getString(R.string.shop_color).equals(attrsEntity.getAttrname()) && !mTempColors.contains(attrsEntity.getAttrvalue())) {
                        mColors.add(new TagInfo(attrsEntity.getAttrvalue()));
                        mTempColors.add(attrsEntity.getAttrvalue());
                    }
                    //判断制式
                    if (mActivity.getString(R.string.shop_standard).equals(attrsEntity.getAttrname()) && !mTempVersions.contains(attrsEntity.getAttrvalue())) {
                        mVersions.add(new TagInfo(attrsEntity.getAttrvalue()));
                        mTempVersions.add(attrsEntity.getAttrvalue());
                    }
                    //判断内存
                    if (mActivity.getString(R.string.shop_monery).equals(attrsEntity.getAttrname()) && !mTempMonerys.contains(attrsEntity.getAttrvalue())) {
                        mMonerys.add(new TagInfo(attrsEntity.getAttrvalue()));
                        mTempMonerys.add(attrsEntity.getAttrvalue());
                    }
                });

        // 提取出 每种颜色的照片
        tempImageColor = new ArrayList<>();
        mImages = new ArrayList<>();
        //遍历所有的商品列表
        Observable.from(shopLists)
                .subscribe(childsEntity -> {
                    String color = childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue();
                    if (!tempImageColor.contains(color)) {
                        mImages.add(childsEntity.getShowimg());
                        tempImageColor.add(color);
                    }
                });
        // 提取出 每种颜色的照片

        //通知图片
        callBack.changeData(mImages, "￥" + responseDto.getPricemin() + " - " + responseDto.getPricemax());
        callBack.complete(null);
    }

## 初始化属性列表
属性之间是有一些关系的，比如我这里是以颜色为初始第一项，那么我就得根据颜色筛选出这个颜色下的所有内存，然后根据内存筛选出所有的版本。同时，只要颜色、内存、版本三个都选择了，就得筛选出这个商品。

	{颜色>内存>版本}>具体商品

#### 颜色
初始化颜色，设置选择监听，一旦用户选择了某个颜色，那么需要获取这个颜色下的所有内存，并且要开始尝试获取商品详情。

1. 初始化颜色

		/**
	     * 初始化颜色
	     *
	     * @hint
	     */
	    private void initShopColor() {
	        for (TagInfo mColor : mColors) {
	            //初始化所有的选项为未选择状态
	            mColor.setSelect(false);
	        }
	        tvColor.setText("\"未选择颜色\"");
	        mColors.get(colorPositon).setSelect(true);
	        colorAdapter = new ProperyTagAdapter(mActivity, mColors);
	        rlShopColor.setAdapter(colorAdapter);
	        colorAdapter.notifyDataSetChanged();
	        rlShopColor.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
	        rlShopColor.setOnTagSelectListener((parent, selectedList) -> {
	            colorPositon = selectedList.get(0);
	            strColor = mColors.get(colorPositon).getText();
	            // L.e("选中颜色：" + strColor);
	            tvColor.setText("\"" + strColor + "\"");
	            //获取颜色照片
	            initColorShop();
	            //查询商品详情
	            iterationShop();
	        });
	    }

2. 获取颜色下所有的内存和该颜色的照片

		/**
	     * 初始化相应的颜色的商品 获得 图片
	     */
	    private void initColorShop() {
	        //初始化 选项数据
	        Observable.from(mMonerys).subscribe(tagInfo -> {
	            tagInfo.setChecked(true);
	        });
	        L.e("开始筛选颜色下的内存----------------------------------------------------------------------------------");
	        final List<String> tempColorMemery = new ArrayList<>();
	        //筛选内存
	        Observable.from(shopLists)
	                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue().equals(strColor))
	                .flatMap(childsEntity -> Observable.from(childsEntity.getAttrinfo().getAttrs()))
	                .filter(attrsEntity -> mActivity.getString(R.string.shop_monery).equals(attrsEntity.getAttrname()))
	                .subscribe(attrsEntity -> {
	                    tempColorMemery.add(attrsEntity.getAttrvalue());
	                    // L.e("内存："+attrsEntity.getAttrvalue());
	                });
	
	        Observable.from(mTempMonerys)
	                .filter(s -> !tempColorMemery.contains(s))
	                .subscribe(s -> {
	                    L.e("没有的内存：" + s);
	                    mMonerys.get(mTempMonerys.indexOf(s)).setChecked(false);
	                });
	        momeryAdapter.notifyDataSetChanged();
	        L.e("筛选颜色下的内存完成----------------------------------------------------------------------------------");
	
	        //获取颜色的照片
	         ImageHelper.loadImageFromGlide(mActivity, mImages.get(tempImageColor.indexOf(strColor)), ivShopPhoto);
	    }

	
3. 根据选中的属性查询是否存在该商品

		/**
	     * 迭代 选择商品属性
	     */
	    private void iterationShop() {
	        //  选择的内存           选择的版本           选择的颜色
	        if (strMemory == null || strVersion == null || strColor == null)
	            return;
	        //隐藏购买按钮 显示为缺货
	        resetBuyButton(false);
	        Observable.from(shopLists)
	                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue().equals(strColor))
	                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(1).getAttrvalue().equals(strVersion))
	                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(2).getAttrvalue().equals(strMemory))
	                .subscribe(childsEntity -> {
	                    L.e(childsEntity.getShopprice());
	                    tvPrice.setText("￥" + childsEntity.getShopprice());
	                    // ImageHelper.loadImageFromGlide(mActivity, Constant.IMAGE_URL + childsEntity.getShowimg(), ivShopPhoto);
	                    L.e("已找到商品：" + childsEntity.getName() + " id:" + childsEntity.getPid());
	                    selectGoods = childsEntity;
	                    tvShopName.setText(childsEntity.getName());
	                    //显示购买按钮
	                    resetBuyButton(true);
	                    initShopStagesCount++;
	                });
	    }


#### 内存
通过前面一步，已经获取了所有的内存。这一步只需要展示该所有内存，设置选择监听，选择了某个内存后就根据 选择颜色>选择内存 获取所有的版本。并在在其中也是要iterationShop()查询商品的，万一你是往回点的时候呢？

1. 初始化版本

		/**
	     * 初始化内存
	     */
	    private void initShopMomery() {
	        for (TagInfo mMonery : mMonerys) {
	            mMonery.setSelect(false);
	            Log.e("  ", "initShopMomery: " + mMonery.getText());
	        }
	        tvMomey.setText("\"未选择内存\"");
	        mMonerys.get(momeryPositon).setSelect(true);
	        //-----------------------------创建适配器
	        momeryAdapter = new ProperyTagAdapter(mActivity, mMonerys);
	        rlShopMomery.setAdapter(momeryAdapter);
	        rlShopMomery.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
	        rlShopMomery.setOnTagSelectListener((parent, selectedList) -> {
	            momeryPositon = selectedList.get(0);
	            strMemory = mMonerys.get(momeryPositon).getText();
	            // L.e("选中内存：" + strMemory);
	            iterationShop();
	            tvMomey.setText("\"" + strMemory + "\"");
	            iterationVersion();
	        });
	    }

2. 根据已选择的颜色和内存获取到版本

		/**
	     * 迭代 获取版本信息
	     */
	    private void iterationVersion() {
	        if (strColor == null || strMemory == null) {
	            return;
	        }
	        // L.e("开始迭代版本");
	        Observable.from(mVersions).subscribe(tagInfo -> {
	            tagInfo.setChecked(true);
	        });
	        final List<String> iterationTempVersion = new ArrayList<>();
	        //1. 遍历出 这个颜色下的所有手机
	        //2. 遍历出 这些手机的所有版本
	        Observable.from(shopLists)
	                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue().equals(strColor))
	                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(2).getAttrvalue().equals(strMemory))
	                .flatMap(childsEntity -> Observable.from(childsEntity.getAttrinfo().getAttrs()))
	                .filter(attrsEntity -> attrsEntity.getAttrname().equals(mActivity.getString(R.string.shop_standard)))
	                .subscribe(attrsEntity -> {
	                    iterationTempVersion.add(attrsEntity.getAttrvalue());
	                });
	
	        Observable.from(mTempVersions).filter(s -> !iterationTempVersion.contains(s)).subscribe(s -> {
	            mVersions.get(mTempVersions.indexOf(s)).setChecked(false);
	        });
	        versionAdapter.notifyDataSetChanged();
	        // L.e("迭代版本完成");
	    }


#### 版本
其实到了这一步，已经算是完成了，只需要设置监听，获取选中的版本，然后开始查询商品。


		/**
	     * 初始化版本
	     */
	    private void initShopVersion() {
	        for (TagInfo mVersion : mVersions) {
	            mVersion.setSelect(false);
	        }
	        tvVersion.setText("\"未选择版本\"");
	        mVersions.get(versionPositon).setSelect(true);
	        //-----------------------------创建适配器
	        versionAdapter = new ProperyTagAdapter(mActivity, mVersions);
	        rlShopVersion.setAdapter(versionAdapter);
	        rlShopVersion.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
	        rlShopVersion.setOnTagSelectListener((parent, selectedList) -> {
	            versionPositon = selectedList.get(0);
	            strVersion = mVersions.get(versionPositon).getText();
	            // L.e("选中版本：" + strVersion);
	            iterationShop();
	            tvVersion.setText("\"" + strVersion + "\"");
	        });
	    }


##完成
最终效果图如下：

![筛选属性最终完成](筛选属性最终完成.gif)

不要在意后面的轮播图，那其实很简单的。


## 最后
文采不是很好啊。不是很完美的表达出我自己的想法
如果有可能，能给我start吗？
[源码地址]()


<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="知识共享许可协议" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br />本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。