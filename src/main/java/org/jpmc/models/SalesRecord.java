package org.jpmc.models;

import java.util.List;

public class SalesRecord {

	private static SalesRecord salesRecordInstance = null;

	protected SalesRecord() {
		// Exists only to defeat instantiation.
	}

	public static SalesRecord getInstance() {
		if (salesRecordInstance == null) {
			salesRecordInstance = new SalesRecord();
		}
		return salesRecordInstance;
	}

	public List<Product> products;

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Product getProductByType(String productType){
		Product product = null;
		if(getProducts() !=null){
			for(Product productExting : getProducts()){
				if(productType.equals(productExting.getProductType())){
					product=productExting;
					break;
				}
			}
		}
		return product;
	}

}
