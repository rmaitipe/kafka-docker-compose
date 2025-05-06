import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaLocalConsumer {

    public static void main(String[] args) {
        // Set up producer properties
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        //properties.put("bootstrap.servers", "kafka:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Create Kafka producer
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        String topic = "my-topic-1";
        consumer.subscribe(Arrays.asList(topic));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(20));
                for (ConsumerRecord<String, String> record : records) {
                    //System.out.println("Consumed record: " + record);
                    System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}