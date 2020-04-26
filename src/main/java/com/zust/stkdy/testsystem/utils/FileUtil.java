package com.zust.stkdy.testsystem.utils;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static void sloveFile(String path){
        File file=new File(path);
        if(!file.exists()){
            try {
                //创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
