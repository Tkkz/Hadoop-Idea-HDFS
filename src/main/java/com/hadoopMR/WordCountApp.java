package com.hadoopMR;

import com.hdfsMR.MyPartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by sssss on 2017/3/15.
 */
public class WordCountApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //conf.set("fs.defaultFS","file:///");

        Job job= Job.getInstance(conf);
        job.setJobName("WordCountApp");
        job.setJarByClass(WordCountApp.class);
        //设置输出格式
//        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //设置最大切片数
        //FileInputFormat.setMaxInputSplitSize(job,10);
        //设置最小切片数
        //FileInputFormat.setMinInputSplitSize(job,5);
        //设置分区类 在map后进行分区
        job.setPartitionerClass(MyPartitioner.class);

        //设置合成类
        job.setCombinerClass(WordCountReduce.class);


        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);
        //设置R的任务个数
        job.setNumReduceTasks(3);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);














    }
}
