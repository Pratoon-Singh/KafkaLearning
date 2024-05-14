package org.example.Truck.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class TruckPartioner implements Partitioner {
	@Override
	public int partition(String topic, Object key, byte[] key_bytes, Object value, byte[] valuebytes, Cluster cluster) {
		List<PartitionInfo> partitionInfos = cluster.availablePartitionsForTopic(topic);
		TruckLocation truck = (TruckLocation) value;
		if ("37.2431".equals(truck.getLatitude()) && "115.793".equals(truck.getLongitude())) {
			return 5;
		}
			return (Math.abs(Utils.murmur2(key_bytes)) % partitionInfos.size() - 1);
	}



	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
	// Create a partioner if the latitude and longitude is 37.2431 ,  115.793 it should go to the partioner  5


}
