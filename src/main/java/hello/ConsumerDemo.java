package hello;

import org.apache.kafka.clients.ConnectionState;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Constants;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    public static void main(String[] args){

        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);


        //Create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, Constants.consumerGroupId);
        //There can be three values for this earliest/latest/none. Read the ConsumerConfig.java class for more details.
        //This is same as the from beginning option you use in the Kafka consumer cli. So it will start from the last set offset.
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //Create the Consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        //Subscribe the consumer to a topic
        //By doing Collections.singleton we are basically saying that we are subscribing to only one topic
//        kafkaConsumer.subscribe(Collections.singleton("first_topic"));

        kafkaConsumer.subscribe(Arrays.asList("first_topic"));

        //A consumer does not get data unless it asks for Data

        //Poll for new Data
        while(true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
                logger.info("Key {}", consumerRecord.key());
                logger.info("Value {}", consumerRecord.value());
                logger.info("Partition {}", consumerRecord.partition());
            }

        }

    }
}
