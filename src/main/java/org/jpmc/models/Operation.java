package org.jpmc.models;

public class Operation {
	public Operation(String productType, String action, long changePrice) {
		this.productType = productType;
		this.actionType = action;
		this.changePrice = changePrice;
	}

	public String productType;
	public String actionType;
	public long changePrice;

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
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the changePrice
	 */
	public long getChangePrice() {
		return changePrice;
	}

	/**
	 * @param changePrice
	 *            the changePrice to set
	 */
	public void setChangePrice(long changePrice) {
		this.changePrice = changePrice;
	}

}
