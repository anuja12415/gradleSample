package hello;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Constants;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerSeekAndAssign {
    public static void main(String[] args){

        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);


        //Create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.bootstrapServer);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //There can be three values for this earliest/latest/none. Read the ConsumerConfig.java class for more details.
        //This is same as the from beginning option you use in the Kafka consumer cli. So it will start from the last set offset.
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //Create the Consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        //Assign and seek are mostly used to replay data or fetch a specific data. We do not need GroupId for this consumer API.


        //assign
        Long offsetToReadFrom = 2L;
        TopicPartition partitionsToReadFrom = new TopicPartition("first_topic", 0);
        kafkaConsumer.assign(Arrays.asList(partitionsToReadFrom));
        kafkaConsumer.seek(partitionsToReadFrom, offsetToReadFrom);

        //A consumer does not get data unless it asks for Data

        int numberOfMessagesToRead = 5;
        boolean keepReading = true;
        int numberOfMessagesRead = 0;


        //Poll for new Data
        while(keepReading){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
                numberOfMessagesRead+=1;
                logger.info("MyKey {}", consumerRecord.key());
                logger.info("Value {}", consumerRecord.value());
                logger.info("Partition {}", consumerRecord.partition());
                System.out.println("Printing number of Messages Read " + numberOfMessagesRead);
                if(numberOfMessagesRead >= numberOfMessagesToRead){
                    System.out.println("Coming to the True Part");
                    keepReading = false;
                    break;
                }
            }
        }
        System.out.println("Hello Anuja");

    }
}

