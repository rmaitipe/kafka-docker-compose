import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaLocalProducer {

    public static void main(String[] args) {
        // Set up producer properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        //properties.put("bootstrap.servers", "kafka:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create Kafka producer
        Producer<String, String> producer = new KafkaProducer<>(properties);

        // Publish messages to a Kafka topic
        //String topicName = "your_topic_name";
        String topic = "my-topic-1";
        for (int i = 0; i < 10; i++) {
            String message = "Message " + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            producer.send(record);
        }
        // Close the producer
        producer.close();
    }
}