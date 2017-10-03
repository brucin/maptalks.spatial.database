package org.maptalks.database.util.district;

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
 * Created by wangjun on 2017/10/3.
 */
public class TestAddDistrictLayer {

    private MapDatabase mapControl;

    @Before
    public void init() {
        mapControl = new MapDatabase("121.40.37.230", 8090, "MAPTALKS_ULA");
    }


    @Test
    public void testAddLayer() throws IOException, RestException,
            InvalidLayerException {
        this.addProvince();
        this.addCity();
        this.addCounty();
        this.addTown();
        this.addVillage();
    }

    private void addProvince()  throws IOException, RestException,
            InvalidLayerException{
        String layerId = "shape_prc_province";
        this.removeLayer(mapControl, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName("省/直辖市");
        List<LayerField> list = new ArrayList<LayerField>();
        list.add(this.needName());
        list.add(this.needAdCode());
        list.add(this.needIsMulti());
        layer.setFields(list);
        mapControl.addLayer(layer);
    }

    private void addCity()  throws IOException, RestException,
            InvalidLayerException{
        String layerId = "shape_prc_city";
        this.removeLayer(mapControl, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName("市");
        List<LayerField> list = new ArrayList<LayerField>();
        list.add(this.needName());
        list.add(this.needAdCode());
        list.add(this.needProvinceCode());
        list.add(this.needProvinceName());
        list.add(this.needIsMulti());
        layer.setFields(list);
        mapControl.addLayer(layer);
    }

    private void addCounty()  throws IOException, RestException,
            InvalidLayerException{
        String layerId = "shape_prc_county";
        this.removeLayer(mapControl, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName("区/县");
        List<LayerField> list = new ArrayList<LayerField>();
        list.add(this.needName());
        list.add(this.needAdCode());
        list.add(this.needProvinceCode());
        list.add(this.needProvinceName());
        list.add(this.needCityCode());
        list.add(this.needCityName());
        list.add(this.needIsMulti());
        layer.setFields(list);
        mapControl.addLayer(layer);
    }

    private void addTown()  throws IOException, RestException,
            InvalidLayerException{
        String layerId = "shape_prc_town";
        this.removeLayer(mapControl, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName("镇/乡");
        List<LayerField> list = new ArrayList<LayerField>();
        list.add(this.needName());
        list.add(this.needAdCode());
        list.add(this.needProvinceCode());
        list.add(this.needProvinceName());
        list.add(this.needCityCode());
        list.add(this.needCityName());
        list.add(this.needCountyCode());
        list.add(this.needCountyName());
        list.add(this.needIsMulti());
        layer.setFields(list);
        mapControl.addLayer(layer);
    }

    private void addVillage()  throws IOException, RestException,
            InvalidLayerException{
        String layerId = "shape_prc_village";
        this.removeLayer(mapControl, layerId);
        Layer layer = new Layer();
        layer.setId(layerId);
        layer.setName("街道/村");
        List<LayerField> list = new ArrayList<LayerField>();
        list.add(this.needName());
        list.add(this.needAdCode());
        list.add(this.needProvinceCode());
        list.add(this.needProvinceName());
        list.add(this.needCityCode());
        list.add(this.needCityName());
        list.add(this.needCountyCode());
        list.add(this.needCountyName());
        list.add(this.needTownCode());
        list.add(this.needTownName());
        list.add(this.needIsMulti());
        layer.setFields(list);
        mapControl.addLayer(layer);
    }

    private LayerField needId() {
        return this.needField("id", "int", 10);
    }

    private LayerField needName() {
        return this.needField("name", "VARCHAR(200)", 200);
    }

    private LayerField needAdCode() {
        return this.needField("code", "VARCHAR(30)", 30);
    }

    private LayerField needProvinceCode() {
        return this.needXCode("province_code");
    }

    private LayerField needProvinceName() {
        return this.needXName("province_name");
    }

    private LayerField needCityCode() {
        return this.needXCode("city_code");
    }

    private LayerField needCityName() {
        return this.needXName("city_name");
    }

    private LayerField needCountyCode() {
        return this.needXCode("county_code");
    }

    private LayerField needCountyName() {
        return this.needXName("county_name");
    }

    private LayerField needTownCode() {
        return this.needXCode("town_code");
    }

    private LayerField needTownName() {
        return this.needXName("town_name");
    }

    private LayerField needIsMulti() {
        return this.needField("ismulti", "CHAR(2)", 2);
    }

    private LayerField needXCode(String code) {
        return this.needField(code, "VARCHAR(30)", 30);
    }

    private LayerField needXName(String name) {
        return this.needField(name, "VARCHAR(200)", 200);
    }

    private LayerField needField(String name, String dataTypeStr, int fieldSize) {
        LayerField field = new LayerField();
        field.setFieldName(name);
        field.setDataType(dataTypeStr);
        field.setFieldSize(fieldSize);
        return field;
    }

    private void removeLayer(MapDatabase mapControl, String layerId)  throws IOException, RestException,
            InvalidLayerException {
        Layer layer = mapControl.getLayer(layerId);
        if (layer != null) {
            mapControl.removeLayer(layerId);
        }
    }
}
