package com.devx.kafka;

import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerClient {

    private String topic;
    private String bootstrapServers;
    private String consumerName;

    KafkaConsumerClient(String consumerName, String topic, String bootstrapServers){
        this.topic = topic;
        this.bootstrapServers = bootstrapServers;
        this.consumerName = consumerName;

    }

    public static void main(String ... args) throws Exception{
        String topic = System.getenv("DEVX_KAFKA_TOPIC");
        System.out.println("TOPIC -> "+topic);
        String bootstrapServers = System.getenv("DEVX_KAFKA_SERVICE_HOST")+":"+System.getenv("DEVX_KAFKA_SERVICE_PORT");
        System.out.println("Bootstrap -> "+bootstrapServers);
        String consumerName = System.getenv("DEVX_KAFKA_CONSUMER_NAME");
        System.out.println("Name -> "+consumerName);
        KafkaConsumerClient kafkaClient = new KafkaConsumerClient(consumerName, topic, bootstrapServers);
        try {
            kafkaClient.startConsumer();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(100);
        } catch (Exception e){
	   e.printStackTrace();
           Thread.sleep(300000);

	}
    }

    private Consumer<Long, String> createConsumer() {
	System.out.println("Creating Consumer....");
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                consumerName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(topic));
	System.out.println("Created Consumer");
        return consumer;
    }

    public void startConsumer() throws InterruptedException {
	System.out.println("Starting Consumer....");
        final Consumer<Long, String> consumer = createConsumer();

        final int giveUp = 100000;   int noRecordsCount = 0;

        while (true) {
	System.out.println("Polling Consumer....");
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(10000);
	System.out.println("Polling ...."+consumerRecords.count());
            if (consumerRecords.count()==0) {
                noRecordsCount++;
		System.out.println("No record found "+noRecordsCount);
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }
}
