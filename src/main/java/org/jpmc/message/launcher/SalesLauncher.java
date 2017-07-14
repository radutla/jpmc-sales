package org.jpmc.message.launcher;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.jpmc.models.Message;
import org.jpmc.sales.message.consumer.SalesMessageConsumer;
import org.jpmc.sales.message.producer.SalesMessageProducer;

public class SalesLauncher {
	public static List<Message> incomingMessages = null;

	public static void main(String[] args) {

		BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
		SalesMessageProducer producer = new SalesMessageProducer(queue);
		SalesMessageConsumer consumer = new SalesMessageConsumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

	}

}
