package main.java.com.storehive.application.utilities;

import main.java.com.storehive.application.domain.Category;
import main.java.com.storehive.application.services.crud.CategoryCrudService;
import main.java.com.storehive.application.services.crud.impl.CategoryCrudServiceImpl;

public class Test {

	public static void main(String[] args) throws Exception {
//		Client client = ClientBuilder.newBuilder().register(SseFeature.class).build();
//		
//		WebTarget target = client.target("http://41.185.26.135/api/business/push");
//		 
//		EventInput eventInput = target.request().get(EventInput.class);
//		while (!eventInput.isClosed()) {
//		    final InboundEvent inboundEvent = eventInput.read();
//		    if (inboundEvent == null) {
//		        break;
//		    }
//		    System.out.println(inboundEvent.getName() + "; "+ inboundEvent.readData(String.class));
//		}
		
		CategoryCrudService cs = new CategoryCrudServiceImpl();
		Category a = new Category();
		a.setCategoryName("Food");
		a.setCategoryDescription("All your your food needs");
		
		ResponseResult output = cs.createEntity(a);
	}

}
