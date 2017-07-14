package org.jpmc.sales.reporting;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jpmc.models.Product;
import org.jpmc.models.SalesRecord;
import org.jpmc.sales.util.SalesUtil;
public class SalesReportGenerator {

	private SalesRecord salesRecord;
	private SalesUtil salesUtil = null;
	public static Logger logger = Logger.getLogger(SalesReportGenerator.class);
	public SalesReportGenerator() {
		salesUtil = new SalesUtil();
		salesRecord = SalesRecord.getInstance();
		PropertyConfigurator.configure("resources/log4j.properties");

	}

	public void logSales() {
		if (salesRecord.getProducts() != null) {
			for (int i = (salesUtil.REPORT_COUNTER - salesUtil.REPORT_COUNTER_HOP); i < salesRecord.getProducts()
					.size(); i++) {
				Product existingProduct = salesRecord.getProducts().get(i);
				logger.info("Sold :" + existingProduct);
			}
			salesUtil.REPORT_COUNTER += salesUtil.REPORT_COUNTER_HOP;
		}

	}

	public void logSalesHistory() {
		System.out.println("No more Sales are Accepted, See below for Sale Change log history.");
		if (salesRecord.getProducts() != null) {
			for (Product existingProduct : salesRecord.getProducts()) {
				if (!existingProduct.getTradeLog().isEmpty()) {
					logger.info("Sold :" + existingProduct + " Sale History : " + existingProduct.getTradeLog());
				}

			}

		}
	}

}
