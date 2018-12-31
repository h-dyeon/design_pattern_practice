package DesignPattern_ExceptionCheck;

import java.util.HashMap;
import java.util.Vector;

public class ExcepCheck_file1 implements ExceptionCheck{
	public boolean inputRuleCheck(String[] splitValue){
		if(splitValue.length == 5 || splitValue.length == 6)
			return true;
		else 
			return false;
	}
	
	public boolean nullDataCheck(Vector<String> v){
		for(int i=0; i<6; i++){
			if(v.get(i)==null)
				return true;
			else
				continue;
		}
		return false;
	}
	
	public boolean checkIdConflict(HashMap<String, String> hm, String productId){
		if(hm.containsKey(productId)){
			return true;
		}
		return false;
	}

	public boolean illegalValue(Vector<String> v){
		//3,4,5 : value/ currentStock/ safeStock
		boolean[] value = new boolean[3];
		value[0] = isNumPositive((String)v.get(3));
		value[1] = isNumPositive((String)v.get(4));
		value[2] = isNumPositive((String)v.get(5));
		if(value[0]&value[1]&value[2])
			return false;
		return true;
	}
	
	
	public boolean isNumPositive(String value){
		int num;
		try {
			num = Integer.parseInt(value);
			if(num>=0)
				return true;
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
