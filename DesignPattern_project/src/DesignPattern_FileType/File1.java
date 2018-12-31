package DesignPattern_FileType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import DesignPattern_ExceptionCheck.ExcepCheck_file1;

public class File1 extends FileType{
	//카테고리 정보
	HashMap<String, String> categoryInfo = new HashMap<String, String>();
	//product의 id가 충돌되는지 보기위해 id를 저장
	static HashMap<String, String> productIdData = new HashMap<String, String>();
	
	public File1(){
		exceptionCheck=new ExcepCheck_file1();
	}
	
	@Override
	public ArrayList<Vector<String>> String2Data(ArrayList<String> strings) {

		System.out.println("스트링2데이터");
		ArrayList<Vector<String>> returnData = new ArrayList<Vector<String>>();
		hashSet();
		System.out.println("해쉬 셋 완료");
		for(String rowDataStr: strings) {
			Vector<String> hello=parseData(rowDataStr);

			System.out.println("헬롤옹오오오");
			returnData.add(hello);
		}
		System.out.println("리턴데이터 완료");
		return returnData;
	}

	public Vector<String> parseData(String rowDataStr){
		Vector<String> rowData = new Vector<String>();
		String[] tmp = rowDataStr.split(":");
		// if it is normal input
		// 0: ProductName  1: Product ID  2: Value  3: CurrentStock  4: Safe Stock (5: Memo)
		if(exceptionCheck.inputRuleCheck(tmp)){
			rowData.add(0,tmp[0].trim());
			if(!exceptionCheck.checkIdConflict(productIdData, tmp[1].trim())){
				productIdData.put(tmp[1].trim(), " ");
				rowData.add(1, tmp[1].trim());
				rowData.add(2, getCategory(rowData.get(1)));
				rowData.add(3,tmp[2].trim());
				rowData.add(4,tmp[3].trim());
				rowData.add(5,tmp[4].trim());
				if(tmp.length == 6)
					rowData.add(6,tmp[5].trim());
				else 
					rowData.add(6,null);
				
				if(exceptionCheck.illegalValue(rowData)){
					rowData.add(7,"Illegal Value");
				}
			}
			else{
				rowData.add(1, tmp[1].trim());
				rowData.add(2,getCategory(rowData.get(1)));
				rowData.add(3,tmp[2].trim());
				rowData.add(4,tmp[3].trim());
				rowData.add(5,tmp[4].trim());
				if(tmp.length == 6)
					rowData.add(6,tmp[5].trim());
				else 
					rowData.add(6,null);
				rowData.add(7, "Id Conflict");
			}
		}else{
			for(String a: tmp)
			rowData.add(a);
			while(rowData.size() != 7)
				rowData.add(null);
			rowData.add("irregular Product Info");
		}	
		//System.out.println(rowData.get(0));
		return rowData;
	}
	
	private void hashSet(){
		categoryInfo.put("1", "Food");
		categoryInfo.put("2", "Office");
		categoryInfo.put("3", "Misc");
		categoryInfo.put("4", "Health");
		categoryInfo.put("5", "Clothing");
	}
		
	private String getCategory(String productId){
		String categoryNumber = productId.substring(0,1);
		if(categoryInfo.containsKey(categoryNumber))
			return categoryInfo.get(categoryNumber);
		else 
			return "Category number error";
	}

	@Override
	public ArrayList<String> Data2String(ArrayList<Vector<String>> datas) {
		//0: ProductName   1: Product ID 
		//2: Category      3: Value       4: Current Stock
		//5: SafeStock     6: Memo        7: ErrorMessage
		ArrayList<String> ar =  new ArrayList<String>();
		for(Vector<String> vs : datas) {
			String tmp=new String();
			//메모가 없을때
			if(vs.get(6)==null) {
				for(int i=0;i<6;i++) {
					if(i==2) continue;
					tmp+=vs.get(i);
					if(i==5) continue;
					tmp+=":";
				}
			}
			//메모가 있을때
			else {
				for(int i=0;i<7;i++) {
					if(i==2) continue;
					tmp+=vs.get(i);
					if(i==6) continue;
					tmp+=":";
				}
			}
			ar.add(tmp);
		}		
		return ar;
	}

	@Override
	public String[] makeColumnName(String strFileType) {
		String[] columnName = {"제품명" , "제품ID" , "카테고리", "가격", "재고수", "최소재고량", "기타메모", "비고"};
		return columnName;
	}

	@Override
	public Vector<String> modifyDataCheck(Vector<String> originData, Vector<String> modifyData) {
		System.out.println("FileType_File1_modifyDataCheck : 함수안으로 들어옴");
		if((String)originData.get(1) != (String)modifyData.get(1)){
			if(this.exceptionCheck.checkIdConflict(productIdData, (String)modifyData.get(1))){
				modifyData.add("Id Conflict");
			}
		}
		if(this.exceptionCheck.illegalValue(modifyData)){
			modifyData.add("Illegal Value");
		}
		if(this.exceptionCheck.nullDataCheck(modifyData)){
			if(modifyData.size() == 8){
				modifyData.remove(7);
			}
			modifyData.add("Irregular Product Info");
		}
		if(modifyData.size()  ==  7)
			modifyData.add(null);
		return modifyData;
	}

	@Override
	public Vector<String> addDataCheck(Vector<String> rowData_ForAdd) {
		Vector<String> returnData_ForAdd=new Vector<String>();
		hashSet();
		for(String s: rowData_ForAdd){
			returnData_ForAdd.add(s);
		}
		if(this.exceptionCheck.checkIdConflict(productIdData, returnData_ForAdd.get(1))){
			returnData_ForAdd.add("Id Conflict");
		}
		if(this.exceptionCheck.illegalValue(returnData_ForAdd)){
			returnData_ForAdd.add("Illegal Value");
		}
		if(this.exceptionCheck.nullDataCheck(returnData_ForAdd)){
			if(returnData_ForAdd.size() == 8){
				returnData_ForAdd.remove(7);
			}
			returnData_ForAdd.add("Irregular Product Info");
		}
		if(returnData_ForAdd.size() ==  7){
			returnData_ForAdd.add(null);	
		}
		return returnData_ForAdd;
	}

	@Override
	public String getFileType() {
		String fileType="제품명:제품ID:카테고리:가격:재고수:최소재고량:기타메모:비고";
		return fileType;
	}
}
