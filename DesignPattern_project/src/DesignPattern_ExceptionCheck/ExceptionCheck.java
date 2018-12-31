package DesignPattern_ExceptionCheck;

import java.util.HashMap;
import java.util.Vector;

public interface ExceptionCheck {

	//이런것들 추가하면 됨

	public boolean inputRuleCheck(String[] splitValue);
	public boolean nullDataCheck(Vector<String> v);	
	boolean checkIdConflict(HashMap<String, String> hm, String productId);
	boolean illegalValue(Vector<String> v);	
	boolean isNumPositive(String value);

}
