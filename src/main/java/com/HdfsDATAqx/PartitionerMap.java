package com.HdfsDATAqx;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * map阶段
 */
public class PartitionerMap extends Mapper<LongWritable,Text,Text,IntWritable>{


    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyout=new Text();
        IntWritable valueout=new IntWritable();
        //以空格切分
        String [] arr=value.toString().split(" ");
                for(String s:arr){
                    keyout.set(s);
                    valueout.set(1);
                    context.write(keyout,valueout);
                }
    }
}
