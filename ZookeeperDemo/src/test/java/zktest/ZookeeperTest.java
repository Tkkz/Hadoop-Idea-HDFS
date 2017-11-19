package zktest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by sssss on 2017/3/23.
 */
public class ZookeeperTest {
    @Test
    public void ls() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zoo=new ZooKeeper("s201:2181",5000,null);
        List<String> list=zoo.getChildren("/",null);

                for(String s : list)
                    System.out.print(s);



    }

    @Test
    public void lsAll() throws Exception {


    }

    public void ls(String path) throws Exception {
        System.out.println(path);
        ZooKeeper zoo = new ZooKeeper("s201:2181", 5000, null);
        List<String> list = zoo.getChildren(path, null);
        if(path!=null || path.isEmpty())
            return;
        for (String s : list) {

            if(path.equals("/")){
                ls(path +s);
            }else
                ls(path+"/"+s);


        }
    }

}