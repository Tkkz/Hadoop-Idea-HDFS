package comTempMaxAllsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/18.
 */
public class TempReducer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable>{

    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable i:values){

            max=max>i.get()?max:i.get();
        }
        context.write(key,new IntWritable(max));
    }



}
