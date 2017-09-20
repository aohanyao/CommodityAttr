## 还没有完成，不要使用！！！！
## 还没有完成，不要使用！！！！
## 还没有完成，不要使用！！！！

 先看效果图
![筛选属性最终完成](筛选属性最终完成.gif)


## P
    支持多个属性筛选，不限定属性数量，不限定数据结构
    支持多个属性筛选，不限定属性数量，不限定数据结构
    支持多个属性筛选，不限定属性数量，不限定数据结构
    重要的事情说三遍

[博客地址](http://www.jianshu.com/p/af75427d7a54)
 
## step1 商品信息实体类实现CommodityInf接口

    class CommodityBean implements CommodityInf{
         @Override
          public String getCommodityName() {
            return "商品名称";
         }
    }

   
    
## step2 商品属性实体类实现AttributesValueInf接口
    class AttrsValueBean implements AttributesValueInf{
        @Override
        public String getAttributesValue() {
            return "属性值";
        }

        @Override
        public String getAttributesName() {
            return "属性名称";
        }
    }

    

## step3 遍历所有商品，存放所有属性数据

	//存放商品和属性数据  CommoditySpiderInfo 筛选所需要的数据对象
	List<CommoditySpiderInfo> commoditySpiderInfos = new ArrayList<>();
	//CommodityInfo 商品，实现CommodityInf接口
  	for (CommodityInfo info : commoditys) {
			//info.getAttrinfo().getAttrs() 该商品的所有属性  实现AttributesValueInf接口
            commoditySpiderInfos.add(new CommoditySpiderInfo(info, info.getAttrinfo().getAttrs()));
        }

	
## step4 创建筛选帮助类
	
	CommoditySpiderHelper mCommoditySpiderHelper = new CommoditySpiderHelper(commoditySpiderInfos, new OnSelectCommodityListener<CommoditySpiderInfo>() {
            @Override
            public void onSelectCommodityListener(CommoditySpiderInfo onSelectCommodity) {
                L.e(onSelectCommodity.getName());
                Toast.makeText(MainActivity.this, onSelectCommodity.getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void sortValues(Map<String, Set<String>> sortValues) {
                L.e(sortValues.toString());
            }
        });

## step5 开始筛选

	 	Map<String, String> param = new TreeMap<>();
        param.put("颜色", "钛银灰");
        param.put("制式", "全网通版");
        param.put("国家", "大陆");
        param.put("内存", "3GB+32GB");
        mCommoditySpiderHelper.filter(param);


具体请看demo详情	

## 最后

[源码地址](https://github.com/aohanyao/CommodityAttr/)
