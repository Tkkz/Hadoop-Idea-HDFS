package com.HdfsDATAqx;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * App
 * Created by sssss on 2017/3/18.
 */
public class PartitionerApp {
    public static void main(String[] args) throws Exception {
        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job= Job.getInstance(conf);
        job.setJobName("PartitionerApp");
        job.setJarByClass(PartitionerApp.class);

        //设置输入输出路径
        FileInputFormat.addInputPath(job,new Path("d:/HadoopExercise/word"));
        FileOutputFormat.setOutputPath(job,new Path("d:/HadoopExercise/word/out"));

        //设置输入格式
        job.setInputFormatClass(TextInputFormat.class);
         //设置分区类
        job.setPartitionerClass(MyPartitioner.class);
        //设置MR类
        job.setMapperClass(PartitionerMap.class);
        job.setReducerClass(PartitionerReduce.class);

        //设置M的输出格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置M的个数
        job.setNumReduceTasks(3);
        //设置zui最终的输出格式
        job.setMapOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.waitForCompletion(true);

    }




}
