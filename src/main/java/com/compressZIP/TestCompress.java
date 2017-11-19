package com.compressZIP;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by sssss on 2017/3/13.
 */
    public class TestCompress {


        public static void main(String [] argsss) throws Exception {
            Class[] zipClasses = {
                    DeflateCodec.class,
                    GzipCodec.class,
                    BZip2Codec.class,
                    Lz4Codec.class,
                    SnappyCodec.class
            };

            for (Class c : zipClasses) {
                zip(c);
            }
        }

        /**
         * 压缩测试
         */
        public static void zip(Class codecClass) throws Exception {
            long start = System.currentTimeMillis();
            //实例化对象
            CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, new Configuration());
            //创建文件输出流,得到默认扩展名
            FileOutputStream fos = new FileOutputStream("d:/lxcompress/b" + codec.getDefaultExtension());
            //得到压缩流
            CompressionOutputStream zipOut = codec.createOutputStream(fos);
            IOUtils.copyBytes(new FileInputStream("d:/lxcompress/a.txt"), zipOut, 1024);
            zipOut.close();
            System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));
        }

    /*
    *解压缩
    */

        public void unzip() throws Exception {
            long start = System.currentTimeMillis();
            Class codecClass = DefaultCodec.class;
            CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, new Configuration());

            FileInputStream fis = new FileInputStream("d:/HadoopExercise/1" + codec.getDefaultExtension());
            CompressionInputStream zipin = codec.createInputStream(fis);
            IOUtils.copyBytes(zipin, new FileOutputStream("d:/HadoopExercise/b" + codec.getDefaultExtension() + ".txt"), 1024);
            fis.close();
            System.out.println(System.currentTimeMillis() - start);

    }
}
