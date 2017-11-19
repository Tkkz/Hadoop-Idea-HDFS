package com.HdfsDATAqx;

import org.apache.hadoop.io.InputBuffer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区
 * Created by sssss on 2017/3/18.
 */
public class MyPartitioner extends Partitioner<Text,IntWritable> {

    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {

        return 0;

    }
}
