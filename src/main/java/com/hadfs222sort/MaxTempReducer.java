package com.hadfs222sort;

import org.apache.commons.lang.ObjectUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Reducer
 */
public class MaxTempReducer extends Reducer<ComboKey, NullWritable, IntWritable, IntWritable>{

    /**
     */
    protected void reduce(ComboKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        int year = key.getYear();
        int temp = key.getTemp();
        System.out.println("==============>reduce");
        for(NullWritable v : values){
            System.out.println(key.getYear() + " : " + key.getTemp());
        }
        context.write(new IntWritable(year),new IntWritable(temp));
    }
}