package com.camel;

public class OrderService {

	  public String processOrder(Order order) {
	        return "Processed order id " + order.getId() + " item " + order.getName() + " of " + order.getAge() + " copies of " + order.getAddress();
	    }
	}

