package org.example.order;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class VIPpartioner implements Partitioner {


	@Override
	public int partition(String topic, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
		List<PartitionInfo> partitionInfos = cluster.availablePartitionsForTopic(topic);
		if(((String)o).equals("Pratoon")){
			return 7;
		}
		return Math.abs(Utils.murmur2(bytes))%partitionInfos.size()-1;

	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
