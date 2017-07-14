package org.jpmc.sales.util;

import java.util.ArrayList;
import java.util.List;

import org.jpmc.models.Operation;
import org.jpmc.models.Product;
import org.jpmc.models.SalesRecord;

public class SalesUtil {

	private static String ACTION_ADD = "add";
	private static String ACTION_SUBSTRACT = "substract";
	private static String ACTION_MULTIPLY = "multiply";
	public static int REPORT_COUNTER = 10;
	public static int REPORT_COUNTER_HOP = 10;
	
	public static int STOP_COUNTER = 50;
	private static SalesRecord salesRecord;

	public SalesUtil() {
		salesRecord = SalesRecord.getInstance();
	}

	public void addSalesRecord(List<Product> products) {
		if (salesRecord.getProducts() != null) {
			salesRecord.getProducts().addAll(products);
		} else {
			List<Product> productList = new ArrayList<Product>();
			productList.addAll(products);
			salesRecord.setProducts(productList);
		}

	}

	public void updateSalesRecord(Operation operation, long messageId) {
		String productType = operation.getProductType();
		String actionType = operation.getActionType();
		long priceChange = operation.getChangePrice();
		applyOperation(productType, actionType, priceChange, messageId);
	}

	public void applyOperation(String productType, String action, long priceChange, long messageId) {
		
		if(salesRecord.getProducts() == null){
			return ;
		}for (Product existingProduct : salesRecord.getProducts()) {
			if (productType.equals(existingProduct.getProductType())) {
				if (ACTION_ADD.equals(action)) {
					existingProduct.setProductPrice(existingProduct.getProductPrice() + priceChange);
				} else if (ACTION_SUBSTRACT.equals(action)) {
					existingProduct.setProductPrice(existingProduct.getProductPrice() - priceChange);
				} else {
					existingProduct.setProductPrice(existingProduct.getProductPrice() * priceChange);
				}
				if (!existingProduct.getTradeLog().isEmpty()) {

					existingProduct.setTradeLog("Action Performed : " + existingProduct.getTradeLog() + " | " + action + " | " + priceChange
							+ " By message Id : " + messageId);
				} else {
					existingProduct.setTradeLog("Action Performed : " + action + " | " + priceChange + " By message Id : " + messageId);
				}

			}
		}
		
	}

	public boolean checkThreshold() {
		
		if(salesRecord.getProducts() == null){
			return true;
		}
		if(salesRecord.getProducts().size() < STOP_COUNTER){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isReportToBeGenerated() {
		if (salesRecord.getProducts() != null && salesRecord.getProducts().size() < REPORT_COUNTER) {
			return false;
		} else {
			return true;

		}
	}

}
