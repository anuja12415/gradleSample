package hello;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.*;
import java.util.Properties;

public class ProducerDemo {
	public static void main(String[] args) {
			System.out.println("Producer Demo class is here");
			String bootstrapServer = "localhost:50002";

			//set Producer properties
			Properties properties = new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

			//Create Producer
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

		//Create a Producer record
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("first_topic", "myKey1", "myvalue1");

		//Send Data -- This is an Asynchrounos method. As this is Async this is going to happen in the background.As this is execute the program ends and this is never executed.
		kafkaProducer.send(record);

		//This will force all the data to be produced. You must write this.
		kafkaProducer.flush();

       //Clsoe the Producer
		kafkaProducer.close();

	  }
}
