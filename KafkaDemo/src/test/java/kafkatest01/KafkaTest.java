package kafkatest01;

  import kafka.consumer.ConsumerConfig;
  import org.junit.Test;
  import kafka.javaapi.producer.Producer;
  import kafka.producer.KeyedMessage;
  import kafka.producer.ProducerConfig;
  import scala.collection.immutable.HashMap;
  import scala.collection.immutable.Map;


  import java.util.Properties;

    /**
     * Created by Administrator on 2017/3/31.
     */
    public class  KafkaTest{
        @Test
        public void testSend() {
            Properties props = new Properties();
            //broker列表
            props.put("metadata.broker.list", "s202:9092");
            //串行化
            props.put("serializer.class", "kafka.serializer.StringEncoder");
            //
            props.put("request.required.acks", "1");

            //创建生产者配置对象
            ProducerConfig config = new ProducerConfig(props);

            //创建生产者
            Producer<String, String> producer = new Producer<String, String>(config);

            KeyedMessage<String, String> msg = new KeyedMessage<String, String>("test3", "100", "hello world tomas100");
            producer.send(msg);
            System.out.println("send over!");
        }

        /**
         * 消费者
         */
        @Test
        public void testConumer(){
            Properties props=new Properties();
            props.put("zookeeper.connect", "s202:2181");
            props.put("group.id", "g3");
            props.put("zookeeper.session.timeout.ms", "500");
            props.put("zookeeper.sync.time.ms","250");
            props.put("auto.commit.interval.ms", "1000");
            props.put("auto.offset.reset", "smallest");

            ConsumerConfig config=new ConsumerConfig(props);

            Map<String,Integer> map=new HashMap<String,Integer>();




        }
    }

