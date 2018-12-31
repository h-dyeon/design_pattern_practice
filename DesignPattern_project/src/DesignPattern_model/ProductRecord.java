package DesignPattern_model;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.Vector;

import DesignPattern_FileType.*;
import DesignPattern_view.ProductListTable;

public class ProductRecord extends Observable{
	FileIO fileIO=new FileIO();
	private Boolean Isfile;

	FileType whatfile;
	//�����
	public ArrayList<Vector<String>> totalData;// = new ArrayList<Vector<String>>();
	// vector value form : productName/productId/category/value/cStock/sStock/Memo
	String strFileType=new String();
	public String[] columnName= {"���m"};
	public Vector<String> originData;
	public int modifyRow;
	
	public ProductRecord() {
		System.out.println("��-productrecord ����");
		Isfile=false;
	}
	public void Model_fileOpen() {
		System.out.println("�ҷ��� ���� ����");
		String fileStr=fileIO.selectOpenFile();
		if(fileStr==null) {
			System.out.println("���ϰ�� ����");
		}
		else {
			System.out.println("model_ProductRecord_Model_fileOpen() : ���� ���� ����");
			totalData = new ArrayList<Vector<String>>();//���� �ʱ�ȭ�ϰ�
			ArrayList<String> openRecord=fileIO.openAndReadFile(fileStr);
			System.out.println("model_ProductRecord_Model_fileOpen() : openReadfile�Լ� �Ϸ�");
			FileFactory fF=new FileFactory();
			this.whatfile=fF.createFile(fileIO.menuStr);
			this.strFileType=this.whatfile.getFileType();
			
			//this.whatfile=fF.createFile(openRecord.get(0));
			//this.strFileType=openRecord.get(0);
			
			
			System.out.println("model_ProductRecord_Model_fileOpen() : �����ϳ� ����");
			this.totalData=whatfile.String2Data(openRecord);
			System.out.println("model_ProductRecord_Model_fileOpen() : ��Ʈ��2������ �Ϸ�");
			
			//�޴���ϸ����
			System.out.println("model_ProductRecord_Model_fileOpen() : �÷����� �޾ƿ���");
			this.columnName=whatfile.makeColumnName(this.strFileType);
			//������?
			setChanged();
			notifyObservers("MainTable");
			//view�� ����
			
			Isfile=true;
		}		
	}

	public void Model_fileSave() {
		System.out.println("������ ���� ��� ����");
		String fileStr=fileIO.selectSaveFile();
		if(fileStr==null) {
			System.out.println("���ϰ�� ����");
		}
		else {
			System.out.println("�������� ����");
			FileFactory fF=new FileFactory();
			FileType whatfile=fF.createFile(this.strFileType);
			ArrayList<String> saveRecord=whatfile.Data2String(this.totalData);
			fileIO.openAndWriteFile(saveRecord,fileStr);
		}		
	}
	public Boolean Model_isFileThere() {
		return Isfile;		
	}
	public void Model_addBtn() {
		System.out.println("model_ProductRecord_addBtn()_�߰�");
		setChanged();
		notifyObservers("addBtn");
	}
	public void Model_deleteBtn(int[] rows) {
		System.out.println("model_ProductRecord_deleteBtn()_�߰�");
		int num=rows.length;
		for(int i=num-1;i>=0;i--) {
			System.out.println("model_ProductRecord_deleteBtn()_�����ȣ : "+rows[i]);
			totalData.remove(rows[i]);
		}
		setChanged();
		notifyObservers("deleteBtn");
	}
	public void Model_modifyBtn(int row) {
		System.out.println("model_ProductRecord_modifyBtn()_����");
		
		//���� �׸�id�� �����ؾ��� originID
		this.modifyRow=row;
		originData=new Vector<String>();
		originData=totalData.get(this.modifyRow);
		System.out.println("model_ProductRecord_modifyBtn() : originData ������");
		setChanged();
		notifyObservers("modifyBtn");
	}
	public void Model_doneAddBtn(Vector<String> rowData_ForAdd) {
		System.out.println("model_ProductRecord_doneAddBtn()_�߰��Ϸ��ư");
		
		Vector<String> returnData_ForAdd=new Vector<String>();
		for(String str:whatfile.addDataCheck(rowData_ForAdd)){
			returnData_ForAdd.add(str);
		}
		
		totalData.add(returnData_ForAdd);
		setChanged();
		notifyObservers("doneAddBtn");
	}
	public void Model_doneModifyBtn(Vector<String> rowData_ForModify) {
		System.out.println("model_ProductRecord_doneModifyBtn()_�����Ϸ��ư");
		this.originData=this.whatfile.modifyDataCheck(this.totalData.get(modifyRow),rowData_ForModify);
		this.totalData.remove(this.modifyRow);
		this.totalData.add(this.modifyRow,this.originData);
		//this.totalData.get(this.modifyRow).clear();
		//this.totalData.get(this.modifyRow).addAll(this.modifyData);
		setChanged();
		notifyObservers("doneModifyBtn");
	}
	
}
