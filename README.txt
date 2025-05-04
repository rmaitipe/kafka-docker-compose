# Kafka in Docker
Examples of running Kafka and elements of Confluent Platform in docker using Docker Compose. 

This repository is inspired by
https://github.com/confluentinc/cp-docker-images
https://codingharbour.com/apache-kafka/guide-to-apache-avro-and-kafka/


docker-compose up //docker-compose down   docker-compose up  -d
1. single-node-kafka is a simple cluster for testing Local Producer and Local Consumer.
2. single-node-avro-kafka comes with a schema registry for testing avro Producers and Consumers.
   run mvn build to generate class from Avro Schema