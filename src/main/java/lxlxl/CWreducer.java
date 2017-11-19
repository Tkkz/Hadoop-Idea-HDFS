package lxlxl;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/14.
 */
public class CWreducer extends Reducer<Text,IntWritable,Text,IntWritable> {

    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        for(IntWritable i:values){
            count=count+i.get();
        }
        context.write(key,new IntWritable(count));





    }
}

