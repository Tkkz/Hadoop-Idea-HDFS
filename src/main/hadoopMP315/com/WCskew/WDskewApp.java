package com.WCskew;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 解决数据倾斜问题  对处理过的数据再处理 再处理过程
 */
public class WDskewApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job= Job.getInstance(conf);
      job.setInputFormatClass(TextInputFormat.class);

        //添加shu'r输入路径
        FileInputFormat.addInputPath(job, new Path("D:/H"));
        //设置输出路径
        FileOutputFormat.setOutputPath(job,new Path("D:/HadoopExercise/out2"));

        job.setReducerClass(WCSkewReducer.class);
        job.setMapperClass(WDskewMapper.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);



        job.setNumReduceTasks(4);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);


    }
}
