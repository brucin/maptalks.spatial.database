package org.maptalks.database.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.maptalks.geojson.*;
import org.maptalks.geojson.json.GeoJSONFactory;
import org.maptalks.javasdk.FeatureLayer;
import org.maptalks.javasdk.MapDatabase;
import org.maptalks.javasdk.exceptions.InvalidLayerException;
import org.maptalks.javasdk.exceptions.RestException;
import org.maptalks.proj4.Proj4Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjun on 2017/9/29.
 */
public class ImportPoiFromGeoJsonTool {

    private static final Logger logger = LoggerFactory.getLogger(ImportPoiFromGeoJsonTool.class);
    private static final CRS DB_CRS = CRS.GCJ02;

    public static void main(String[] args) throws IOException,
            RestException, InvalidLayerException, InvalidFormatException, Proj4Exception{
        MapDatabase mapDb = new MapDatabase("121.40.37.230", 8090, "MAPTALKS_ULA");
        String folderName = "/Users/wangjun/git/GitHub/solution/maptalks.spatial.database/src/main/resources/geojson/";
        String layerPrefixStr = "shape_tum_";
        ImportPoiFromGeoJsonTool.importGeoJsonFromFolder(folderName, layerPrefixStr, mapDb);
    }

    private static void importGeoJsonFromFolder(String folderName, String layerPrefixStr, MapDatabase mapDb) throws IOException, InvalidFormatException, RestException, Proj4Exception, InvalidLayerException {
        File folder = new File(folderName);
        if(folder.isDirectory()) {
            String[] names = folder.list();
            String prefixStr = "shape_tum_";
            for (int i = 0; i < names.length; i++) {
                String fullName = names[i];
                if(fullName.lastIndexOf(".geojson") > 0) {
                    String jsonPath = folderName.concat(fullName);
                    String fileName = fullName.substring(0, fullName.lastIndexOf(".geojson"));
                    String layerId = prefixStr.concat(fileName);
                    FeatureLayer featureLayer = new FeatureLayer(layerId, mapDb);
                    ImportPoiFromGeoJsonTool.buildGeometryFromGeoJson(featureLayer, jsonPath, 0, 1);
                }
            }
        }
    }

    private static void buildGeometryFromGeoJson(FeatureLayer featureLayer, String path, int start, int step)
            throws IOException, InvalidFormatException, RestException, Proj4Exception, InvalidLayerException {
        System.out.println("start:" + start);
        System.out.println("step:" + step);
        List<String> lines = FileTool.read2000LineFromFile(path, start, step);
        System.out.println("lines length : " + lines.size());
        if(lines != null && lines.size() > 0) {
            List<Feature> geometries = new ArrayList<Feature>();
            String line, lastChar;
            for (int i = 0; i < lines.size(); i++) {
                line = lines.get(i);
                if(line != null && line.indexOf("properties") >=0 && line.indexOf("Feature") >= 0) {
                    lastChar = line.substring(line.length()-1);
                    if(lastChar.equals(",")) line = line.substring(0, line.length()-1);
                    Feature feature = (Feature)GeoJSONFactory.create(line);
                    feature.setProperties(ImportPoiFromGeoJsonTool.convertProperties(feature.getProperties()));
                    geometries.add(feature);
                }
            }
            System.out.println("size:"+ geometries.size());
            addToDatabase(featureLayer, geometries);
            buildGeometryFromGeoJson(featureLayer, path, (start + 2000), step+1);
        }
    }

    private static Map<String, Object> convertProperties(Map<String, Object> properties) {
        Map<String, Object> props = new HashMap<String, Object>();
        for (String key : properties.keySet()) {
            Object value = properties.get(key);
            if(value == null) continue;
            if(key.equals("Name")) {
                props.put("name", value);
            } else if(key.equals("Ctype")) {
                props.put("type", value);
            } else if(key.equals("Ntype")) {
                props.put("code", value);
            } else if(key.equals("Address")) {
                props.put("address", value);
            } else if(key.equals("Telephone")) {
                props.put("telephone", value);
            }
        }
        return props;
    }


    private static void addToDatabase(FeatureLayer featureLayer, List<Feature> geometries)  throws IOException,
            RestException, InvalidLayerException, InvalidFormatException, Proj4Exception{
        int length = geometries.size();
        if(length <= 500) {
            System.out.println("添加到数据库...");
            featureLayer.add(geometries, CRS.GCJ02);
        } else {
            int group = (int)Math.ceil(length/500.0);
            System.out.println("group:" + group);
            for (int i = 0; i < group; i++) {
                int now = i + 1;
                System.out.println("导入第："+now+"组");
                int end = now*500;
                if(end > length) end = length;
                System.out.println("start:" + i*500);
                System.out.println("end:" + end);
                List<Feature> geometryList = geometries.subList(i*500, end);
                addToDatabase(featureLayer, geometryList);
            }
        }
    }

}
