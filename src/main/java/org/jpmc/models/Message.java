package org.jpmc.models;

import java.util.ArrayList;

public class Message {

	private long messageId;
	private ArrayList<Product> products;

	/*
	 * Possible Values of Operation can be : Add, Substract or Multiply
	 */
	private Operation operation;

	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * @param products2
	 *            the products to set
	 */
	public void setProducts(ArrayList<Product> products2) {
		this.products = products2;
	}

	/**
	 * @return the operation
	 */
	public Operation getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

}
