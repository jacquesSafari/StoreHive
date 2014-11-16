package main.java.com.storehive.application.utilities;

import java.util.Calendar;
import java.util.Date;



public class Test {
	
	public static void main(String[] args) throws Exception {
		Date t = new Date();
		String today = t.getYear()+"-"+t.getMonth()+"-"+t.getDay();
		System.out.println(today);
	}
}
