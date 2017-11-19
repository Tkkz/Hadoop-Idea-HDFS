package comTempMaxAllsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by sssss on 2017/3/18.
 */
public class TempMap extends Mapper<IntWritable,IntWritable,IntWritable,IntWritable>{


    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {
        context.write(key,value);


    }
}
