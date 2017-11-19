package com.hadoopMR;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 设置分区类
 * 数据倾斜的分区
 *
 */
public class Mypartitioner extends Partitioner<Text,IntWritable>{


    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        return 0;
    }
}
