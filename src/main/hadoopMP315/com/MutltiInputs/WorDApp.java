package com.MutltiInputs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * MutilitApp
 * Created by sssss on 2017/3/14.
 */
public class WorDApp {
        public static void main(String[] args) throws Exception {
                

        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");

        Job job=Job.getInstance(conf);

        job.setJobName("WordApp");
        job.setJarByClass(WorDApp.class);
        MultipleInputs.addInputPath(job,new Path("file:///D:/HadoopExercise/wd/txt"), TextInputFormat.class,CWTextmapper.class);
        MultipleInputs.addInputPath(job,new Path("file:///D:/HadoopExercise/wd/seq"), SequenceFileInputFormat.class,CWSeqmapper.class);
        //设置输出目录
        FileOutputFormat.setOutputPath(job,new Path("D:/HadoopExercise/wd/out"));
       // FileInputFormat.addInputPath(job,new Path(args[0]));

        //多格式输出不能固定Map的类
        // job.setMapperClass(CWTextmapper.class);
        job.setReducerClass(CWreducer.class);
        job.setNumReduceTasks(3);
        //设置M类输出的KV
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终类的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.waitForCompletion(true);



    }
    }