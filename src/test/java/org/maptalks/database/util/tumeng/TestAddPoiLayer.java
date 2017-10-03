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
import java.util.List;

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
//        this.addPoiLayer();
    }

    private void addPoiLayer(String id, String name, String idPrefix)  throws IOException, RestException,
            InvalidLayerException{
        String layerId = idPrefix + id;//"shape_tum_zhuzhaixq";
        this.removeLayer(mapDatabase, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName(name); //住宅小区
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
