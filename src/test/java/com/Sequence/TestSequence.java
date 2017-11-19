package com.Sequence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;

import org.junit.Test;
import java.util.Random;

/**
 *
 */
public class TestSequence {

    @Test
    public void save() throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);
        Path path=new Path("d:/temp.seq");
        SequenceFile.Writer writer=SequenceFile.createWriter(fs,conf,path,IntWritable.class, IntWritable.class);
        for(int i=0; i<6000;i++){
            int year=1970+new Random().nextInt(1000);
            int temp=-30+new Random().nextInt(100);
            writer.append(new IntWritable(year),new IntWritable(temp));
        }
        for (int i = 0; i < 2000; i++) {
            int year = 2000 + new Random().nextInt(5);
            int temp = -30 + new Random().nextInt(100);
            writer.append(new IntWritable(year), new IntWritable(temp));
        }
}
}
