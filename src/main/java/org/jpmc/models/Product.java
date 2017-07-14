package org.jpmc.models;

public class Product {

	public Product(String prodType, long price) {
		this.productType = prodType;
		this.productPrice = price;
	}

	private String productType;
	private long productPrice;
	private String tradeLog = "";

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productPrice
	 */
	public long getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the tradeLog
	 */
	public String getTradeLog() {
		return tradeLog;
	}

	/**
	 * @param tradeLog
	 *            the tradeLog to set
	 */
	public void setTradeLog(String tradeLog) {
		this.tradeLog = tradeLog;
	}

	public String toString() {
		return "Product Type : " + this.productType + " Product Price " + this.productPrice;
	}

}
