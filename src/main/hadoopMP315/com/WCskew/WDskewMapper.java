package com.WCskew;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/16.
 */
public class WDskewMapper extends Mapper<Text,Text,Text,IntWritable> {


    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
                context.write(key,new IntWritable(Integer.parseInt(value.toString())));
    }
}
