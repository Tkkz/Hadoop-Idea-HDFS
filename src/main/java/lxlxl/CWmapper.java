package lxlxl;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/14.
 */                                     //<word 1>
public class CWmapper extends Mapper<LongWritable,Text,Text,IntWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        IntWritable valueout=new IntWritable();
        Text keyout=new Text();
        String[] arr=value.toString().split(" ");
        for(String s:arr){
            keyout.set(s);
            valueout.set(1);
            context.write(keyout,valueout);
        }


    }
}
