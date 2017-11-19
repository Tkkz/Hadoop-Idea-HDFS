package com.HDFStest;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by sssss on 2017/3/12.
 */
public class HDfsTest {
    /*
        读取
     */
    @Test
    public void testRead() throws IOException {
        ////获得配置对象
        Configuration conf=new Configuration();
        //获取配置
        FileSystem fs=FileSystem.get(conf);
        //获取文件path
        Path path=new Path("hdfs://s201/user/centos/hello.txt");
        //hadoop 的文件输入流 files
        FSDataInputStream fis=fs.open(path);
        //获取字节数组输出流 也就是写入内存
    ByteArrayOutputStream baso=new ByteArrayOutputStream();
        //将文件内容写入字节数组中
        IOUtils.copyBytes(fis,baso,1024);
        fis.close();
        System.out.println(new String(baso.toByteArray()));
        /*
        byte[] bytes = new byte[1024];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = fis.read(bytes)) != -1) {
            baos.write(bytes, 0, len);
        }
        System.out.println(new String(baos.toByteArray()));
         */

    }
    /*
        写入
     */
    @Test
    public void testWrite() throws IOException {
        Configuration conf=new Configuration();
        FileSystem fs=FileSystem.get(conf);
        Path path=new Path("hdfs://s201/user/centos/hello.txt");
        FSDataOutputStream fls=fs.create(new Path("hdfs://s201/user/centos/a.txt"));
        fls.write("nihao man wo hao".getBytes());
        fls.close();
    }
    /*
        定制副本数量和blocksize

     */
    public void testsize() throws IOException {
        //获取配置
        Configuration conf=new Configuration();
        //系统文件获取这个peiz
        FileSystem fs=FileSystem.get(conf);
                                            //路径 覆盖追加 缓存区大小 副本数 块大小
        FSDataOutputStream fls=fs.create(new Path("/user/centos/a.txt"),true,
                20,(short)2,512);
        FileInputStream fis=new FileInputStream("d:/xxx");

        IOUtils.copyBytes(fis,fls,1024);
        fls.close();
        fis.close();
    }

}








