package org.maptalks.database.util.tumeng;

import org.junit.Before;
import org.junit.Test;
import org.maptalks.javasdk.MapDatabase;
import org.maptalks.javasdk.db.Layer;
import org.maptalks.javasdk.db.LayerField;
import org.maptalks.javasdk.exceptions.InvalidLayerException;
import org.maptalks.javasdk.exceptions.RestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2017/9/29.
 */
public class TestAddPoiLayer {

    private MapDatabase mapDatabase;

    @Before
    public void init() {
        mapDatabase = new MapDatabase("121.40.37.230", 8090, "MAPTALKS_ULA");
    }

    @Test
    public void testAddAllLayer() throws IOException, RestException,
            InvalidLayerException {
        String idPrefix = "shape_tum_";
        Map<String, String> layerMap = new HashMap<String, String>();
        layerMap.put("baoxiangs","保险公司");
        layerMap.put("canyinfw","餐饮服务");
        layerMap.put("daolufsss","道路附属设施");
        layerMap.put("feijic","飞机场");
        layerMap.put("fengjingms","风景名胜");
        layerMap.put("gangkoumt","港口码头");
        layerMap.put("gaodengyx","高等院校");
        layerMap.put("gonggongss","公共设施");
        layerMap.put("gongjianfajg","公检法机关");
        layerMap.put("gongshangswjg","工商税务机构");
        layerMap.put("gongsiqy","公司企业");
        layerMap.put("gongyuan","公园");
        layerMap.put("gouwufw","购物服务");
        layerMap.put("guidaojt","城市轨道交通");
        layerMap.put("guoshengjjd","国省级景点");
        layerMap.put("huochezhan","火车站");
        layerMap.put("jiaotongdm","交通地名");
        layerMap.put("jiaotongssfw","交通设施服务");
        layerMap.put("jinrongbxjg","金融保险机构");
        layerMap.put("kejiaowhfw","科教文化服务");
        layerMap.put("louyuds","楼宇大厦");
        layerMap.put("motuochefw","摩托车服务");
        layerMap.put("putongyy","普通医院");
        layerMap.put("qiao","桥");
        layerMap.put("qichexgfw","汽车相关服务");
        layerMap.put("quxianjizf","区县级政府");
        layerMap.put("sanjiayy","三甲医院");
        layerMap.put("shangwuzz","商务住宅");
        layerMap.put("shenghuofw","生活服务");
        layerMap.put("shuiximc","水系名称");
        layerMap.put("tiyuxxfw","体育休闲服务");
        layerMap.put("wenhuacgfw","文化场馆服务");
        layerMap.put("wuxingjbg","五星级宾馆");
        layerMap.put("xiangzhendm","乡镇地名");
        layerMap.put("xingzhengdm","行政地名");
        layerMap.put("yiliaoxgfw","医疗相关服务");
        layerMap.put("yinhang","银行");
        layerMap.put("yinhangxgfw","银行相关服务");
        layerMap.put("zhengfujgtt","政府机关及社会团体");
        layerMap.put("zhengquangs","证券公司");
        layerMap.put("zhongxiaoxuexiao","中小学校");
        layerMap.put("zhusufw","住宿服务");
        layerMap.put("zhuzhaixq","住宅小区");
        layerMap.put("zirandm","自然地名");
        for (Map.Entry<String, String> entry : layerMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            this.addPoiLayer(entry.getKey(), entry.getValue(), idPrefix);

        }
    }

    private void addPoiLayer(String id, String name, String idPrefix)  throws IOException, RestException,
            InvalidLayerException{
        String layerId = idPrefix + id;//"shape_tum_zhuzhaixq";
        this.removeLayer(mapDatabase, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName(name);
        List<LayerField> list = new ArrayList<LayerField>();
        list.add(this.needString("name", 300));
        list.add(this.needString("type", 30));
        list.add(this.needString("code", 30));
        list.add(this.needString("address", 300));
        list.add(this.needString("telephone", 60));
        layer.setFields(list);
        mapDatabase.addLayer(layer);
    }

    private LayerField needString(String name, int length) {
        return this.needField(name, "VARCHAR(" + length + ")", length);
    }

    private LayerField needField(String name, String dataTypeStr, int fieldSize) {
        LayerField field = new LayerField();
        field.setFieldName(name);
        field.setDataType(dataTypeStr);
        field.setFieldSize(fieldSize);
        return field;
    }

    /**
     * 移除图层
     * @param mapDatabase 空间库访问对象
     * @param layerId 图层id
     * @throws IOException
     * @throws RestException
     * @throws InvalidLayerException
     */
    private void removeLayer(MapDatabase mapDatabase, String layerId)  throws IOException, RestException,
            InvalidLayerException {
        Layer layer = mapDatabase.getLayer(layerId);
        if (layer != null) {
            mapDatabase.removeLayer(layerId);
        }
    }

}
