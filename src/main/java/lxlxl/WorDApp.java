package lxlxl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by sssss on 2017/3/14.
 */
public class WorDApp {
    public static void main(String[] args) throws Exception {

        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job=Job.getInstance(conf);

        job.setJobName("TempMaxApp");
        job.setJarByClass(WorDApp.class);
        //设置输入类型
          // job.setInputFormatClass(FileInputFormat.class);
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //设置MR类
        job.setMapperClass(CWmapper.class);
        job.setReducerClass(CWreducer.class);
        //设置M类输出的KV
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终类的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.waitForCompletion(true);



    }
    }