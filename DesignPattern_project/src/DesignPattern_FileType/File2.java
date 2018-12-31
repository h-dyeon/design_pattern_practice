package DesignPattern_FileType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import DesignPattern_ExceptionCheck.ExcepCheck_file2;

public class File2 extends FileType{
	//product의 id가 충돌되는지 보기위해 id를 저장
		static HashMap<String, String> studentIdData = new HashMap<String, String>();
	public File2(){
		exceptionCheck=new ExcepCheck_file2();
	}
	@Override
	public ArrayList<Vector<String>> String2Data(ArrayList<String> strings) {
		System.out.println("FileType_File2_String2Data : 시작");
		ArrayList<Vector<String>> returnData = new ArrayList<Vector<String>>();
		for(String rowDataStr: strings) {
			Vector<String> hello=parseData(rowDataStr);
			System.out.println("FileType_File2_String2Data : 헬롤옹오오오");
			returnData.add(hello);
		}
		System.out.println("FileType_File2_String2Data : 리턴데이터 완료");
		return returnData;
	}
	@Override
	public Vector<String> parseData(String rowDataStr) {
		Vector<String> rowData = new Vector<String>();
		String[] tmp = rowDataStr.split(":");
		// if it is normal input
		// 0: Name  1: ID  2: male/female  3: checkGraduation
		if(exceptionCheck.inputRuleCheck(tmp)){
			rowData.add(0,tmp[0].trim());
			if(!exceptionCheck.checkIdConflict(studentIdData, tmp[1].trim())){
				studentIdData.put(tmp[1].trim(), " ");
				rowData.add(1, tmp[1].trim());
				rowData.add(2,tmp[2].trim());
				rowData.add(3,tmp[3].trim());				
				if(exceptionCheck.illegalValue(rowData)){
					rowData.add(4,"Illegal Value");
				}
				else {
					rowData.add(4,null);
				}
			}
			else{
				rowData.add(1, tmp[1].trim());
				rowData.add(2,tmp[2].trim());
				rowData.add(3,tmp[3].trim());
				rowData.add(4, "Id Conflict");
			}
		}else{
			for(String a: tmp)
			rowData.add(a);
			while(rowData.size() != 4)
				rowData.add(null);
			rowData.add("irregular Product Info");
		}	
		//System.out.println(rowData.get(0));
		return rowData;
	}

	@Override
	public ArrayList<String> Data2String(ArrayList<Vector<String>> datas) {
		// 0: Name  1: ID  2: male/female  3: checkGraduation 4: ErrorMessage
				ArrayList<String> ar =  new ArrayList<String>();
				for(Vector<String> vs : datas) {
					String tmp=new String();
					for(int i=0;i<4;i++) {
						tmp+=vs.get(i);
						tmp+=":";
					}
					ar.add(tmp);
				}		
				return ar;
	}

	@Override
	public String[] makeColumnName(String strFileType) {
		String[] columnName = {"이름","학번","성별","졸업여부","비고"};
		return columnName;
	}

	@Override
	public Vector<String> modifyDataCheck(Vector<String> originData, Vector<String> modifyData) {
		if((String)originData.get(1) != (String)modifyData.get(1)){
			if(this.exceptionCheck.checkIdConflict(studentIdData, (String)modifyData.get(1))){
				modifyData.add("Id Conflict");
			}
		}
		if(this.exceptionCheck.illegalValue(modifyData)){
			modifyData.add("Illegal Value");
		}
		if(this.exceptionCheck.nullDataCheck(modifyData)){
			if(modifyData.size() == 5){
				modifyData.remove(4);
			}
			modifyData.add("Irregular Product Info");
		}
		if(modifyData.size()  ==  4)
			modifyData.add(null);
		return modifyData;
	}

	@Override
	public Vector<String> addDataCheck(Vector<String> rowData_ForAdd) {
		Vector<String> returnData_ForAdd=new Vector<String>();
		for(String s: rowData_ForAdd){
			returnData_ForAdd.add(s);
		}
		if(this.exceptionCheck.checkIdConflict(studentIdData, returnData_ForAdd.get(1))){
			returnData_ForAdd.add("Id Conflict");
		}
		if(this.exceptionCheck.illegalValue(returnData_ForAdd)){
			returnData_ForAdd.add("Illegal Value");
		}
		if(this.exceptionCheck.nullDataCheck(returnData_ForAdd)){
			if(returnData_ForAdd.size() == 5){
				returnData_ForAdd.remove(4);
			}
			returnData_ForAdd.add("Irregular Product Info");
		}
		if(returnData_ForAdd.size() ==  4){
			returnData_ForAdd.add(null);	
		}
		return returnData_ForAdd;
	}

	@Override
	public String getFileType() {
		String fileType="이름:학번:성별:졸업여부:비고";
		return fileType;
	}
	
}
