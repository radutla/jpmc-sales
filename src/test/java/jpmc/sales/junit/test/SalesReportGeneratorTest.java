package jpmc.sales.junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.jpmc.models.Message;
import org.jpmc.models.Operation;
import org.jpmc.models.Product;
import org.jpmc.models.SalesRecord;
import org.jpmc.sales.util.SalesUtil;
import org.junit.BeforeClass;
import org.junit.Test;

public class SalesReportGeneratorTest {

	private static SalesUtil salesUtil;
	private static SalesRecord salesRecord;
	
	@BeforeClass
	public static void dataSetup(){
		salesUtil = new SalesUtil();
		salesRecord = SalesRecord.getInstance();
	}
	
	
	@Test
	public void testaddSalesRecord() {
		ArrayList<Message> msgList = dataSetupMessageWithOperation();
		for (Message incomingMsg : msgList) {
			if (incomingMsg.getProducts() != null) {
				salesUtil.addSalesRecord(incomingMsg.getProducts());
				assertNotNull(salesRecord.getProducts());
			}
			else if (incomingMsg.getOperation() != null) {
				Operation operation = incomingMsg.getOperation();
				salesUtil.applyOperation(operation.getProductType(), operation.getActionType(),
						operation.getChangePrice(), incomingMsg.getMessageId());
				assertNotNull(salesRecord.getProducts());

			}


		}
	}

	@Test
	public void testUpdateSalesRecord(){
		ArrayList<Message> msgList = dataSetupMessageWithOperation();
		for (Message incomingMsg : msgList) {
			if (incomingMsg.getProducts() != null) {
				salesUtil.addSalesRecord(incomingMsg.getProducts());
			}
			if (incomingMsg.getOperation() != null) {
				Operation operation = incomingMsg.getOperation();
				salesUtil.applyOperation(operation.getProductType(), operation.getActionType(),	operation.getChangePrice(), incomingMsg.getMessageId());
				Product productChanged = salesRecord.getProductByType("apple");
				assertEquals(40, productChanged.getProductPrice());

			}

		}
	}
	private ArrayList<Message> dataSetupMessageWithOperation() {
		ArrayList<Message> msgList = new ArrayList<Message>();
		Message message1 = new Message();
		message1.setMessageId(1);
		ArrayList<Product> productList1 = new ArrayList<Product>();
		productList1.add(new Product("apple", 10));
		message1.setProducts(productList1);

		msgList.add(message1);
		Message message2 = new Message();
		message2.setMessageId(2);
		ArrayList<Product> productList2 = new ArrayList<Product>();
		productList2.add(new Product("orange", 20));
		productList2.add(new Product("orange", 20));
		productList2.add(new Product("orange", 20));
		message2.setProducts(productList2);
		msgList.add(message2);

		Message message3 = new Message();
		message3.setMessageId(3);
		message3.setOperation(new Operation("apple", "add", 30));
		ArrayList<Product> productList3 = new ArrayList<Product>();
		productList3.add(new Product("orange", 20));
		productList3.add(new Product("apple", 10));
		productList3.add(new Product("orange", 20));
		message3.setProducts(productList3);
		msgList.add(message3);

		Message message4 = new Message();
		message4.setMessageId(2);
		ArrayList<Product> productList4 = new ArrayList<Product>();
		productList4.add(new Product("orange", 20));
		productList4.add(new Product("orange", 20));
		productList4.add(new Product("orange", 20));
		message4.setProducts(productList4);
		msgList.add(message4);

		return msgList;
	}
	private ArrayList<Message> dataWithJustOperation() {
		
		ArrayList<Message> msgList = new ArrayList<Message>();
		Message message1 = new Message();
		message1.setMessageId(3);
		ArrayList<Product> productList3 = new ArrayList<Product>();
		productList3.add(new Product("apple", 10));
		productList3.add(new Product("apple", 10));
		productList3.add(new Product("apple", 10));
		message1.setProducts(productList3);
		msgList.add(message1);

		Message message2 = new Message();
		message2.setMessageId(3);
		message2.setOperation(new Operation("apple", "add", 30));
		msgList.add(message2);

		return msgList;
	}

}
