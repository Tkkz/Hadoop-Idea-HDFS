package com.WCskew;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/16.
 */
public class WCSkewReducer extends Reducer<Text,IntWritable,Text,IntWritable> {


    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        for(IntWritable in:values){
            count=count+in.get();
            }
        context.write(key,new IntWritable(count));
    }


}
