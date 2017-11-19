package comTempMax;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

/**
 * Created by sssss on 2017/3/14.
 */
public class TempMaxReducer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {

    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max=Integer.MIN_VALUE;
            for(IntWritable value:values){
                max= max > value.get()? max:value.get();

            }
            context.write(key, new IntWritable(max));




    }
}

