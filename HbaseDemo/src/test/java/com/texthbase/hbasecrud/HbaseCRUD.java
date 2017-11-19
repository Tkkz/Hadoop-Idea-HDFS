package com.texthbase.hbasecrud;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/29.
 */
public class HbaseCRUD {
    @Test
    public void put() throws Exception {
        //创建conf对象
        Configuration conf = HBaseConfiguration.create();
        //通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        //通过连接查询tableName对象
        TableName tname = TableName.valueOf("ns1:t1");
        //获得table
        Table table = conn.getTable(tname);

        //通过bytes工具类创建字节数组(将字符串)
        byte[] rowid = Bytes.toBytes("row3");

        //创建put对象
        Put put = new Put(rowid);

        byte[] f1 = Bytes.toBytes("f1");
        byte[] id = Bytes.toBytes("id");
        byte[] value = Bytes.toBytes(102);
        put.addColumn(f1, id, value);

        //执行插入
        table.put(put);
    }
    @Test
    public void get() throws IOException {
        Configuration conf=HBaseConfiguration.create();
        Connection conn=ConnectionFactory.createConnection(conf);
        TableName tname=TableName.valueOf("ns1:t1");

        Table table=conn.getTable(tname);

        byte[] rowid=Bytes.toBytes("row3");
        Get get=new Get(rowid);
        Result r=table.get(get);
        byte[] idvalue=r.getValue(Bytes.toBytes("f1"),Bytes.toBytes("id"));
        System.out.println(Bytes.toInt(idvalue));
    }
}
