package org.maptalks.database.util;

import org.maptalks.geojson.CRS;
import org.maptalks.javasdk.MapDatabase;
import org.maptalks.javasdk.db.InstallSettings;
import org.maptalks.javasdk.exceptions.RestException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 2017/9/27.
 */
public class FileTool {

    public static String readFile(String Path){
        BufferedReader reader = null;
        String content = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                content += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    public static List<String> readLargerFile(String Path){
        List<String> lines = new ArrayList<String>();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(Path)));
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);// 10M缓存
            String temp = null;
            temp = reader.readLine();
            while (temp != null) {
                lines.add(temp);
                temp = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public static List<String> read2000LineFromFile(String Path, int start, int step){
        List<String> lines = new ArrayList<String>();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(Path)));
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024 * 1024);// 10M缓存
            String temp = null;
            temp = reader.readLine();
            int  index = 0;
            while (temp != null) {
                if(index < 2000 * step && index >= start) {
                    lines.add(temp);
                }
                temp = reader.readLine();
                index ++;
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

}
