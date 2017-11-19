package comTempMax;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/14.
 */                                     //<word 1>
public class TmepMaxMapper extends Mapper<LongWritable,Text,IntWritable,IntWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String arr[] =value.toString().split(" ");
        context.write(new IntWritable(Integer.parseInt(arr[0])),new IntWritable(Integer.parseInt(arr[1])));


    }
}
