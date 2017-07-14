package org.jpmc.sales.message.producer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

import org.jpmc.models.Message;
import org.jpmc.models.Operation;
import org.jpmc.models.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SalesMessageProducer implements Runnable {

	protected BlockingQueue<Message> blockingQueue = null;

	public SalesMessageProducer(BlockingQueue<Message> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void run() {
		Message inputMessage = null;
		Product product = null;
		ArrayList<Product> productList = null;
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			while (true) {
				File file = new File("resources/incomingMessages.txt");

				FileReader fileContent = new FileReader(file);

				if (fileContent.read() > 0) {

					obj = parser.parse(new FileReader(file));

					PrintWriter writer = new PrintWriter(file);
					writer.print("");
					writer.close();
					JSONObject jsonObject = (JSONObject) obj;
					JSONArray msgsArr = (JSONArray) jsonObject.get("incomingMessages");

					for (int i = 0; i < msgsArr.size(); i++) {
						JSONObject messageObj = (JSONObject) msgsArr.get(i);
						inputMessage = new Message();
						inputMessage.setMessageId((Long) messageObj.get("messageId"));
						JSONArray prodArray = (JSONArray) messageObj.get("products");
						Iterator<JSONObject> prodIterator = prodArray.iterator();
						productList = new ArrayList<Product>();
						while (prodIterator.hasNext()) {
							JSONObject prodObj = prodIterator.next();
							product = new Product((String) prodObj.get("productType"),
									(Long) prodObj.get("productPrice"));
							productList.add(product);

						}
						Operation operation = null;
						if (messageObj.get("operation") != null) {
							JSONObject operationObj = (JSONObject) messageObj.get("operation");
							operation = new Operation((String) operationObj.get("productType"),
									(String) operationObj.get("actionType"), (Long) operationObj.get("changePrice"));
							inputMessage.setOperation(operation);
						}
						inputMessage.setProducts(productList);

						try {
							blockingQueue.put(inputMessage);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}

				}

			}
		} catch (FileNotFoundException e) {
			System.out.println("Input file now found : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the input : " + e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Error occured while parsing the data : " + e.getMessage());
			e.printStackTrace();
		}

	}

}
