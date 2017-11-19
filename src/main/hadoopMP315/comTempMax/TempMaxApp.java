package comTempMax;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by sssss on 2017/3/14.
 */
public class TempMaxApp {
    public static void main(String[] args) throws Exception {

        Configuration conf=new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job=Job.getInstance(conf);

        job.setJobName("TempMaxApp");
        job.setJarByClass(TempMaxApp.class);
        //设置输入类型

        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setNumReduceTasks(1);
        //设置MR类
        job.setMapperClass(TmepMaxMapper.class);
        job.setReducerClass(TempMaxReducer.class);
        //设置M类输出的KV
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终类的输出类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);



    }
    }