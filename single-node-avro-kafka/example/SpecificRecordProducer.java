import avro.SimpleMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;

import java.util.Properties;

public class SpecificRecordProducer {

    public static void main (String args[]) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                org.apache.kafka.common.serialization.StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        props.put("schema.registry.url", "http://localhost:8081");
        KafkaProducer producer = new KafkaProducer(props);

        String key = "key2";
        //create the specific record
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setContent("Specific value1");
        ProducerRecord<Object, Object> record = new ProducerRecord<>("avro-topic", key, simpleMessage);
        try {
            producer.send(record);
        } catch (SerializationException e) {
            // may need to do something with it
        } catch (Exception e){
            System.out.println("ERROR");
        }
// When you're finished producing records, you can flush the producer to ensure it has all been written to Kafka and
// then close the producer to free its resources.
        finally {
            producer.flush();
            producer.close();
        }
    }
}