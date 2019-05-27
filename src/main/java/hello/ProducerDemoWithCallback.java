package hello;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Constants;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoWithCallback {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Producer Demo class is here");

        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallback.class);
        //set Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.bootstrapServer);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //Create Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        for(int i =0; i< 10; i++ ) {
            //Create a Producer record
            String key = "myKey " + Integer.toString(i);
            String value = "myValue " + Integer.toString(i);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", key, value);

            //Send Data -- This is an Asynchrounos method. As this is Async this is going to happen in the background.As this is execute the program ends and this is never executed.
            kafkaProducer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    //onCompletion gets called if a record is succesfully sent or otherwise

                    if (exception == null) {
                        //Will get executed for successfull execution of Producer
                        logger.info("Metadata Here\n" + metadata.topic() + "\n" + metadata.partition());
                    } else {
                        //Executed in case of Exception
                    }
                }
            });//.get is to make the send method synchrounous. Never do this in Production. It will give you a very sloopy performance.
        }

        //This will force all the data to be produced. You must write this.
        kafkaProducer.flush();

        //Clsoe the Producer
        kafkaProducer.close();

    }

}
