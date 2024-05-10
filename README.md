# Notes on Kafka

## Introduction

Kafka is a distributed streaming platform used for building real-time data pipelines and streaming applications. It offers high scalability, fault tolerance, and performance, making it suitable for various use cases in industries such as finance, healthcare, retail, and more.

### Definition

- Kafka serves as a distributed commit log, managing events or messages produced by microservice applications.
- Events are stored in logs known as topics, allowing for real-time data processing and analytics.

## Advantages of Kafka

1. **Support for Multiple Producers and Consumers**
   - Multiple producers can write to a single topic concurrently, and multiple consumers can subscribe to and consume messages from the same topic.
   - Kafka retains messages, enabling multiple consumers to process the same message independently.

2. **Scalability**
   - Kafka brokers can be easily scaled to handle increasing loads.
   - Environments can range from single-broker setups for development to large clusters with hundreds or thousands of brokers for production.

3. **Fault Tolerance and Persistence**
   - Messages are persisted on disk, ensuring durability even if consumers are temporarily down.
   - Replication across multiple brokers provides high availability.

4. **Performance**
   - Kafka's design, including support for multiple producers and consumers, partitions, and scalability, leads to enhanced performance for real-time data processing.

## Use Cases

1. **Messaging**
   - Exchange messages across microservice applications.
   - Implement producer-consumer patterns for task distribution.

2. **Activity Tracking**
   - Track user activity for analytics and personalized recommendations.

3. **Metrics and Log Aggregation**
   - Aggregate and analyze application metrics and logs in real time.
   - Detect anomalies or security threats promptly.

4. **Commit Log**
   - Stream database changes for replication or analysis.

5. **Stream Processing**
   - Create data pipelines with transformation and computation stages using Kafka Streams.

## Applications of Kafka

- **Mobile Apps**: Twitter utilizes Kafka for performance management and analytics, handling billions of sessions daily.
- **Transportation**: Uber processes trillions of events daily for log aggregation and real-time tracking.
- **Entertainment**: Netflix uses Kafka as its messaging backbone, managing hundreds of billions of events.
- **E-commerce**: Pinterest leverages Kafka for real-time advertising, handling billions of events daily.

## Kafka Architecture Components

1. **Kafka Broker**
   - Java process responsible for message exchange, persistence, and durability.
   - Can be scaled to form a Kafka cluster.

2. **Zookeeper**
   - Manages cluster coordination, leader election, and metadata storage.
   - Will be replaced by a Kafka-native solution in the future.

3. **Producer**
   - Application that produces data/messages to Kafka topics.
   - Communicates with brokers via TCP protocol.

4. **Consumer**
   - Application that consumes data/messages from Kafka topics.
   - Can work as part of a consumer group for load balancing.

## Records in Kafka

- A record consists of attributes such as topic, partition, offset, timestamp, key, headers, and value.
- Partitions ensure scalability and concurrency in Kafka.
- Replication ensures high availability and fault tolerance.

## Kafka Commands (CLI)

- `kafka-topics --list`: List all topics.
- `kafka-topics --create`: Create a new topic.
- `kafka-topics --describe`: Describe details of a topic.
- `kafka-console-consumer`: Consume messages from a topic.
- `kafka-console-producer`: Produce messages to a topic.
- `kafka-topics --delete`: Delete a topic (enable `delete.topic.enable` in server.properties).

```markdown
# Setting up Kafka on Windows

## Step 1: Starting Zookeeper
Open CMD and start Zookeeper using the following command:
```
zookeeper-server-start.bat config\zookeeper.properties
```

## Step 2: Starting Kafka
Open CMD and start Kafka using the following command:
```
kafka-server-start.bat config\server.properties
```
```
## Kafka Core APIs

Kafka provides five core APIs:
1. Admin API: Manages and inspects topics, brokers, and other objects in the Kafka cluster.
2. Producer API: Publishes event records or messages to Kafka topics.
3. Consumer API: Subscribes and reads messages from Kafka brokers.
4. Streaming API: Implements stream processing applications to process event streams and apply transformations.
5. Kafka Connect API: Builds and runs reusable data import and export connectors.

GUI tools like Kafka Drop utilize the Admin API for browser-based access to manage Kafka clusters.

## Kafka Producer Workflow
1. **Producer Record Creation**: Set attributes like topic, value (payload), partition, timestamp, key, and headers.
2. **Serialization**: Convert key and value into byte arrays using serializers.
3. **Partitioning**: Assign the record to a partition based on a partition number or key hash.
4. **Batching**: Add records to a batch for a specific topic and partition.
5. **Asynchronous Sending**: Send batches to Kafka brokers; handle retries and failures.
6. **Handling Responses**: Retrieve record metadata for successful writes.

## Kafka Consumer API
1. **Consumer Initialization**: Create Kafka consumer with bootstrap servers, key and value deserializers, and group ID.
2. **Subscription**: Subscribe to Kafka topics.
3. **Polling**: Continuously poll topics for messages.
4. **Message Processing**: Handle received records.
5. **Closing**: Close the consumer connection.

## Apache Avro Integration
Apache Avro simplifies serialization and deserialization of object types in Kafka:
- Define language-neutral schema files representing data objects.
- Use Avro serializers and deserializers to convert objects into byte arrays and vice versa.
- Schema registry stores and manages schema versions; Avro serializers push schemas to the registry.

## Partitioning in Kafka
- Partitioners in Kafka assign records to partitions based on partition number or key hash.
- Records without a partition number are assigned partitions in a round-robin fashion.
- Kafka brokers handle message writes to appropriate partitions within topics.

---

### Note:
- Kafka producers create partitions.
- A synchronous send method call returns a Future containing record metadata.
- The serialize method in a Serializer class returns a byte array.
- Inbuilt Kafka API classes can be used for serialization and deserialization.

## Kafka Producer Configuration Notes

## Custom Partitioning

- **Manual Topic Creation**: Use Kafka topics command to create a topic with multiple partitions.

``kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 10 --topic order-partitioned-topic``


- **Custom Partitioner Class**: Create a custom partitioner class named `VIPPartitioner` in the `CustomSerializers.Partitioners` package.

- **Partitioning Logic**:
- Default: Utilizes Murmur2 algorithm for partitioning.
- Custom: Allows defining custom partitioning logic based on record attributes.

## Producer Configuration Properties

### Mandatory Properties

- `bootstrap.servers`: Kafka bootstrap servers.
- `key.serializer`: Serializer for message keys.
- `value.serializer`: Serializer for message values.

### Additional Properties

- `acks`: Acknowledgement configuration for message acknowledgment.
- Values: `0` (no acknowledgment), `1` (leader acknowledgment), `all` (all replicas acknowledgment).

- `buffer.memory`: Buffer memory for messages before sending to the broker.
- Default: 256 MB.

- `compression.type`: Compression type for messages.
- Options: `snappy`, `gzip`, `lz4`.

- `retries`: Number of retries for recovering from recoverable errors.
- Default: `0` (no retries).

- `retry.backoff.ms`: Backoff time between retries in milliseconds.

- `linger.ms`: Time to wait before sending a batch of messages to the broker.

- `request.timeout.ms`: Timeout for waiting for a response from the broker.

## Additional Notes

- **Batch Size**: Configure a reasonable batch size to optimize throughput.
- **Batch Linger Time**: Control the linger time to accumulate more messages before sending a batch.
- **Producer Config Class**: Use `ProducerConfig` class constants for cleaner configuration.

## Further Resources

- [Kafka Producer Configuration](https://docs.confluent.io/platform/current/installation/configuration/producer-configs.html): Explore more properties and their detailed descriptions.

## Message Delivery and Transactions

### Achieving Idempotency
In Kafka, achieving message delivery idempotency, meaning no duplicate messages, is crucial. The Kafka producer API, along with the Broker, supports three different message delivery semantics:

1. **At Least Once Delivery**: The default behavior where the message is delivered at least once. However, this can result in message duplication if acknowledgements fail.
2. **At Most Once Delivery**: Ensures the message is delivered at most once, meaning no message duplication. However, there's a risk of message loss if acknowledgements fail.
3. **Exactly Once Delivery**: This is the most desired behavior, ensuring idempotency. It guarantees no duplication of messages. Achieving this requires setting the `enable.idempotence` property to `true`.

### Transactions
Transactions in Kafka producers mimic database transactions, ensuring either all records are committed or none are. The process involves four steps:
1. **Assign a Unique Transactional ID**: Set the `transactional.id` property with a unique value.
2. **Initialize Transactions**: Invoke `producer.initTransactions()` to prepare for transactional operations.
3. **Begin Transaction**: Start a transaction using `producer.beginTransaction()`.
4. **Commit or Abort Transaction**: Either commit the transaction with `producer.commitTransaction()` or abort it with `producer.abortTransaction()`. Abort is invoked upon encountering exceptions.

### Important Points about Transactions:
- A single producer instance cannot have multiple transactions open simultaneously.
- `commitTransaction()` flushes unsent records before committing and throws exceptions if errors occur during commit.
- The `abortTransaction()` method resets the transaction upon failure, preventing further message sends.

## Consumer Groups

### Overview
Consumer groups are crucial for scaling consumer applications in Kafka. They enable multiple consumers to read from the same topic, distributing partitions among themselves to handle varying loads.

### Scaling with Consumer Groups
- **Single Consumer**: Responsible for consuming from all partitions in the topic.
- **Multiple Consumers**: Split partitions among themselves, each handling one or more partitions.
- **Scaling Up**: Additional consumers can be added to handle increased load, ensuring efficient load distribution.
- **Partition-Consumer Mapping**: Each consumer in the group is responsible for consuming from one or more partitions, ensuring efficient resource utilization.

Consumer groups facilitate load balancing and allow multiple applications to consume from the same topic simultaneously while maintaining their own copies of data.

# Consumer Group Rebalancing

## Introduction
In this lecture, we define what consumer group rebalancing is and its significance for Kafka's high availability and scalability.

### Definition
- Consumer group rebalancing occurs when:
  - A new consumer joins a consumer group.
  - A consumer leaves the group (gracefully or due to a crash).
- During rebalancing:
  - Partitions are reassigned among available consumers.
  - The process is managed by the group leader, not the coordinator.
  
## How Rebalancing Works
- Kafka allocates a consumer group coordinator.
- New consumers join by sending a request to the coordinator.
- Coordinator sends partition details to the first joining consumer, making it the group leader.
- Additional consumers trigger a rebalance, where the leader reassigns partitions.
- Coordinator distributes partition assignments to consumers.

## Consumer Health Monitoring
- Consumers send heartbeats to indicate their health.
- If no heartbeat is received within a configured time, the consumer is considered dead.
- Dead consumers trigger a rebalance.

## Impact of Rebalancing
- During rebalance, consumers are silent.
- Rebalancing affects consumer group performance.

## Managing Offsets
- Consumers commit processed offsets to a special Kafka topic: `__consumer_offsets`.
- Offsets help manage duplicate processing and ensure fault tolerance.

# Auto Commit

## Overview
Auto commit periodically commits offsets based on the poll method's invocation.
- Default interval: 5 seconds.

### Auto Commit Interval
- The poll method drives auto commit.
- Offsets are committed if the interval has elapsed since the last commit.

## Sync Commit
Disables auto commit and manually commits offsets using the `commitSync` method.
- Ensures control over when offsets are committed.
- Addresses rebalancing issues related to duplicate processing.

## Async Commit
- Enables non-blocking offset commits using the `commitAsync` method.
- Suitable for performance-sensitive applications.
- Doesn't retry failed commits due to potential order inconsistency issues.

# Commit Custom Offset

## Overview
- Commits specific offsets to avoid processing duplication during rebalancing.
- Provides more frequent offset commits.

## Implementation
- Create a rebalance listener to commit offsets before revocation.
- Utilize `commitSync` or `commitAsync` with specific offset information.

# Consumer Configuration

## Introduction
Learn about configuring consumer properties for optimization and performance.

## Consumer Config Class
- Utilize the `ConsumerConfig` class for managing consumer properties.
- Configure essential properties like bootstrap servers, deserializers, and group ID.

## Conclusion
Understanding consumer configuration is crucial for optimizing Kafka consumers for various use cases.
# Kafka Consumer Configuration Properties

## 1. Fetch Min Bytes
- Property: `consumer.config.fetch.min.bytes`
- Controls when the Kafka broker sends data to the consumer.
- Higher values reduce back-and-forth data exchanges.
- Default: 1 MB.

## 2. Fetch Max Wait Time
- Property: `consumer.config.fetch.max.wait.ms`
- Sets the maximum wait time for data fetching.
- Default: 500 milliseconds.

## 3. Heartbeat Interval
- Property: `consumer.config.heartbeat.interval.ms`
- Frequency of heartbeat sent by consumer to group coordinator.
- Default: 1000 milliseconds.

## 4. Session Timeout
- Property: `consumer.config.session.timeout.ms`
- Maximum duration consumer can go without sending heartbeat.
- Default: 3000 milliseconds.

## 5. Max Partition Fetch Bytes
- Property: `consumer.config.max.partition.fetch.bytes`
- Controls max bytes returned per partition to consumer.
- Default: 1 MB.

## 6. Auto Offset Reset
- Property: `consumer.config.auto.offset.reset`
- Defines consumer behavior if starting from uncommitted offset.
- Values: "latest" or "earliest".

## 7. Client ID
- Property: `consumer.config.client.id`
- Unique string for logging metrics and quota allocation.

## 8. Max Poll Records
- Property: `consumer.config.max.poll.records`
- Controls max records returned by each poll call.

## 9. Partition Assignment Strategy
- Property: `consumer.config.partition.assignment.strategy`
- Allows configuring partition assigner strategy.

# Kafka Standalone Consumer

## Creating a Standalone Consumer
- Use `assign` method instead of `subscribe` to manually assign partitions.
- Retrieve partitions using `partitionsFor` method.
- Hardcode partition assignment or assign all partitions of a topic.

## Testing Standalone Consumer
- Run a producer to produce messages.
- Set `auto.offset.reset` to "earliest" for proper message consumption.

## Important Points
- Need to provide a valid `group.id` even for standalone consumers.
- Exception occurs if attempting to commit offsets without a valid group ID.

# Real-Time Stream Processing with Kafka

## Near Real-Time vs. Real-Time Stream Processing
- Near real-time: Data stored, then pulled for analysis and reporting.
- Real-time: Continuous flow of data, processed instantly for immediate insights.

## Use Cases
- Microservices, IoT, finance, healthcare, retail, etc.
- Kafka Streams library facilitates real-time stream processing.
- Processor API and Streams DSL provide computational logic for data processing.

## Demo Commands
- Commands for creating Kafka topics, producers, and consumers.
- Word Count Demo setup commands provided.

### Kafka Topic Creation Commands
```sh
kafka-topics --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic streams-dataflow-input

kafka-topics --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic streams-dataflow-output

kafka-topics --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic streams-wordcount-input

kafka-topics --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 1 \
--topic streams-wordcount-output
```

### Kafka Console Producer Command
```sh
kafka-console-producer --bootstrap-server localhost:9092 --topic streams-dataflow-input
```

### Kafka Console Consumer Command
```sh
kafka-console-consumer --bootstrap-server localhost:9092 \
--topic streams-dataflow-output \
--property print.key=true \
--property print.value=true \
--property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
--property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

kafka-console-consumer --bootstrap-server localhost:9092 \
--topic streams-wordcount-output \
--from-beginning \
--property print.key=true \
--property print.value=true \
--property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
--property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

These commands can be used in a terminal to interact with Kafka and perform various operations like creating topics, producing messages, and consuming messages.
