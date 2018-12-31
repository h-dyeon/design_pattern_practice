package DesignPattern_FileType;

import java.util.ArrayList;
import java.util.Vector;

import DesignPattern_ExceptionCheck.ExceptionCheck;

public abstract class FileType {
	ExceptionCheck exceptionCheck;

	public abstract ArrayList<Vector<String>> String2Data(ArrayList<String> strings);
	public abstract Vector<String> parseData(String rowDataStr);
	//data�� string���� ���ľ� ��
	public abstract ArrayList<String> Data2String(ArrayList<Vector<String>> datas);
	
	public abstract String[] makeColumnName(String strFileType);
	
	public abstract Vector<String> modifyDataCheck(Vector<String> originData,Vector<String> modifyData);//������ �����Ͱ� ������������

	public abstract Vector<String> addDataCheck(Vector<String> rowData_ForAdd);
	
	public abstract String getFileType();	
}
