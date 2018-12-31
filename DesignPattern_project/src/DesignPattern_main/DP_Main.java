package DesignPattern_main;

import DesignPattern_controller.EventHandler;
import DesignPattern_model.ProductRecord;

public class DP_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProductRecord model=new ProductRecord();
		EventHandler controller =new EventHandler(model);
	}

}
