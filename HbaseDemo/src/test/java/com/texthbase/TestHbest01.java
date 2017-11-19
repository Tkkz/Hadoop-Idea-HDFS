package com.texthbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;



/**
 * Created by sssss on 2017/3/26.
 */
public class TestHbest01 {



    @Test
    public void UDF() throws Exception {
        long start=System.currentTimeMillis();
        Configuration conf= HBaseConfiguration.create();
        Connection conn=ConnectionFactory.createConnection(conf);
        TableName tname=TableName.valueOf("ns1:t1");
        HTable table=(HTable) conn.getTable(tname);

        table.setAutoFlush(false);
        for (int i=4;i<1000000;i++){
            Put put = new Put(Bytes.toBytes("row" + i));
            put.setWriteToWAL(false);
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom" + i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), Bytes.toBytes(i % 100));
            table.put(put);

            if (i % 2000 == 0) {
                table.flushCommits();
            }
        }
        //
        table.flushCommits();
        System.out.println(System.currentTimeMillis() - start);
    }
}

