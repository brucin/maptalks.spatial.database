package org.maptalks.database.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maptalks.geojson.CRS;
import org.maptalks.javasdk.MapDatabase;
import org.maptalks.javasdk.db.DBInfo;
import org.maptalks.javasdk.db.InstallSettings;
import org.maptalks.javasdk.exceptions.RestException;

import java.io.IOException;

/**
 * Created by wangjun on 2017/9/27.
 */

public class TestAddSpatialDatabase {

    private MapDatabase mapDatabase;

    @Before
    public void init() {
        mapDatabase = new MapDatabase("121.40.37.230", 8090, "maptalks_baoshan");
    }

    @Test
    public void testAddSpatialDatabase() throws IOException, RestException {
        /**
         * 创建数据库
         */
//        InstallSettings settings = new InstallSettings();
//        CRS crs = CRS.GCJ02;
//        settings.setCRS(crs);
//        mapDatabase.install(settings);
        /**
         * 查看空间库信息
         */
        DBInfo info = mapDatabase.getDatabaseInfo();
        Assert.assertTrue(info.getName().equals("maptalks_baoshan"));
    }

}
