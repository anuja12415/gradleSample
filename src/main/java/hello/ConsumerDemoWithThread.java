package hello;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Constants;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerDemoWithThread {
    Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThread.class);
    public static void main(String[] args){
        new ConsumerDemoWithThread().run();
    }

    private void run(){
        logger.info("Creating the Consumer Thread");
        String consumerGroupId = "my-seventh-application";
        CountDownLatch latch = new CountDownLatch(1);

        //create consumer runnable
        Runnable myConsumerRunnable = new ConsumerRunnable(latch, consumerGroupId);

        //Start the Thread
        Thread myThread = new Thread(myConsumerRunnable);
        myThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(
                ()->{
                    logger.info("Caught Shutdown Hook");
                    ((ConsumerRunnable) myConsumerRunnable).shutdown();
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("Application has exited");
                }
        ) );

        try{
            //Causes the current thread to wait until the value of latch is set to 0. Now here we do not want our application to exit right away so we are doing this.
            latch.await();
        }catch (InterruptedException ex){
            logger.error("Application got Interrupted {}", ex);
        }finally {
            logger.info("Application is closing");
        }
    }


    //Thread class implementation starts here :
    public class ConsumerRunnable implements  Runnable{

        private CountDownLatch latch;
        private  KafkaConsumer<String, String> consumer;
        private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);


        public ConsumerRunnable(CountDownLatch countDownLatch,String consumerGroupId) {
            this.latch = countDownLatch;
            //Create consumer configs
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.bootstrapServer);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
            //There can be three values for this earliest/latest/none. Read the ConsumerConfig.java class for more details.
            //This is same as the from beginning option you use in the Kafka consumer cli. So it will start from the last set offset.
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
 //          Assign the Consumer
           consumer= new KafkaConsumer<String, String>(properties);
            //Subscribe the consumer to a topic
            //By doing Collections.singleton we are basically saying that we are subscribing to only one topic
//        kafkaConsumer.subscribe(Collections.singleton("first_topic"));
            consumer.subscribe(Arrays.asList("first_topic"));
        }

        @Override
        public void run(){
            //A consumer does not get data unless it asks for Data
            //Poll for new Data
            try {
                while (true) {
                    ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                        logger.info("Key {}", consumerRecord.key());
                        logger.info("Value {}", consumerRecord.value());
                        logger.info("Partition {}", consumerRecord.partition());
                    }
                }
            }catch (WakeupException ex){
                //This is an expected exception
                logger.info("Received Shutdown Signal");
            }finally {
                consumer.close();
                //Tell the main code we are done with the consumer
                latch.countDown();
            }
        }

        //This will shutdown our consumer Thread
        public void shutdown(){
            //Wakeup is a special method to interrupt consumer.poll. It will through a WakeUp exception
            consumer.wakeup();
        }

    }
}
