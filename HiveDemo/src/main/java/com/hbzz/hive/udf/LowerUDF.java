package com.hbzz.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * Created by sssss on 2017/7/19.
 */
public class LowerUDF extends UDF{

    public Text evaluate(Text str){
        if(null==str.toString()){
            return null;
        }
        //lower 转换成小写
        return new Text(str.toString().toLowerCase());
    }

    public static void main(String[] args) {
        System.out.println(new LowerUDF().evaluate(new Text("HIVE")));
    }

}
