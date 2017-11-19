package comTempMaxAllsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;


/**
 * 全排序
 * Created by sssss on 2017/3/18.
 */
public class TempAllsortApp {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        Job job= Job.getInstance(conf);

        job.setJobName("TempAllsortApp");
        job.setJarByClass(TempAllsortApp.class);

        FileInputFormat.addInputPath(job,new Path("d:/HadoopExercise/temp.seq"));
        FileOutputFormat.setOutputPath(job,new Path("d:/HadoopExercise/out"));
        //输入文件格式
        job.setInputFormatClass(SequenceFileInputFormat.class);


        job.setMapperClass(TempMap.class);
        job.setReducerClass(TempReducer.class);

        job.setNumReduceTasks(3);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);

        //创建随机采样器对象 freq:每个key被选中的概率 numSamples:抽取样本总数
        //maxSplitSampled:最大采样切片数
        InputSampler.Sampler<IntWritable, IntWritable> sampler = new InputSampler.RandomSampler
                <IntWritable, IntWritable>(0.5, 2000, 5);


        //将sample数据写入分区文件
        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), new Path("d:/temp.lst"));
        //设置全排序类
        job.setPartitionerClass(TotalOrderPartitioner.class);

        InputSampler.writePartitionFile(job,sampler);
       // job.waitForCompletion(true);
    }
}
