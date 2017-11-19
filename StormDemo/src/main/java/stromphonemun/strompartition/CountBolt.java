package stromphonemun.strompartition;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sssss on 2017/4/5.
 */
public class CountBolt implements IRichBolt{
    private Map<String,Integer>map;
    private TopologyContext context;
    private OutputCollector collector;

    private long lastEmitTime=0;

    private long duration=5000;



    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

        this.context=context;
        this.collector=collector;
        map =new HashMap<String, Integer>();

    }

    public void execute(Tuple input) {

        String word=input.getString(0);


    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}

