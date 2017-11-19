package com.hadoopMR;

import org.apache.hadoop.hdfs.util.EnumCounters;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.htrace.fasterxml.jackson.annotation.JsonFormat;

import java.io.IOException;

/**
 * Mapper
 */
                                    //kin 偏移量,v第一行数据 ,kout 文本 ,vout 1
public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
       //定义输出
        Text keyout=new Text();
        IntWritable valueout=new IntWritable();
        //对文本以空格进行切割
        String [] arr=value.toString().split(" ");

        //对数组中分割的字符进行输出遍历
        for (String s:arr){
               keyout.set(s);
               //<word 1>
               valueout.set(1);
               //输出
               context.write(keyout,valueout);
               context.getCounter("M",Uitl.getInfo(this,"map")).increment(1);
        }
    }
}

