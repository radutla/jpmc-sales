package org.jpmc.sales.message.consumer;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import org.jpmc.models.Message;
import org.jpmc.models.Product;
import org.jpmc.sales.reporting.SalesReportGenerator;
import org.jpmc.sales.util.SalesUtil;

public class SalesMessageConsumer implements Runnable {
	public SalesUtil salesUtil;
	protected BlockingQueue<Message> blockingQueue = null;

	public SalesMessageConsumer(BlockingQueue<Message> blockingQueue) {
		this.blockingQueue = blockingQueue;
		salesUtil = new SalesUtil();
	}

	public void run() {
		SalesReportGenerator salesReportGenerator = new SalesReportGenerator();
		try {
			Message incomingMsg = null;
			while ((incomingMsg = blockingQueue.take()) != null && salesUtil.checkThreshold()) {
				ArrayList<Product> prodList = incomingMsg.getProducts();
				if (incomingMsg.getOperation() != null) {
					salesUtil.updateSalesRecord(incomingMsg.getOperation(), incomingMsg.getMessageId());
				}
				if (prodList != null && !prodList.isEmpty()) {
					salesUtil.addSalesRecord(prodList);
				}
				if (salesUtil.isReportToBeGenerated()) {
					salesReportGenerator.logSales();
				}
			}
			if (!salesUtil.checkThreshold()) {
				salesReportGenerator.logSalesHistory();
			} else {
				blockingQueue.wait();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
