package com.compressTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.ReflectionUtils;

import org.junit.Test;


import java.io.FileInputStream;
import java.io.FileOutputStream;



/**
 * 测试压缩
 */
public class TestCompress {
    /*
     *压缩
     */
    @Test
    public void zip() throws Exception {

        long start=System.currentTimeMillis();
        //Deflate编辑码器类
        Class codecClass=DefaultCodec.class;
        CompressionCodec codec=(CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());
        //创建文件输出流
        FileOutputStream fos=new FileOutputStream("d:/HadoopExercise/1.deflate");
        //得到压缩流
        CompressionOutputStream zipOut=codec.createOutputStream(fos);
        IOUtils.copyBytes(new FileInputStream("d:/HadoopExercise/duowan_user.txt"),zipOut,1024);
        zipOut.close();
        System.out.println(System.currentTimeMillis()-start);

    }
    /*
     *解压缩
     */
    @Test
    public void unzip() throws Exception {
        long start=System.currentTimeMillis();
        Class codecClass=DefaultCodec.class;
        CompressionCodec codec=(CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());

        FileInputStream fis=new FileInputStream("d:/HadoopExercise/1"+codec.getDefaultExtension());
        CompressionInputStream zipin=codec.createInputStream(fis);
        IOUtils.copyBytes(zipin,new FileOutputStream("d:/HadoopExercise/b"+codec.getDefaultExtension()+".txt"),1024);
        fis.close();
        System.out.println(System.currentTimeMillis()-start);

    }

}


