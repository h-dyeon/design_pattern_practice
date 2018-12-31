package DesignPattern_ExceptionCheck;

import java.util.HashMap;
import java.util.Vector;

public class ExcepCheck_file2 implements ExceptionCheck{

	@Override
	public boolean inputRuleCheck(String[] splitValue) {
		if(splitValue.length ==5  || splitValue.length == 4)
			return true;
		else 
			return false;
	}

	@Override
	public boolean nullDataCheck(Vector<String> v) {
		for(int i=0; i<4; i++){
			if(v.get(i)==null)
				return true;
			else
				continue;
		}
		return false;
	}

	@Override
	public boolean checkIdConflict(HashMap<String, String> hm, String productId) {
		if(hm.containsKey(productId)){
			return true;
		}
		return false;
	}

	@Override
	public boolean illegalValue(Vector<String> v) {
		//이름:학번:성별:졸업여부:비고
		if(!isNumPositive((String)v.get(1))) {
			return true;
		}
		if(!(v.get(2).equals("여"))  && !(v.get(2).equals("남"))){
			return true;
		}
		if(!(v.get(3).equals("true"))  && !(v.get(3).equals("false"))){
			return true;
		}
		return false;
	}

	@Override
	public boolean isNumPositive(String value) {
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
