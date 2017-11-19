package com.texthbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by sssss on 2017/3/27.
 */
public class hbase02 {
    @Test
    public void testBatchAndCaching() throws Exception {

        Configuration conf= HBaseConfiguration.create();
       Connection conn=ConnectionFactory.createConnection(conf);

       //获取名字
        TableName tname=TableName.valueOf("ns1:t7");

        Scan scan=new Scan();
        //设置扫描缓存
        scan.setCaching(2);
        //分批处理个数
        scan.setBatch(4);
        //
        Table t=conn.getTable(tname);
        //结果扫描集合
        ResultScanner rs=t.getScanner(scan);
        //
        Iterator<Result>it=rs.iterator();

        while(it.hasNext()){
            Result r=it.next();
        }

    }
    }