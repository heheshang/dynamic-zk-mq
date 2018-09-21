package bhz.mq;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
/**
 * 自定义Consumer类
 * @author alienware
 *
 */
public class MQConsumer extends DefaultMQPushConsumer {

	private String consumerId;
	
	public MQConsumer(String consumerId, String groupName, String namesrvAddr){
		super(groupName);
		this.setNamesrvAddr(namesrvAddr);
		this.consumerId = consumerId;
	}

	public void registerMessageListener(List<MQListener> mqListeners){
		for (MQListener mqListener : mqListeners) {
			this.registerMessageListener(mqListeners);
		}
	}
	
	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	
	
	
	
}
